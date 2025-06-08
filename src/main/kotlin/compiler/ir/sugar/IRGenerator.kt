//@file:Suppress("NestedLambdaShadowedImplicitParameter")
//
package compiler.ir.sugar
//
//import compiler.ir.context
//import compiler.semantic.*
//import compiler.semantic.ProjectTree
//import tools.ID
//import tools.never
//import kotlin.collections.plusAssign
//
//data class IRSBuilder(
//    val functions : MutableList<FunctionDecIR> = mutableListOf(),
//    val classes : MutableList<ClassDecIR> = mutableListOf(),
//    val variables : MutableList<VariableDecIR> = mutableListOf(),
//    val commands : MutableMap<ID,List<SugarRunnableIR>> = mutableMapOf(),
//)
//data class RegisterAllocator(
//    var index : Int = 0,
//    val mapping : MutableMap<ID,Int> = mutableMapOf()
//)
//inline val RegisterAllocator.allocate get() = index++
//fun RegisterAllocator.mapName(name : ID) : Int = allocate.also {
//    mapping[name] = it
//}
//fun RegisterAllocator.findName(name : ID) : Int? = mapping[name]
//val IRSBuilder.result get() = IntermediateRepresentation(
//    classes = classes.toList(),
//    functions = functions.toList(),
//    variables = variables.toList(),
//    commands = commands.toMap(),
//)
//fun ProjectTree.generateIR() : IRS =
//    context(IRSBuilder()){ irs ->
//        processVariables()
//        processFunctions()
//        processClasses()
//        irs.result
//    }
//context(tree : ProjectTree,irs : IRSBuilder)
//fun processVariables() = tree.files
//    .associateWith { it.tops }
//    .mapValues { it.value.filterIsInstance<VariableTree>() }
//    .flatMap { (file,vars) -> vars.associateBy { file.name }.toList() }
//    .forEach { (file,variable) ->
//        val ir = VariableDecIR(
//            name = variable.name,
//            type = variable.type.ir,
//            line = variable.line,
//            host = null,
//            file = file,
//            anns = variable.annotations.map { it.ir },
//        )
//        irs.variables += ir
//        context(RegisterAllocator(),mutableListOf<SugarRunnableIR>()){ _,_ ->
//            val index = variable.value!!.resultRegister()
//            irs.commands[ir.runnableID] = listOf(
//                AssignIR(
//                    name      = irs.variables.indexOf(ir),
//                    value     = index,
//                    isLocal   = false,
//                    formLocal = true,
//                )
//            )
//        }
//    }
//context(tree : ProjectTree,irs : IRSBuilder)
//fun processFunctions() = tree.files
//    .associateWith { it.tops }
//    .mapValues { it.value.filterIsInstance<FunctionTree>() }
//    .flatMap { (file,funs) -> funs.associateBy { file.name }.toList() }
//    .forEach { (file,function) ->
//        val ir = FunctionDecIR(
//            name = function.name,
//            anns = function.annotations.map { it.ir },
//            type = function.returnType.ir,
//            host = null,
//            typeParams = function.typeParameters,
//            file = file,
//            line = function.line,
//            params = function.parameters.associate { it.name to it.type.ir }
//        )
//        irs.functions += ir
//        irs.commands[ir.runnableID] = function.body?.ir ?: emptyList()
//    }
//context(tree : ProjectTree,irs : IRSBuilder)
//fun processClasses() = tree.files
//    .associateWith { it.tops }
//    .mapValues { it.value.filterIsInstance<ClassTree>() }
//    .flatMap { (file,classes) -> classes.associateBy { file.name }.toList() }
//    .forEach { (file,cls) ->
//        val ir = ClassDecIR(
//            name = cls.name,
//            anns = cls.annotations.map { it.ir },
//            file = file,
//            line = cls.line,
//            typeParams = cls.typeParameters,
//            parents = cls.parents.map { it.ir }
//        )
//        irs.classes += ir
//        cls.members
//            .filterIsInstance<FunctionTree>()
//            .forEach { function ->
//                val ir = FunctionDecIR(
//                    name = function.name,
//                    anns = function.annotations.map { it.ir },
//                    type = function.returnType.ir,
//                    host = ir.name,
//                    typeParams = function.typeParameters,
//                    file = file,
//                    line = function.line,
//                    params = function.parameters.associate { it.name to it.type.ir }
//                )
//                irs.functions += ir
//                irs.commands[ir.runnableID] = function.body?.ir ?: emptyList()
//            }
//        cls.members
//            .filterIsInstance<VariableTree>()
//            .map { variable ->
//                val ir = VariableDecIR(
//                    name = variable.name,
//                    type = variable.type.ir,
//                    line = variable.line,
//                    host = ir.name,
//                    file = file,
//                    anns = variable.annotations.map { it.ir },
//                )
//                irs.variables += ir
//                TODO("变量赋值")
//            }
//    }
//context(tree : ProjectTree,irs : IRSBuilder)
//val BlockTree.ir : List<SugarRunnableIR>
//    get() = buildList {
//        RegisterAllocator().run {
//            this@ir.stmts.forEach {
//                when(it){
//                    is InvokeTree   -> {
//                        val name = it.invoker.resultRegister()
//                        val args = it.arguments.map { it.resultRegister() }
//                        this@buildList += InvokeIR(
//                            name = name,
//                            args = args,
//                            result = index,
//                            isLocal = true,
//                            resultIsLocal = true
//                        )
//                    }
//                    is FunctionTree -> TODO()
//                    is VariableTree -> {
//                        val expr = it.value!!.resultRegister()
//                        this@buildList += AssignIR(
//                            name      = mapName(it.name),
//                            value     = expr,
//                            isLocal   = true,
//                            formLocal = true,
//                        )
//                    }
//                    is ClassTree    -> TODO()
//                    else            -> Unit
//                }
//            }
//        }
//    }
//context(allocator : RegisterAllocator,list : MutableList<SugarRunnableIR>,irs : IRSBuilder)
//fun ExpressionTree.resultRegister() : Int = when(this){
//    is DecimalConstantTree -> allocator.allocate.also { index ->
//        list += AssignConstantIR(
//            name = index,
//            value = DecimalConstant(value),
//            isLocal = true
//        )
//    }
//    is IntegerConstantTree -> allocator.allocate.also { index ->
//        list += AssignConstantIR(
//            name = index,
//            value = IntegerConstant(value),
//            isLocal = true
//        )
//    }
//    is StringConstantTree  -> allocator.allocate.also { index ->
//        list += AssignConstantIR(
//            name = index,
//            value = StringConstant(value),
//            isLocal = true
//        )
//    }
//    is InvokeTree          -> allocator.allocate.also { index ->
//        val name = invoker.resultRegister()
//        val args = arguments.map { it.resultRegister() }
//        list += InvokeIR(
//            name          = name ,
//            args          = args ,
//            result        = index,
//            isLocal       = true ,
//            resultIsLocal = true ,
//        )
//    }
//    is LambdaTree          -> TODO()
//    is NameTree            -> expression?.run {
//        val expr = resultRegister()
//        allocator.allocate.also {
//            list += AssignMemberIR(
//                name = expr,
//                member = name,
//                isLocal = true,
//                result = it,
//                resultIsLocal = allocator.findName(name)?.let { true } ?: false
//            )
//        }
//    } ?: allocator.findName(name)
//      ?: irs.variables.find { it.name == name }
//      ?: /** 将寄存器封装成一个类 */
//}
//val AnnotationTree.ir : AnnotationIR
//    get() = AnnotationIR(
//        name = name,
//        value = arguments?.let {
//            when(it){
//                is DecimalConstantTree -> DecimalConstant(it.value)
//                is IntegerConstantTree -> IntegerConstant(it.value)
//                is StringConstantTree  -> StringConstant(it.value)
//                else -> never
//            }
//        },
//        line = line
//    )
//val TypeTree.ir : TypeIR get() = context(this)
//    { typeTree ->
//        when(typeTree) {
//            is ApplyTypeTree    -> TypeIR(
//                name = typeTree.name,
//                typeParams = typeTree.arguments.map { it.ir }
//            )
//            is CommonTypeTree   -> TypeIR(
//                name = typeTree.name,
//                typeParams = emptyList()
//            )
//            is FunctionTypeTree -> TypeIR(
//                name = typeTree.name,
//                typeParams = typeTree.parameters.map { it.ir }
//            )
//            is NullableTypeTree -> TypeIR(
//                name = typeTree.name,
//                typeParams = listOf(typeTree.type.ir)
//            )
//        }
//    }