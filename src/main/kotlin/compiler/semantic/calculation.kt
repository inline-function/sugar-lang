package compiler.semantic
//允许接收者安全转换为某类型
context(info : MutableInformation,scope : LexicalScope)
infix fun TypeTree.isCastableTo(type : TypeTree) : Boolean{
    //如果类型是类型本身，则返回true
    if(type == this) return true
    //查找接收者的定义
    val left = scope.findClassSymbol(name)
    left?.parents
        ?.map { it.toAst() }
        ?.forEach {
            //如果父类中有类型，则返回true
            if(it == type) return true
            //如果已经是最高父类,则返回假
            if(it.name == highestParentClass) return false
            //如果接收者可以转换为父类，则返回true
            if(this isCastableTo it) return true
        }
    return false
}
//上面函数的相反版本
context(info : MutableInformation,scope : LexicalScope)
infix fun TypeTree.isNotCastableTo(type : TypeTree) = !(this isCastableTo type)