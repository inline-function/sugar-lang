/**
 * 本文件定义语义分析过程及规则
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.16
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.semantic
import compiler.parser.ClassTree
import compiler.parser.CommonTypeTree
import compiler.parser.FunctionTree
import compiler.parser.VariableTree
import compiler.parser.ProjectTree as ProjectSyntaxTree
import compiler.parser.FileTree as FileSyntaxTree
import compiler.parser.TopTree as TopSyntaxTree
import compiler.parser.ClassTree as ClassSyntaxTree
import compiler.parser.TypeTree as TypeSyntaxTree
//typealias LexicalScope = MutableScope<InnerTreeBuilder<TopTree>>
//typealias LexicalRootScope = MutableScope<FileTreeBuilder>
//inline val rootScope : LexicalRootScope
//    get() = Scope()
//inline val MutableScope<*>.subScope : LexicalScope
//    get() = Scope(parent = this)
///**
// * 把语法阶段语法树进行语义分析,转换为语义阶段语法树(即抽象语法树)
// */
//@Suppress("NestedLambdaShadowedImplicitParameter")
//fun ProjectSyntaxTree.semanticAnalysis() = let {
//    Project(name = name) { val scope = rootScope
//        files.forEach {
//            scope += File(name = it.name) { val scope = scope.subScope
//            }
//        }
//    }
//}