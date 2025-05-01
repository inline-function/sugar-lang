package compiler.semantic

import tools.*

/**
 * 获取文件树构建器的只读抽象语法树结果
 * @return 只读抽象语法树
 * @receiver 文件树构建器
 * @throws Exception 如果父语法树为空则抛出异常
 */
val FileTreeBuilder.result : FileTree
    get() = FileTree(
        name = name,
        tops = tops.map { it.result },
        parent = parent?.result ?: exception(nullParentMessage)
    )
/**
 * 获取文件抽象语法树相应的构建器
 * @receiver 文件抽象语法树
 * @return 构建器
 */
val FileTree.builder : FileTreeBuilder
    get() = FileTreeBuilder(
        name = name,
        tops = tops.map { it.builder }.toMutableList(),
        parent = parent.builder
    )
/**
 * 获取内部语法树相应的构建器
 * @receiver 内部语法树
 * @return 构建器
 */
@Suppress("UNCHECKED_CAST")
val <R : InnerTree> R.builder : InnerTreeBuilder<R>
    get() = when(this) {
        is DecimalConstantTree -> builder
        is IntegerConstantTree -> builder
        is StringConstantTree  -> builder
        is IfTree              -> builder
        is InvokeTree          -> builder
        is NameTree            -> builder
        is FunctionTree        -> builder
        is VariableTree        -> builder
        is ClassTree           -> builder
        is CommonTypeTree      -> builder
        else                   -> never
    } as InnerTreeBuilder<R>
fun <T : InnerTreeBuilder<*>> T.line(newLine : Int) =
    apply { line = newLine }
fun <T : InnerTreeBuilder<*>> T.column(newColumn : Int) =
    apply { column = newColumn }
fun <T : InnerTreeBuilder<*>> T.info(tree : compiler.parser.InnerTree) =
    apply { line(tree.line) ; column(tree.column) }
/**
 * 获取名称抽象语法树相应的构建器
 * @receiver 名称抽象语法树
 * @return 构建器
 */
val NameTree.builder : NameTreeBuilder
    get() = NameTreeBuilder(
        line       = line,
        column     = column,
        expression = expression?.builder,
        name       = name,
        parent     = { parent }
    )
/**
 * 获取工程树构建器的只读抽象语法树结果
 * @return 只读抽象语法树
 * @receiver 工程树构建器
 */
val ProjectTreeBuilder.result : ProjectTree
    get() = ProjectTree(
        name = name,
        files = files.map { it.result }
    )
/**
 * 获取工程抽象语法树相应的构建器
 * @receiver 工程抽象语法树
 * @return 构建器
 */
val ProjectTree.builder : ProjectTreeBuilder
    get() = ProjectTreeBuilder(
        name = name,
        files = files.map { it.builder }.toMutableList()
    )
/**
 * 获取块抽象语法树相应的构建器
 * @receiver 块抽象语法树
 * @return 构建器
 */
val BlockTree.builder : BlockTreeBuilder
    get() = BlockTreeBuilder(
        line       = line,
        column     = column,
        stmts      = stmts.map { it.builder }.toMutableList(),
        parent     = { parent }
    )
/**
 * 获取if抽象语法树相应的构建器
 * @receiver if抽象语法树
 * @return 构建器
 */
val IfTree.builder : IfTreeBuilder
    get() = IfTreeBuilder(
        line       = line,
        column     = column,
        condition  = condition.builder,
        then       = then.builder,
        `else`     = `else`.builder,
        parent     = { parent }
    )
/**
 * 获取类抽象语法树相应的构建器
 * @receiver 类抽象语法树
 * @return 构建器
 */
val ClassTree.builder : ClassTreeBuilder
    get() = ClassTreeBuilder(
        line       = line,
        column     = column,
        name       = name,
        parents    = parents.map { it.builder }.toMutableList(),
        members    = members.map { it.builder }.toMutableList(),
        parent     = { parent }
    )
/**
 * 获取函数抽象语法树相应的构建器
 * @receiver 函数抽象语法树
 * @return 构建器
 * @throws Exception 返回类型/函数体为空
 */
val FunctionTree.builder : FunctionTreeBuilder
    get() = FunctionTreeBuilder(
        line       = line,
        column     = column,
        name       = name,
        parameters = parameters.map { it.builder }.toMutableList(),
        returnType = returnType?.builder ?: exception("返回类型为空,语法树构建失败"),
        body       = body?.builder ?: exception("函数体为空,语法树构建失败"),
        parent     = { parent }
    )
/**
 * 获取变量抽象语法树相应的构建器
 * @receiver 变量抽象语法树
 * @return 构建器
 */
val VariableTree.builder : VariableTreeBuilder
    get() = VariableTreeBuilder(
        line       = line,
        column     = column,
        name       = name,
        type       = returnType?.builder,
        value      = value?.builder,
        parent     = { parent }
    )
/**
 * 获取调用抽象语法树相应的构建器
 * @receiver 调用抽象语法树
 * @return 构建器
 */
val InvokeTree.builder : InvokeTreeBuilder
    get() = InvokeTreeBuilder(
        line       = line,
        column     = column,
        invoker    = invoker.builder,
        arguments  = arguments.map { it.builder }.toMutableList(),
        parent     = { parent }
    )
/**
 * 获取字符串常量抽象语法树相应的构建器
 * @receiver 字符串常量抽象语法树
 * @return 构建器
 */
val StringConstantTree.builder : StringConstantTreeBuilder
    get() = StringConstantTreeBuilder(
        line       = line,
        column     = column,
        value      = value,
        parent     = { parent }
    )
/**
 * 获取整型常量抽象语法树相应的构建器
 * @receiver 整型常量抽象语法树
 * @return 构建器
 */
val IntegerConstantTree.builder : IntegerConstantTreeBuilder
    get() = IntegerConstantTreeBuilder(
        line       = line,
        column     = column,
        value      = value,
        parent     = { parent }
    )
/**
 * 获取浮点常量抽象语法树相应的构建器
 * @receiver 浮点常量抽象语法树
 * @return 构建器
 */
val DecimalConstantTree.builder : DecimalConstantTreeBuilder
    get() = DecimalConstantTreeBuilder(
        line       = line,
        column     = column,
        value      = value,
        parent     = { parent }
    )
/**
 * 获取普通类型抽象语法树相应的构建器
 * @receiver 普通类型抽象语法树
 * @return 构建器
 */
val CommonTypeTree.builder : CommonTypeTreeBuilder
    get() = CommonTypeTreeBuilder(
        line       = line,
        column     = column,
        name       = name,
        parent     = { parent }
    )