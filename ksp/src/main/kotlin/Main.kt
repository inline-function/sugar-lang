@file:Suppress("NestedLambdaShadowedImplicitParameter","NAME_SHADOWING")

package sugar.lang

import com.google.devtools.ksp.*
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.KModifier.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import java.io.OutputStreamWriter
import java.util.*

const val builderJvmName = "tools.Builder"
const val head = "@file:Suppress(\"NOTHING_TO_INLINE\",\"RedundantVisibilityModifier\",\"RemoveRedundantQualifierName\",\"unused\")"
class BuilderSP(
    val codeGenerator: CodeGenerator,
    val logger: KSPLogger
) : SymbolProcessor {
    override fun process(resolver : Resolver) =
        resolver.getSymbolsWithAnnotation(builderJvmName)
            .also { it
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
            fileName     = "${classDecl.simpleName.asString()}BuilderExtensions"
        )
        val code = buildString {
            //为文件添加包名和警告抑制注解 : package xxx.xxx
            run{
                appendLine(head)
                appendLine(packageName)
            }
            //扩展函数的接收者
            val receiver = run{
                classDecl
                    .asStarProjectedType()
                    .declaration.let {
                        ClassName(
                            it.packageName.asString(),it.simpleName.asString()
                        )
                    }
                    .let {
                        if(classDecl.typeParameters.isNotEmpty())
                            classDecl.typeParameters.drop(1).fold(
                                it.plusParameter(
                                    TypeVariableName(
                                        classDecl.typeParameters.first().name.asString()
                                    )
                                )
                            ) { acc,it ->
                                acc.plusParameter(TypeVariableName(it.name.asString()))
                            }
                        else it
                    }
            }
            //为每一个属性生成它自己的设置函数 : Xxx.varName(it : VarType) : Xxx
            run {
                classDecl
                    .primaryConstructor
                    ?.parameters
                    ?.map {
                        it
                            .name
                            ?.asString()
                            ?.run {
                                FunSpec
                                    .builder(this)
                                    .addTypeVariables(classDecl.typeParameters.map {
                                        TypeVariableName(
                                            it.name.asString(),
                                            it
                                                .bounds
                                                .first()
                                                .resolve()
                                                .declaration
                                                .let {
                                                    ClassName(
                                                        it.packageName.asString(),
                                                        it.simpleName.asString()
                                                    )
                                                }
                                        )
                                    })
                                    .addParameter(
                                        ParameterSpec.builder(
                                            "it",
                                            it.type.resolve().let {
                                                ClassName(
                                                    it.declaration.packageName.asString(),
                                                    it.declaration.simpleName.asString()
                                                )
                                            }.run {
                                                fun classWithGeneric(it : KSValueParameter,cls : ClassName) = it
                                                    .type
                                                    .resolve()
                                                    .arguments
                                                    .let {
                                                        if(it.isNotEmpty())
                                                            it.drop(1).fold(
                                                                cls.plusParameter(
                                                                    TypeVariableName(
                                                                        it[0]
                                                                            .type!!
                                                                            .resolve()
                                                                            .fullName
                                                                    )
                                                                )
                                                            ) { acc,it ->
                                                                acc.plusParameter(
                                                                    TypeVariableName(
                                                                        it
                                                                            .type!!
                                                                            .resolve()
                                                                            .fullName
                                                                    )
                                                                )
                                                            }
                                                        else cls
                                                    }
                                                classWithGeneric(it,this)
                                            }
                                        ).apply {
                                            ((it.type
                                                .resolve()
                                                .declaration
                                                    as? KSTypeAlias
                                                    )
                                                ?.type
                                                ?.resolve()
                                                ?.declaration
                                                ?.qualifiedName
                                                ?.takeIf { it.asString().startsWith("kotlin.Function") }
                                                ?.let { addModifiers(NOINLINE) })
                                        }.build()
                                    )
                                    .returns(receiver)
                                    .receiver(receiver)
                                    .addModifiers(PUBLIC,INLINE,INFIX)
                                    .addStatement(
                                        "return apply { ${
                                            it.name!!.asString().let {
                                                it.takeUnless {
                                                    it in listOf(
                                                        "else",
                                                        "if",
                                                        "for",
                                                        "while",
                                                        "do",
                                                        "try",
                                                        "catch",
                                                        "finally",
                                                        "return",
                                                        "break",
                                                        "continue",
                                                        "throw"
                                                    )
                                                } ?: "`$it`"
                                            }
                                        } = it }")
                                    .build()
                            } ?: ""
                    }
                    ?.forEach(::append)
            }
            //为每一个属性生成它自己的设置函数(作用域版本) : Xxx.varName(it : Xxx.()->VarType) : Xxx
            run {
                    classDecl
                        .primaryConstructor
                        ?.parameters
                        ?.map {
                            it
                                .name
                                ?.asString()
                                ?.run {
                                    FunSpec
                                        .builder(this)
                                        .addTypeVariables(classDecl.typeParameters.map {
                                            TypeVariableName(
                                                it.name.asString(),
                                                it
                                                    .bounds
                                                    .first()
                                                    .resolve()
                                                    .declaration
                                                    .let {
                                                        ClassName(
                                                            it.packageName.asString(),
                                                            it.simpleName.asString()
                                                        )
                                                    }
                                            )
                                        })
                                        .addParameter(
                                            ParameterSpec.builder(
                                                "it",
                                                LambdaTypeName.get(
                                                    receiver = receiver,
                                                    parameters = listOf(),
                                                    returnType = it.type.resolve().let {
                                                        ClassName(
                                                            it.declaration.packageName.asString(),
                                                            it.declaration.simpleName.asString()
                                                        )
                                                    }.run {
                                                        fun classWithGeneric(it : KSValueParameter,cls : ClassName) = it
                                                            .type
                                                            .resolve()
                                                            .arguments
                                                            .let {
                                                                if(it.isNotEmpty())
                                                                    it.drop(1).fold(
                                                                        cls.plusParameter(
                                                                            TypeVariableName(
                                                                                it[0]
                                                                                    .type!!
                                                                                    .resolve()
                                                                                    .fullName
                                                                            )
                                                                        )
                                                                    ) { acc,it ->
                                                                        acc.plusParameter(
                                                                            TypeVariableName(
                                                                                it
                                                                                    .type!!
                                                                                    .resolve()
                                                                                    .fullName
                                                                            )
                                                                        )
                                                                    }
                                                                else cls
                                                            }
                                                        classWithGeneric(it,this)
                                                    }
                                                )
                                            ).apply {
                                                ((it.type
                                                    .resolve()
                                                    .declaration
                                                        as? KSTypeAlias
                                                        )
                                                    ?.type
                                                    ?.resolve()
                                                    ?.declaration
                                                    ?.qualifiedName
                                                    ?.takeIf { it.asString().startsWith("kotlin.Function") }
                                                    ?.let { addModifiers(CROSSINLINE) })
                                            }.build()
                                        )
                                        .returns(receiver)
                                        .receiver(receiver)
                                        .addModifiers(PUBLIC,INLINE,INFIX)
                                        .addStatement(
                                            "return apply { ${
                                                it.name!!.asString().let {
                                                    it.takeUnless {
                                                        it in listOf(
                                                            "else",
                                                            "if",
                                                            "for",
                                                            "while",
                                                            "do",
                                                            "try",
                                                            "catch",
                                                            "finally",
                                                            "return",
                                                            "break",
                                                            "continue",
                                                            "throw"
                                                        )
                                                    } ?: "`$it`"
                                                }
                                            } = it() }")
                                        .build()
                                } ?: ""
                        }
                        ?.forEach(::append)
                }
            //为可变列表属性生成添加函数 : Xxx.addToList(it : Type) : Xxx
            run {
                classDecl
                    .primaryConstructor
                    ?.parameters
                    ?.filter { ( it
                            .type
                            .resolve()
                            .declaration
                            as? KSTypeAlias
                        ).run { this
                            ?.type
                            ?.resolve()
                            ?.declaration
                            ?.qualifiedName
                            ?: it
                            .type
                            .resolve()
                            .declaration
                            .qualifiedName
                        }?.asString() == "kotlin.collections.MutableList"
                    }
                    ?.map {
                        it
                            .name
                            ?.asString()
                            ?.run {
                                FunSpec
                                    .builder("addTo${replaceFirstChar { if(it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}")
                                    .addTypeVariables(classDecl.typeParameters.map {
                                        TypeVariableName(
                                            it.name.asString(),
                                            it
                                                .bounds
                                                .first()
                                                .resolve()
                                                .declaration
                                                .let {
                                                    ClassName(
                                                        it.packageName.asString(),
                                                        it.simpleName.asString()
                                                    )
                                                }
                                        )
                                    })
                                    .addParameter(
                                        ParameterSpec.builder(
                                            "it",
                                            (it
                                                .type
                                                .resolve()
                                                .declaration
                                                as? KSTypeAlias
                                            )
                                            .run { this
                                                ?.type
                                                ?.resolve()
                                                ?: it
                                                .type
                                                .resolve()
                                            }
                                            .let {
                                                ClassName(
                                                    it.arguments[0].type!!.resolve().declaration.packageName.asString(),
                                                    it.arguments[0].type!!.resolve().declaration.simpleName.asString()
                                                )
                                            }
                                            .run {
                                                fun classWithGeneric(it : KSValueParameter,cls : ClassName) =
                                                    it.run {
                                                        if(type.resolve().declaration.qualifiedName!!.asString() != "kotlin.collections.MutableList")
                                                            type
                                                                .resolve()
                                                                .arguments
                                                        else
                                                            type
                                                                .resolve()
                                                                .arguments[0]
                                                                .type!!
                                                                .resolve()
                                                                .arguments
                                                    }
                                                    .let {
                                                        if(it.isNotEmpty())
                                                            it.drop(1).fold(
                                                                cls.plusParameter(
                                                                    TypeVariableName(
                                                                        it[0]
                                                                            .type!!
                                                                            .resolve()
                                                                            .fullName
                                                                    )
                                                                )
                                                            ) { acc,it ->
                                                                acc.plusParameter(
                                                                    TypeVariableName(
                                                                        it
                                                                            .type!!
                                                                            .resolve()
                                                                            .fullName
                                                                    )
                                                                )
                                                            }
                                                        else cls
                                                    }
                                                if(simpleName.last().isUpperCase())
                                                    ClassName(
                                                        "",
                                                        simpleName
                                                    )
                                                else
                                                    classWithGeneric(
                                                        it,
                                                        this
                                                    )
                                            }
                                        ).apply {
                                            (
                                                (it
                                                    .type
                                                    .resolve()
                                                    .declaration
                                                    as? KSTypeAlias
                                                )
                                                    ?.type
                                                    ?.resolve()
                                                    ?.declaration
                                                    ?.qualifiedName
                                                    ?.takeIf { it.asString().startsWith("kotlin.Function") }
                                                    ?.let { addModifiers(NOINLINE) }
                                            )
                                        }.build()
                                    )
                                    .returns(receiver)
                                    .receiver(receiver)
                                    .addModifiers(PUBLIC,INLINE)
                                    .addStatement(
                                        "return apply { ${
                                            it.name!!.asString().let {
                                                it.takeUnless {
                                                    it in listOf(
                                                        "else",
                                                        "if",
                                                        "for",
                                                        "while",
                                                        "do",
                                                        "try",
                                                        "catch",
                                                        "finally",
                                                        "return",
                                                        "break",
                                                        "continue",
                                                        "throw"
                                                    )
                                                } ?: "`$it`"
                                            }
                                        }.add(it) }")
                                    .build()
                            } ?: ""
                    }
                    ?.forEach(::append)
            }
        }
        OutputStreamWriter(file).use {
            it.append(code)
        }
    }
    val KSClassDeclaration.generic : String
        get() = asStarProjectedType()
            .declaration
            .qualifiedName
            .let { it ?: simpleName }
            .asString()
            .plus( typeParameters
                .joinToString(",","<",">") { it.name.asString() }
                .takeUnless { it == "<>" }
                ?: ""
            )
    val KSType.fullName : String get() = declaration
        .qualifiedName
        ?.asString()
        ?.let {
            if(it.last().isUpperCase())
                "${it.last()}"
            else
                it
        }
        ?.plus( arguments
            .map { it
                .type
                ?.resolve()
                ?.declaration
                ?.let {
                    if(it.qualifiedName!!.asString().last().isUpperCase())
                        it.qualifiedName!!.asString().last()
                    else
                        it.qualifiedName!!.asString()
                }
                ?.takeUnless { it == "kotlin.Any" }
                ?: "*"
            }
            .joinToString(
                separator = ",",
                prefix    = "<",
                postfix   = ">"
            )
            .takeUnless {
                it == "<>"
            } ?: ""
        ) ?: "kotlin.Nothing"
}
class BuilderSPP : SymbolProcessorProvider {
    override fun create(
        environment: SymbolProcessorEnvironment
    ) = BuilderSP(environment.codeGenerator, environment.logger)
}

fun main() {
    println("Hello World!")
}