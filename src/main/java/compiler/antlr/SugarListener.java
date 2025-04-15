// Generated from F:/JavaProjrct/sugerLang/docs/Sugar.g4 by ANTLR 4.13.2
package compiler.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SugarParser}.
 */
public interface SugarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SugarParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(SugarParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link SugarParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(SugarParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link SugarParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(SugarParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link SugarParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(SugarParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link SugarParser#top}.
	 * @param ctx the parse tree
	 */
	void enterTop(SugarParser.TopContext ctx);
	/**
	 * Exit a parse tree produced by {@link SugarParser#top}.
	 * @param ctx the parse tree
	 */
	void exitTop(SugarParser.TopContext ctx);
	/**
	 * Enter a parse tree produced by {@link SugarParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(SugarParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SugarParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(SugarParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SugarParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(SugarParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SugarParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(SugarParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SugarParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(SugarParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SugarParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(SugarParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SugarParser#class}.
	 * @param ctx the parse tree
	 */
	void enterClass(SugarParser.ClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link SugarParser#class}.
	 * @param ctx the parse tree
	 */
	void exitClass(SugarParser.ClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link SugarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(SugarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SugarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(SugarParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SugarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(SugarParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SugarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(SugarParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SugarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(SugarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SugarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(SugarParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SugarParser#invoke_}.
	 * @param ctx the parse tree
	 */
	void enterInvoke_(SugarParser.Invoke_Context ctx);
	/**
	 * Exit a parse tree produced by {@link SugarParser#invoke_}.
	 * @param ctx the parse tree
	 */
	void exitInvoke_(SugarParser.Invoke_Context ctx);
	/**
	 * Enter a parse tree produced by {@link SugarParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(SugarParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SugarParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(SugarParser.NameContext ctx);
}