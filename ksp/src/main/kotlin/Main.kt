@file:Suppress("NestedLambdaShadowedImplicitParameter","NAME_SHADOWING")

package sugar.lang

import com.google.devtools.ksp.*
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import java.io.OutputStreamWriter
const val builderJvmName = "tools.Builder"
class BuilderSP(
    val codeGenerator: CodeGenerator,
    val logger: KSPLogger
) : SymbolProcessor {
    override fun process(resolver : Resolver) =
        resolver.getSymbolsWithAnnotation(builderJvmName)
            .also {
                it
                    .filter(KSAnnotated::validate)
                    .filterIsInstance<KSClassDeclaration>()
                    .forEach(::generateBuilderExtensions)
            }
            .filterNot(KSAnnotated::validate)
            .toList()
    fun generateBuilderExtensions(classDecl : KSClassDeclaration){
        val packageName = classDecl
            .containingFile
            ?.packageName
            ?.asString()
            ?.takeUnless { it == "" }
            ?.let { "package $it" }
            ?:""
        val file = codeGenerator.createNewFile(
            dependencies = Dependencies(false, classDecl.containingFile!!),
            packageName  = packageName.drop(8),
            fileName     = "${classDecl.simpleName.asString()}ExtensionFunctionsForDomainSpecificLanguage"
        )
        val code = buildString {
            append("""@file:Suppress("NOTHING_TO_INLINE","RemoveRedundantQualifierName","unused","UNNECESSARY_NOT_NULL_ASSERTION","TrailingComma")

            """.trimMargin())
            val packageName = classDecl.packageName.asString()
            if(packageName!="")
                append("package $packageName\n")
            append(classDecl.builder)
            append("\n")
            append(classDecl.propertySetter)
            append(classDecl.listAdd)
            append(classDecl.factory)
            append(classDecl.result)
        }
        OutputStreamWriter(file).use {
            it.append(code)
        }
    }
    //生成构建函数
    val KSClassDeclaration.result : String get() = buildString {
        val (receiver0,generic) = generic
        val receiver = receiver0 + "Builder"
        val result = primaryConstructor
            ?.parameters
            ?.map {
                val name = it.name!!.asString()
                if(it.type.resolve().nullability != Nullability.NULLABLE)
                    "$name!!" else name
            }
        append("inline val $generic $receiver.result get() = $receiver0(${result!!.joinToString(",")})\n")
    }
    //生成类的(工厂)构造函数
    val KSClassDeclaration.factory : String get() = buildString {
        val (receiver0,generic) = generic
        val receiver = receiver0 + "Builder"
        val funName = "build${simpleName.asString()}"
        val result = primaryConstructor
            ?.parameters
            ?.map {
                var value : String? = null
                val type = run{
                    val name = it.name!!.asString()
                    var fn = it.type.resolve().realType.fullName
                    when{
                        fn.startsWith("kotlin.collections.List") -> {
                            fn = "kotlin.collections.MutableList" + fn.drop(23)
                            value = "kotlin.collections.mutableListOf()"
                        }
                        fn.startsWith("kotlin.collections.Map") -> {
                            fn = "kotlin.collections.MutableMap" + fn.drop(22)
                            value = "kotlin.collections.mutableMapOf()"
                        }
                        else -> {
                            fn += "?"
                            value = "null"
                        }
                    }
                    fn
                }
                val noinline = if(it
                        .type
                        .resolve()
                        .realType
                        .declaration
                        .qualifiedName
                        ?.asString()
                        ?.startsWith("kotlin.Function")
                    == true
                ) "noinline" else ""
                "$noinline ${it.name!!.asString()} : $type = $value"
            }!!
        append("inline fun $generic $funName(${result.joinToString(",")},alsoBlock : $receiver.()->Unit = {}) = $receiver(${primaryConstructor!!.parameters.map { it.name!!.asString() }.joinToString(",")}).also(alsoBlock)\n")
    }
    //生成该类其构建器
    val KSClassDeclaration.builder : String get() = buildString {
        val name = simpleName.asString() + "Builder"
        val generic = generic.second
        append("data class $generic $name(")
        primaryConstructor
            ?.parameters
            ?.map {
            val name = it.name?.asString()
            val type = run{
                var fn = it.type.resolve().realType.fullName
                when{
                    fn.startsWith("kotlin.collections.List") -> {
                        fn = "kotlin.collections.MutableList" + fn.drop(23)
                    }
                    fn.startsWith("kotlin.collections.Map") -> {
                        fn = "kotlin.collections.MutableMap" + fn.drop(22)
                    }
                    else -> {
                        fn += "?"
                    }
                }
                fn
            }
            append("var $name : $type,")
        }
        append(")")
    }
    //为列表属性生成添加函数
    val KSClassDeclaration.listAdd : String get() = buildString {
        val (receiver,generic) = generic
        primaryConstructor
            ?.parameters
            ?.filter { it.type.resolve().realType.declaration.qualifiedName?.asString()?.startsWith("kotlin.collections.List") == true }
            ?.map {
            val receiver = receiver + "Builder"
            val name = it.name!!.asString()
            val type = run{
                var fn = it.type.resolve().realType.arguments[0].type!!.resolve().fullName
                when{
                    fn.startsWith("kotlin.collections.List") -> {
                        fn = "kotlin.collections.MutableList" + fn.drop(23)
                    }
                    fn.startsWith("kotlin.collections.Map") -> {
                        fn = "kotlin.collections.MutableMap" + fn.drop(22)
                    }
                }
                fn
            }
            val noinline = if(it
                    .type
                    .resolve()
                    .realType
                    .declaration
                    .qualifiedName
                    ?.asString()
                    ?.startsWith("kotlin.Function")
                == true
            ) "noinline" else ""
            append("inline infix fun $generic $receiver.addTo${name.replaceFirstChar { it.uppercase() }}($noinline it : $type) = apply { $name.add(it) }\n")
            append("inline infix fun $generic $receiver.addTo${name.replaceFirstChar { it.uppercase() }}(crossinline it : ()->$type) = apply { $name.add(it()) }\n")
        }
    }
    //为每一个属性生成它的赋值函数
    val KSClassDeclaration.propertySetter : String get() = buildString {
        val (receiver,generic) = generic
        primaryConstructor?.parameters?.map {
            val receiver = receiver + "Builder"
            val name = it.name!!.asString()
            val type = run{
                var fn = it.type.resolve().realType.fullName
                when{
                    fn.startsWith("kotlin.collections.List") -> {
                        fn = "kotlin.collections.MutableList" + fn.drop(23)
                    }
                    fn.startsWith("kotlin.collections.Map") -> {
                        fn = "kotlin.collections.MutableMap" + fn.drop(22)
                    }
                }
                fn
            }
            val noinline = if(it
                .type
                .resolve()
                .realType
                .declaration
                .qualifiedName
                ?.asString()
                ?.startsWith("kotlin.Function")
                == true
            ) "noinline" else ""
            append("inline infix fun $generic $receiver.$name($noinline it : $type) = apply { $name = it }\n")
            append("inline infix fun $generic $receiver.$name(crossinline it : ()->$type) = apply { $name = it() }\n")
        }
    }
    //获取一个泛型类的全名带泛型形参表示(左),并且额外返回泛型列表(右)
    val KSClassDeclaration.generic : Pair<String,String> get() = (qualifiedName ?: simpleName)
        .asString()
        .run {
            (if(typeParameters.isNotEmpty())
                typeParameters.joinToString(
                    separator = ",",
                    prefix    = "<",
                    postfix   = ">",
                ) { it.name.asString() }
            else "").let {
                this+it to it
            }
        }
    //如果类型是类型别名则返回真实类型,否则返回自己
    val KSType.realType : KSType get() =
        (declaration as? KSTypeAlias)?.type?.resolve()?.replace(arguments) ?: this
    //将一个类型递归的转成字符串,包含它的类型实参
    val KSType.fullName : String get() = declaration
        .qualifiedName
        ?.asString()
        ?.plus( arguments
            .map { it
                .type
                ?.resolve()
                ?.fullName
            }
            .joinToString(
                separator = ",",
                prefix    = "<",
                postfix   = ">"
            )
            .takeUnless {
                it == "<>"
            } ?: ""
        ) ?: declaration
            .simpleName
            .asString()
}
class BuilderSPP : SymbolProcessorProvider {
    override fun create(
        environment: SymbolProcessorEnvironment
    ) = BuilderSP(environment.codeGenerator, environment.logger)
}

fun main() {
    println("Hello World!")
}