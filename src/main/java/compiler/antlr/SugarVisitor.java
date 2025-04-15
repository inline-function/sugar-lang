// Generated from F:/JavaProjrct/sugerLang/docs/Sugar.g4 by ANTLR 4.13.2
package compiler.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SugarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SugarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SugarParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(SugarParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link SugarParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(SugarParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link SugarParser#top}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTop(SugarParser.TopContext ctx);
	/**
	 * Visit a parse tree produced by {@link SugarParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(SugarParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SugarParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(SugarParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SugarParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(SugarParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SugarParser#class}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass(SugarParser.ClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link SugarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(SugarParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SugarParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(SugarParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SugarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(SugarParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SugarParser#invoke_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvoke_(SugarParser.Invoke_Context ctx);
	/**
	 * Visit a parse tree produced by {@link SugarParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(SugarParser.NameContext ctx);
}