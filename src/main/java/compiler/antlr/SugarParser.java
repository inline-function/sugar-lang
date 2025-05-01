// Generated from F:/JavaProjrct/sugerLang/docs/Sugar.g4 by ANTLR 4.13.2
package compiler.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class SugarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		INT=10, DEC=11, STRING=12, ID=13, WS=14, IF=15, ELSE=16;
	public static final int
		RULE_file = 0, RULE_number = 1, RULE_top = 2, RULE_body = 3, RULE_variable = 4, 
		RULE_function = 5, RULE_class = 6, RULE_type = 7, RULE_stmt = 8, RULE_expr = 9, 
		RULE_if = 10, RULE_invoke = 11, RULE_name = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "number", "top", "body", "variable", "function", "class", "type", 
			"stmt", "expr", "if", "invoke", "name"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'='", "':'", "'('", "','", "')'", "'|>'", "'.'", 
			null, null, null, null, null, "'if'", "'else'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "INT", "DEC", 
			"STRING", "ID", "WS", "IF", "ELSE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Sugar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SugarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SugarParser.EOF, 0); }
		public List<TopContext> top() {
			return getRuleContexts(TopContext.class);
		}
		public TopContext top(int i) {
			return getRuleContext(TopContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(26);
				top();
				}
				}
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(32);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SugarParser.INT, 0); }
		public TerminalNode DEC() { return getToken(SugarParser.DEC, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==DEC) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TopContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public ClassContext class_() {
			return getRuleContext(ClassContext.class,0);
		}
		public TopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_top; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterTop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitTop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitTop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopContext top() throws RecognitionException {
		TopContext _localctx = new TopContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_top);
		try {
			setState(39);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
				function();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(38);
				class_();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BodyContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(T__0);
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 48160L) != 0)) {
				{
				{
				setState(42);
				stmt();
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SugarParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable);
		int _la;
		try {
			setState(60);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				match(ID);
				setState(51);
				match(T__2);
				setState(52);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				match(ID);
				setState(54);
				match(T__3);
				setState(55);
				type();
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(56);
					match(T__2);
					setState(57);
					expr();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SugarParser.ID, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(ID);
			setState(63);
			match(T__4);
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(64);
				variable();
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__5) {
					{
					{
					setState(65);
					match(T__5);
					setState(66);
					variable();
					}
					}
					setState(71);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(74);
			match(T__6);
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(75);
				match(T__3);
				setState(76);
				type();
				}
			}

			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(79);
				body();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SugarParser.ID, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public ClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassContext class_() throws RecognitionException {
		ClassContext _localctx = new ClassContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_class);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(ID);
			setState(85); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(83);
				match(T__7);
				setState(84);
				type();
				}
				}
				setState(87); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__7 );
			setState(89);
			match(T__0);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				setState(92);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(90);
					variable();
					}
					break;
				case 2:
					{
					setState(91);
					function();
					}
					break;
				}
				}
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(97);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SugarParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		try {
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				match(ID);
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				match(T__4);
				setState(101);
				type();
				setState(102);
				match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TopContext top() {
			return getRuleContext(TopContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_stmt);
		try {
			setState(108);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case INT:
			case DEC:
			case STRING:
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				expr();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				top();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode STRING() { return getToken(SugarParser.STRING, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IfContext if_() {
			return getRuleContext(IfContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public InvokeContext invoke() {
			return getRuleContext(InvokeContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case DEC:
				{
				setState(110);
				number();
				}
				break;
			case STRING:
				{
				setState(111);
				match(STRING);
				}
				break;
			case T__4:
				{
				setState(112);
				match(T__4);
				setState(113);
				expr();
				setState(114);
				match(T__6);
				}
				break;
			case IF:
				{
				setState(116);
				if_();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(119);
				name();
				}
			}

			setState(123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(122);
				invoke();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(SugarParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<BodyContext> body() {
			return getRuleContexts(BodyContext.class);
		}
		public BodyContext body(int i) {
			return getRuleContext(BodyContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(SugarParser.ELSE, 0); }
		public IfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfContext if_() throws RecognitionException {
		IfContext _localctx = new IfContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_if);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(IF);
			setState(126);
			expr();
			setState(127);
			body();
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(128);
				match(ELSE);
				setState(129);
				body();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InvokeContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public InvokeContext invoke() {
			return getRuleContext(InvokeContext.class,0);
		}
		public InvokeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invoke; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterInvoke(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitInvoke(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitInvoke(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InvokeContext invoke() throws RecognitionException {
		InvokeContext _localctx = new InvokeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_invoke);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(T__4);
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 39968L) != 0)) {
				{
				setState(133);
				expr();
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__5) {
					{
					{
					setState(134);
					match(T__5);
					setState(135);
					expr();
					}
					}
					setState(140);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(143);
			match(T__6);
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(144);
				name();
				}
			}

			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(147);
				invoke();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SugarParser.ID, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public InvokeContext invoke() {
			return getRuleContext(InvokeContext.class,0);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(T__8);
			setState(151);
			match(ID);
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(152);
				name();
				}
			}

			setState(156);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(155);
				invoke();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0010\u009f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0001\u0000\u0005\u0000\u001c\b\u0000\n\u0000\f\u0000"+
		"\u001f\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002(\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0005\u0003,\b\u0003\n\u0003\f\u0003/\t\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004;\b\u0004\u0003\u0004=\b\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005D\b"+
		"\u0005\n\u0005\f\u0005G\t\u0005\u0003\u0005I\b\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005N\b\u0005\u0001\u0005\u0003\u0005Q\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0004\u0006V\b\u0006\u000b\u0006"+
		"\f\u0006W\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006]\b\u0006\n\u0006"+
		"\f\u0006`\t\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007i\b\u0007\u0001\b\u0001\b\u0003"+
		"\bm\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\tv\b\t\u0001\t\u0003\ty\b\t\u0001\t\u0003\t|\b\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0003\n\u0083\b\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0005\u000b\u0089\b\u000b\n\u000b\f\u000b\u008c\t\u000b\u0003"+
		"\u000b\u008e\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0092\b\u000b"+
		"\u0001\u000b\u0003\u000b\u0095\b\u000b\u0001\f\u0001\f\u0001\f\u0003\f"+
		"\u009a\b\f\u0001\f\u0003\f\u009d\b\f\u0001\f\u0000\u0000\r\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u0000\u0001\u0001"+
		"\u0000\n\u000b\u00ac\u0000\u001d\u0001\u0000\u0000\u0000\u0002\"\u0001"+
		"\u0000\u0000\u0000\u0004\'\u0001\u0000\u0000\u0000\u0006)\u0001\u0000"+
		"\u0000\u0000\b<\u0001\u0000\u0000\u0000\n>\u0001\u0000\u0000\u0000\fR"+
		"\u0001\u0000\u0000\u0000\u000eh\u0001\u0000\u0000\u0000\u0010l\u0001\u0000"+
		"\u0000\u0000\u0012u\u0001\u0000\u0000\u0000\u0014}\u0001\u0000\u0000\u0000"+
		"\u0016\u0084\u0001\u0000\u0000\u0000\u0018\u0096\u0001\u0000\u0000\u0000"+
		"\u001a\u001c\u0003\u0004\u0002\u0000\u001b\u001a\u0001\u0000\u0000\u0000"+
		"\u001c\u001f\u0001\u0000\u0000\u0000\u001d\u001b\u0001\u0000\u0000\u0000"+
		"\u001d\u001e\u0001\u0000\u0000\u0000\u001e \u0001\u0000\u0000\u0000\u001f"+
		"\u001d\u0001\u0000\u0000\u0000 !\u0005\u0000\u0000\u0001!\u0001\u0001"+
		"\u0000\u0000\u0000\"#\u0007\u0000\u0000\u0000#\u0003\u0001\u0000\u0000"+
		"\u0000$(\u0003\b\u0004\u0000%(\u0003\n\u0005\u0000&(\u0003\f\u0006\u0000"+
		"\'$\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000\'&\u0001\u0000"+
		"\u0000\u0000(\u0005\u0001\u0000\u0000\u0000)-\u0005\u0001\u0000\u0000"+
		"*,\u0003\u0010\b\u0000+*\u0001\u0000\u0000\u0000,/\u0001\u0000\u0000\u0000"+
		"-+\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000.0\u0001\u0000\u0000"+
		"\u0000/-\u0001\u0000\u0000\u000001\u0005\u0002\u0000\u00001\u0007\u0001"+
		"\u0000\u0000\u000023\u0005\r\u0000\u000034\u0005\u0003\u0000\u00004=\u0003"+
		"\u0012\t\u000056\u0005\r\u0000\u000067\u0005\u0004\u0000\u00007:\u0003"+
		"\u000e\u0007\u000089\u0005\u0003\u0000\u00009;\u0003\u0012\t\u0000:8\u0001"+
		"\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000;=\u0001\u0000\u0000\u0000"+
		"<2\u0001\u0000\u0000\u0000<5\u0001\u0000\u0000\u0000=\t\u0001\u0000\u0000"+
		"\u0000>?\u0005\r\u0000\u0000?H\u0005\u0005\u0000\u0000@E\u0003\b\u0004"+
		"\u0000AB\u0005\u0006\u0000\u0000BD\u0003\b\u0004\u0000CA\u0001\u0000\u0000"+
		"\u0000DG\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000EF\u0001\u0000"+
		"\u0000\u0000FI\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000H@\u0001"+
		"\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000"+
		"JM\u0005\u0007\u0000\u0000KL\u0005\u0004\u0000\u0000LN\u0003\u000e\u0007"+
		"\u0000MK\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000NP\u0001\u0000"+
		"\u0000\u0000OQ\u0003\u0006\u0003\u0000PO\u0001\u0000\u0000\u0000PQ\u0001"+
		"\u0000\u0000\u0000Q\u000b\u0001\u0000\u0000\u0000RU\u0005\r\u0000\u0000"+
		"ST\u0005\b\u0000\u0000TV\u0003\u000e\u0007\u0000US\u0001\u0000\u0000\u0000"+
		"VW\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000"+
		"\u0000XY\u0001\u0000\u0000\u0000Y^\u0005\u0001\u0000\u0000Z]\u0003\b\u0004"+
		"\u0000[]\u0003\n\u0005\u0000\\Z\u0001\u0000\u0000\u0000\\[\u0001\u0000"+
		"\u0000\u0000]`\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000^_\u0001"+
		"\u0000\u0000\u0000_a\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000"+
		"ab\u0005\u0002\u0000\u0000b\r\u0001\u0000\u0000\u0000ci\u0005\r\u0000"+
		"\u0000de\u0005\u0005\u0000\u0000ef\u0003\u000e\u0007\u0000fg\u0005\u0007"+
		"\u0000\u0000gi\u0001\u0000\u0000\u0000hc\u0001\u0000\u0000\u0000hd\u0001"+
		"\u0000\u0000\u0000i\u000f\u0001\u0000\u0000\u0000jm\u0003\u0012\t\u0000"+
		"km\u0003\u0004\u0002\u0000lj\u0001\u0000\u0000\u0000lk\u0001\u0000\u0000"+
		"\u0000m\u0011\u0001\u0000\u0000\u0000nv\u0003\u0002\u0001\u0000ov\u0005"+
		"\f\u0000\u0000pq\u0005\u0005\u0000\u0000qr\u0003\u0012\t\u0000rs\u0005"+
		"\u0007\u0000\u0000sv\u0001\u0000\u0000\u0000tv\u0003\u0014\n\u0000un\u0001"+
		"\u0000\u0000\u0000uo\u0001\u0000\u0000\u0000up\u0001\u0000\u0000\u0000"+
		"ut\u0001\u0000\u0000\u0000vx\u0001\u0000\u0000\u0000wy\u0003\u0018\f\u0000"+
		"xw\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000y{\u0001\u0000\u0000"+
		"\u0000z|\u0003\u0016\u000b\u0000{z\u0001\u0000\u0000\u0000{|\u0001\u0000"+
		"\u0000\u0000|\u0013\u0001\u0000\u0000\u0000}~\u0005\u000f\u0000\u0000"+
		"~\u007f\u0003\u0012\t\u0000\u007f\u0082\u0003\u0006\u0003\u0000\u0080"+
		"\u0081\u0005\u0010\u0000\u0000\u0081\u0083\u0003\u0006\u0003\u0000\u0082"+
		"\u0080\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083"+
		"\u0015\u0001\u0000\u0000\u0000\u0084\u008d\u0005\u0005\u0000\u0000\u0085"+
		"\u008a\u0003\u0012\t\u0000\u0086\u0087\u0005\u0006\u0000\u0000\u0087\u0089"+
		"\u0003\u0012\t\u0000\u0088\u0086\u0001\u0000\u0000\u0000\u0089\u008c\u0001"+
		"\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008a\u008b\u0001"+
		"\u0000\u0000\u0000\u008b\u008e\u0001\u0000\u0000\u0000\u008c\u008a\u0001"+
		"\u0000\u0000\u0000\u008d\u0085\u0001\u0000\u0000\u0000\u008d\u008e\u0001"+
		"\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0091\u0005"+
		"\u0007\u0000\u0000\u0090\u0092\u0003\u0018\f\u0000\u0091\u0090\u0001\u0000"+
		"\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0094\u0001\u0000"+
		"\u0000\u0000\u0093\u0095\u0003\u0016\u000b\u0000\u0094\u0093\u0001\u0000"+
		"\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0017\u0001\u0000"+
		"\u0000\u0000\u0096\u0097\u0005\t\u0000\u0000\u0097\u0099\u0005\r\u0000"+
		"\u0000\u0098\u009a\u0003\u0018\f\u0000\u0099\u0098\u0001\u0000\u0000\u0000"+
		"\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u009c\u0001\u0000\u0000\u0000"+
		"\u009b\u009d\u0003\u0016\u000b\u0000\u009c\u009b\u0001\u0000\u0000\u0000"+
		"\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u0019\u0001\u0000\u0000\u0000"+
		"\u0018\u001d\'-:<EHMPW\\^hlux{\u0082\u008a\u008d\u0091\u0094\u0099\u009c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}