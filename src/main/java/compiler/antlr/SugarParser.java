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
		T__9=10, T__10=11, T__11=12, T__12=13, ELSE=14, VAL=15, VAR=16, FUN=17, 
		CLASS=18, NL=19, INT=20, DEC=21, STRING=22, ID=23, WS=24;
	public static final int
		RULE_file = 0, RULE_annotation = 1, RULE_modifier = 2, RULE_number = 3, 
		RULE_top = 4, RULE_body = 5, RULE_typeParamList = 6, RULE_typeArgList = 7, 
		RULE_variable = 8, RULE_parameter = 9, RULE_function = 10, RULE_class = 11, 
		RULE_type = 12, RULE_applyType = 13, RULE_nullableType = 14, RULE_functionType = 15, 
		RULE_stmt = 16, RULE_expr = 17, RULE_lambda = 18, RULE_invoke = 19, RULE_name = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "annotation", "modifier", "number", "top", "body", "typeParamList", 
			"typeArgList", "variable", "parameter", "function", "class", "type", 
			"applyType", "nullableType", "functionType", "stmt", "expr", "lambda", 
			"invoke", "name"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'@'", "'('", "')'", "'{'", "'}'", "'<'", "','", "'>'", "'='", 
			"':'", "'?'", "'=>'", "'.'", "'else'", "'val'", "'var'", "'fun'", "'class'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "ELSE", "VAL", "VAR", "FUN", "CLASS", "NL", "INT", "DEC", 
			"STRING", "ID", "WS"
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
		public List<TopContext> top() {
			return getRuleContexts(TopContext.class);
		}
		public TopContext top(int i) {
			return getRuleContext(TopContext.class,i);
		}
		public TerminalNode EOF() { return getToken(SugarParser.EOF, 0); }
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
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
			setState(43);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(42);
				match(NL);
				}
				break;
			}
			setState(45);
			top();
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(46);
				match(NL);
				setState(47);
				top();
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
			match(EOF);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(54);
				match(NL);
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
	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SugarParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(T__0);
			setState(58);
			match(ID);
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(59);
				match(T__1);
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(60);
					match(NL);
					}
				}

				setState(63);
				expr();
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(64);
					match(NL);
					}
				}

				setState(67);
				match(T__2);
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
	public static class ModifierContext extends ParserRuleContext {
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
		}
		public List<TerminalNode> ID() { return getTokens(SugarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SugarParser.ID, i);
		}
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_modifier);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(72);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(71);
						match(NL);
						}
					}

					setState(74);
					annotation();
					}
					} 
				}
				setState(79);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(80);
				match(NL);
				}
			}

			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(83);
				match(ID);
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
		enterRule(_localctx, 6, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
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
		enterRule(_localctx, 8, RULE_top);
		try {
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				function();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
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
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
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
		enterRule(_localctx, 10, RULE_body);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__3);
			setState(98);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(97);
				match(NL);
				}
				break;
			}
			setState(100);
			stmt();
			setState(105);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(101);
					match(NL);
					setState(102);
					stmt();
					}
					} 
				}
				setState(107);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(108);
				match(NL);
				}
			}

			setState(111);
			match(T__4);
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
	public static class TypeParamListContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SugarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SugarParser.ID, i);
		}
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
		}
		public TypeParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParamList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterTypeParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitTypeParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitTypeParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParamListContext typeParamList() throws RecognitionException {
		TypeParamListContext _localctx = new TypeParamListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_typeParamList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(T__5);
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(114);
				match(NL);
				}
			}

			setState(117);
			match(ID);
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6 || _la==NL) {
				{
				{
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(118);
					match(NL);
					}
				}

				setState(121);
				match(T__6);
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(122);
					match(NL);
					}
				}

				setState(125);
				match(ID);
				}
				}
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(131);
			match(T__7);
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
	public static class TypeArgListContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
		}
		public TypeArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterTypeArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitTypeArgList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitTypeArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeArgListContext typeArgList() throws RecognitionException {
		TypeArgListContext _localctx = new TypeArgListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_typeArgList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(T__5);
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(134);
				match(NL);
				}
			}

			setState(137);
			type();
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6 || _la==NL) {
				{
				{
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(138);
					match(NL);
					}
				}

				setState(141);
				match(T__6);
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(142);
					match(NL);
					}
				}

				setState(145);
				type();
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(151);
			match(T__7);
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
		public TerminalNode VAL() { return getToken(SugarParser.VAL, 0); }
		public TerminalNode VAR() { return getToken(SugarParser.VAR, 0); }
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
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
		enterRule(_localctx, 16, RULE_variable);
		int _la;
		try {
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(154);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(153);
					modifier();
					}
					break;
				}
				setState(156);
				_la = _input.LA(1);
				if ( !(_la==VAL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(157);
				match(ID);
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(158);
					match(NL);
					}
				}

				setState(161);
				match(T__8);
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(162);
					match(NL);
					}
				}

				setState(165);
				expr();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(167);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(166);
					modifier();
					}
					break;
				}
				setState(169);
				_la = _input.LA(1);
				if ( !(_la==VAL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(170);
				match(ID);
				setState(171);
				match(T__9);
				setState(172);
				type();
				setState(181);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(174);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(173);
						match(NL);
						}
					}

					setState(176);
					match(T__8);
					setState(178);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(177);
						match(NL);
						}
					}

					setState(180);
					expr();
					}
					break;
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
	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SugarParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(185);
				match(NL);
				}
			}

			setState(188);
			match(ID);
			setState(189);
			match(T__9);
			setState(190);
			type();
			setState(199);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(191);
					match(NL);
					}
				}

				setState(194);
				match(T__8);
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(195);
					match(NL);
					}
				}

				setState(198);
				expr();
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
	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode FUN() { return getToken(SugarParser.FUN, 0); }
		public TerminalNode ID() { return getToken(SugarParser.ID, 0); }
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public TypeParamListContext typeParamList() {
			return getRuleContext(TypeParamListContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
		}
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
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
		enterRule(_localctx, 20, RULE_function);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(201);
				modifier();
				}
				break;
			}
			setState(204);
			match(FUN);
			setState(205);
			match(ID);
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(206);
				typeParamList();
				}
			}

			setState(209);
			match(T__1);
			setState(211);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(210);
				match(NL);
				}
				break;
			}
			setState(230);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(214);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					{
					setState(213);
					match(NL);
					}
					break;
				}
				setState(216);
				parameter();
				setState(227);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(218);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(217);
							match(NL);
							}
						}

						setState(220);
						match(T__6);
						setState(222);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
						case 1:
							{
							setState(221);
							match(NL);
							}
							break;
						}
						setState(224);
						parameter();
						}
						} 
					}
					setState(229);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
				}
				}
				break;
			}
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(232);
				match(NL);
				}
			}

			setState(235);
			match(T__2);
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(236);
				match(T__9);
				setState(237);
				type();
				}
			}

			setState(241);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				{
				setState(240);
				match(NL);
				}
				break;
			}
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(243);
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
		public TerminalNode CLASS() { return getToken(SugarParser.CLASS, 0); }
		public TerminalNode ID() { return getToken(SugarParser.ID, 0); }
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public TypeParamListContext typeParamList() {
			return getRuleContext(TypeParamListContext.class,0);
		}
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
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
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
		enterRule(_localctx, 22, RULE_class);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(246);
				modifier();
				}
				break;
			}
			setState(249);
			match(CLASS);
			setState(250);
			match(ID);
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(251);
				typeParamList();
				}
			}

			setState(275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(254);
					match(NL);
					}
				}

				setState(257);
				match(T__9);
				{
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(258);
					match(NL);
					}
				}

				setState(261);
				type();
				setState(272);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(263);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(262);
							match(NL);
							}
						}

						setState(265);
						match(T__6);
						setState(267);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(266);
							match(NL);
							}
						}

						setState(269);
						type();
						}
						} 
					}
					setState(274);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
				}
				}
				}
				break;
			}
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(277);
				match(T__3);
				setState(279);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
				case 1:
					{
					setState(278);
					match(NL);
					}
					break;
				}
				setState(283);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
				case 1:
					{
					setState(281);
					variable();
					}
					break;
				case 2:
					{
					setState(282);
					function();
					}
					break;
				}
				setState(290);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(288);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
						case 1:
							{
							setState(285);
							match(NL);
							setState(286);
							variable();
							}
							break;
						case 2:
							{
							setState(287);
							function();
							}
							break;
						}
						} 
					}
					setState(292);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
				}
				setState(294);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(293);
					match(NL);
					}
				}

				setState(296);
				match(T__4);
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
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SugarParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FunctionTypeContext functionType() {
			return getRuleContext(FunctionTypeContext.class,0);
		}
		public ApplyTypeContext applyType() {
			return getRuleContext(ApplyTypeContext.class,0);
		}
		public NullableTypeContext nullableType() {
			return getRuleContext(NullableTypeContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
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
		enterRule(_localctx, 24, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				{
				setState(300);
				match(ID);
				}
				break;
			case 2:
				{
				setState(301);
				match(T__1);
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(302);
					match(NL);
					}
				}

				setState(305);
				type();
				setState(307);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(306);
					match(NL);
					}
				}

				setState(309);
				match(T__2);
				}
				break;
			case 3:
				{
				setState(311);
				functionType();
				}
				break;
			case 4:
				{
				setState(312);
				applyType();
				}
				break;
			}
			setState(316);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
			case 1:
				{
				setState(315);
				nullableType();
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
	public static class ApplyTypeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SugarParser.ID, 0); }
		public TypeArgListContext typeArgList() {
			return getRuleContext(TypeArgListContext.class,0);
		}
		public ApplyTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_applyType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterApplyType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitApplyType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitApplyType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplyTypeContext applyType() throws RecognitionException {
		ApplyTypeContext _localctx = new ApplyTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_applyType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			match(ID);
			setState(319);
			typeArgList();
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
	public static class NullableTypeContext extends ParserRuleContext {
		public NullableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nullableType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterNullableType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitNullableType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitNullableType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NullableTypeContext nullableType() throws RecognitionException {
		NullableTypeContext _localctx = new NullableTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_nullableType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			match(T__10);
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
	public static class FunctionTypeContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
		}
		public FunctionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterFunctionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitFunctionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitFunctionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionTypeContext functionType() throws RecognitionException {
		FunctionTypeContext _localctx = new FunctionTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_functionType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			match(T__1);
			setState(341);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8912900L) != 0)) {
				{
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(324);
					match(NL);
					}
				}

				setState(327);
				type();
				setState(338);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__6 || _la==NL) {
					{
					{
					setState(329);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(328);
						match(NL);
						}
					}

					setState(331);
					match(T__6);
					setState(333);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(332);
						match(NL);
						}
					}

					setState(335);
					type();
					}
					}
					setState(340);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(343);
			match(T__2);
			setState(344);
			match(T__11);
			setState(346);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(345);
				match(NL);
				}
			}

			setState(348);
			type();
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
		enterRule(_localctx, 32, RULE_stmt);
		try {
			setState(352);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(350);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(351);
				top();
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
	public static class ExprContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode STRING() { return getToken(SugarParser.STRING, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(SugarParser.ID, 0); }
		public LambdaContext lambda() {
			return getRuleContext(LambdaContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public InvokeContext invoke() {
			return getRuleContext(InvokeContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
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
		enterRule(_localctx, 34, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case DEC:
				{
				setState(354);
				number();
				}
				break;
			case STRING:
				{
				setState(355);
				match(STRING);
				}
				break;
			case T__1:
				{
				setState(356);
				match(T__1);
				setState(358);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(357);
					match(NL);
					}
				}

				setState(360);
				expr();
				setState(362);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(361);
					match(NL);
					}
				}

				setState(364);
				match(T__2);
				}
				break;
			case ID:
				{
				setState(366);
				match(ID);
				}
				break;
			case T__3:
				{
				setState(367);
				lambda();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(371);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				{
				setState(370);
				name();
				}
				break;
			}
			setState(374);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1 || _la==T__5) {
				{
				setState(373);
				invoke();
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
	public static class LambdaContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(SugarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SugarParser.ID, i);
		}
		public LambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).enterLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SugarListener ) ((SugarListener)listener).exitLambda(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SugarVisitor ) return ((SugarVisitor<? extends T>)visitor).visitLambda(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaContext lambda() throws RecognitionException {
		LambdaContext _localctx = new LambdaContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_lambda);
		int _la;
		try {
			int _alt;
			setState(460);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(376);
				match(T__3);
				setState(378);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
				case 1:
					{
					setState(377);
					match(NL);
					}
					break;
				}
				setState(380);
				parameter();
				setState(391);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(382);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(381);
							match(NL);
							}
						}

						setState(384);
						match(T__6);
						setState(386);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
						case 1:
							{
							setState(385);
							match(NL);
							}
							break;
						}
						setState(388);
						parameter();
						}
						} 
					}
					setState(393);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
				}
				setState(395);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(394);
					match(NL);
					}
				}

				setState(397);
				match(T__11);
				setState(399);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					setState(398);
					match(NL);
					}
					break;
				}
				setState(402);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
				case 1:
					{
					setState(401);
					stmt();
					}
					break;
				}
				setState(408);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(404);
						match(NL);
						setState(405);
						stmt();
						}
						} 
					}
					setState(410);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
				}
				setState(412);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(411);
					match(NL);
					}
				}

				setState(414);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(416);
				match(T__3);
				setState(418);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
				case 1:
					{
					setState(417);
					match(NL);
					}
					break;
				}
				setState(441);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
				case 1:
					{
					setState(421);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(420);
						match(NL);
						}
					}

					setState(423);
					match(ID);
					setState(434);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(425);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==NL) {
								{
								setState(424);
								match(NL);
								}
							}

							setState(427);
							match(T__6);
							setState(429);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==NL) {
								{
								setState(428);
								match(NL);
								}
							}

							setState(431);
							match(ID);
							}
							} 
						}
						setState(436);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
					}
					setState(438);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(437);
						match(NL);
						}
					}

					setState(440);
					match(T__11);
					}
					break;
				}
				setState(444);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
				case 1:
					{
					setState(443);
					match(NL);
					}
					break;
				}
				setState(447);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
				case 1:
					{
					setState(446);
					stmt();
					}
					break;
				}
				setState(453);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(449);
						match(NL);
						setState(450);
						stmt();
						}
						} 
					}
					setState(455);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
				}
				setState(457);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(456);
					match(NL);
					}
				}

				setState(459);
				match(T__4);
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
	public static class InvokeContext extends ParserRuleContext {
		public TypeArgListContext typeArgList() {
			return getRuleContext(TypeArgListContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(SugarParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(SugarParser.NL, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public InvokeContext invoke() {
			return getRuleContext(InvokeContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
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
		enterRule(_localctx, 38, RULE_invoke);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(462);
				typeArgList();
				}
			}

			setState(465);
			match(T__1);
			setState(467);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
			case 1:
				{
				setState(466);
				match(NL);
				}
				break;
			}
			setState(486);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
			case 1:
				{
				setState(470);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(469);
					match(NL);
					}
				}

				setState(472);
				expr();
				setState(483);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(474);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(473);
							match(NL);
							}
						}

						setState(476);
						match(T__6);
						setState(478);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(477);
							match(NL);
							}
						}

						setState(480);
						expr();
						}
						} 
					}
					setState(485);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
				}
				}
				break;
			}
			setState(489);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(488);
				match(NL);
				}
			}

			setState(491);
			match(T__2);
			setState(493);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
			case 1:
				{
				setState(492);
				invoke();
				}
				break;
			}
			setState(496);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
			case 1:
				{
				setState(495);
				name();
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
		public TerminalNode NL() { return getToken(SugarParser.NL, 0); }
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
		enterRule(_localctx, 40, RULE_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(499);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(498);
				match(NL);
				}
			}

			setState(501);
			match(T__12);
			setState(502);
			match(ID);
			setState(504);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
			case 1:
				{
				setState(503);
				name();
				}
				break;
			}
			setState(507);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
			case 1:
				{
				setState(506);
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
		"\u0004\u0001\u0018\u01fe\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001\u0000\u0003"+
		"\u0000,\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u00001\b\u0000"+
		"\n\u0000\f\u00004\t\u0000\u0001\u0000\u0001\u0000\u0003\u00008\b\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001>\b\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001B\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001F\b\u0001\u0001\u0002\u0003\u0002I\b\u0002\u0001\u0002\u0005"+
		"\u0002L\b\u0002\n\u0002\f\u0002O\t\u0002\u0001\u0002\u0003\u0002R\b\u0002"+
		"\u0001\u0002\u0005\u0002U\b\u0002\n\u0002\f\u0002X\t\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004_\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0003\u0005c\b\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0005\u0005h\b\u0005\n\u0005\f\u0005k\t\u0005\u0001\u0005"+
		"\u0003\u0005n\b\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0003\u0006t\b\u0006\u0001\u0006\u0001\u0006\u0003\u0006x\b\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006|\b\u0006\u0001\u0006\u0005\u0006\u007f"+
		"\b\u0006\n\u0006\f\u0006\u0082\t\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u0088\b\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"\u008c\b\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0090\b\u0007\u0001"+
		"\u0007\u0005\u0007\u0093\b\u0007\n\u0007\f\u0007\u0096\t\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0003\b\u009b\b\b\u0001\b\u0001\b\u0001\b\u0003\b"+
		"\u00a0\b\b\u0001\b\u0001\b\u0003\b\u00a4\b\b\u0001\b\u0001\b\u0003\b\u00a8"+
		"\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u00af\b\b\u0001\b"+
		"\u0001\b\u0003\b\u00b3\b\b\u0001\b\u0003\b\u00b6\b\b\u0003\b\u00b8\b\b"+
		"\u0001\t\u0003\t\u00bb\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00c1"+
		"\b\t\u0001\t\u0001\t\u0003\t\u00c5\b\t\u0001\t\u0003\t\u00c8\b\t\u0001"+
		"\n\u0003\n\u00cb\b\n\u0001\n\u0001\n\u0001\n\u0003\n\u00d0\b\n\u0001\n"+
		"\u0001\n\u0003\n\u00d4\b\n\u0001\n\u0003\n\u00d7\b\n\u0001\n\u0001\n\u0003"+
		"\n\u00db\b\n\u0001\n\u0001\n\u0003\n\u00df\b\n\u0001\n\u0005\n\u00e2\b"+
		"\n\n\n\f\n\u00e5\t\n\u0003\n\u00e7\b\n\u0001\n\u0003\n\u00ea\b\n\u0001"+
		"\n\u0001\n\u0001\n\u0003\n\u00ef\b\n\u0001\n\u0003\n\u00f2\b\n\u0001\n"+
		"\u0003\n\u00f5\b\n\u0001\u000b\u0003\u000b\u00f8\b\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00fd\b\u000b\u0001\u000b\u0003\u000b\u0100"+
		"\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0104\b\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u0108\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u010c\b\u000b\u0001\u000b\u0005\u000b\u010f\b\u000b\n\u000b\f\u000b\u0112"+
		"\t\u000b\u0003\u000b\u0114\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u0118\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u011c\b\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u0121\b\u000b\n\u000b\f\u000b"+
		"\u0124\t\u000b\u0001\u000b\u0003\u000b\u0127\b\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u012b\b\u000b\u0001\f\u0001\f\u0001\f\u0003\f\u0130"+
		"\b\f\u0001\f\u0001\f\u0003\f\u0134\b\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0003\f\u013a\b\f\u0001\f\u0003\f\u013d\b\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u0146\b\u000f\u0001"+
		"\u000f\u0001\u000f\u0003\u000f\u014a\b\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u014e\b\u000f\u0001\u000f\u0005\u000f\u0151\b\u000f\n\u000f\f\u000f"+
		"\u0154\t\u000f\u0003\u000f\u0156\b\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0003\u000f\u015b\b\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0003\u0010\u0161\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0003\u0011\u0167\b\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u016b"+
		"\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0171"+
		"\b\u0011\u0001\u0011\u0003\u0011\u0174\b\u0011\u0001\u0011\u0003\u0011"+
		"\u0177\b\u0011\u0001\u0012\u0001\u0012\u0003\u0012\u017b\b\u0012\u0001"+
		"\u0012\u0001\u0012\u0003\u0012\u017f\b\u0012\u0001\u0012\u0001\u0012\u0003"+
		"\u0012\u0183\b\u0012\u0001\u0012\u0005\u0012\u0186\b\u0012\n\u0012\f\u0012"+
		"\u0189\t\u0012\u0001\u0012\u0003\u0012\u018c\b\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u0190\b\u0012\u0001\u0012\u0003\u0012\u0193\b\u0012"+
		"\u0001\u0012\u0001\u0012\u0005\u0012\u0197\b\u0012\n\u0012\f\u0012\u019a"+
		"\t\u0012\u0001\u0012\u0003\u0012\u019d\b\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0003\u0012\u01a3\b\u0012\u0001\u0012\u0003\u0012"+
		"\u01a6\b\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u01aa\b\u0012\u0001"+
		"\u0012\u0001\u0012\u0003\u0012\u01ae\b\u0012\u0001\u0012\u0005\u0012\u01b1"+
		"\b\u0012\n\u0012\f\u0012\u01b4\t\u0012\u0001\u0012\u0003\u0012\u01b7\b"+
		"\u0012\u0001\u0012\u0003\u0012\u01ba\b\u0012\u0001\u0012\u0003\u0012\u01bd"+
		"\b\u0012\u0001\u0012\u0003\u0012\u01c0\b\u0012\u0001\u0012\u0001\u0012"+
		"\u0005\u0012\u01c4\b\u0012\n\u0012\f\u0012\u01c7\t\u0012\u0001\u0012\u0003"+
		"\u0012\u01ca\b\u0012\u0001\u0012\u0003\u0012\u01cd\b\u0012\u0001\u0013"+
		"\u0003\u0013\u01d0\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u01d4\b"+
		"\u0013\u0001\u0013\u0003\u0013\u01d7\b\u0013\u0001\u0013\u0001\u0013\u0003"+
		"\u0013\u01db\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u01df\b\u0013"+
		"\u0001\u0013\u0005\u0013\u01e2\b\u0013\n\u0013\f\u0013\u01e5\t\u0013\u0003"+
		"\u0013\u01e7\b\u0013\u0001\u0013\u0003\u0013\u01ea\b\u0013\u0001\u0013"+
		"\u0001\u0013\u0003\u0013\u01ee\b\u0013\u0001\u0013\u0003\u0013\u01f1\b"+
		"\u0013\u0001\u0014\u0003\u0014\u01f4\b\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0003\u0014\u01f9\b\u0014\u0001\u0014\u0003\u0014\u01fc\b\u0014"+
		"\u0001\u0014\u0000\u0000\u0015\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(\u0000\u0002\u0001\u0000"+
		"\u0014\u0015\u0001\u0000\u000f\u0010\u025c\u0000+\u0001\u0000\u0000\u0000"+
		"\u00029\u0001\u0000\u0000\u0000\u0004M\u0001\u0000\u0000\u0000\u0006Y"+
		"\u0001\u0000\u0000\u0000\b^\u0001\u0000\u0000\u0000\n`\u0001\u0000\u0000"+
		"\u0000\fq\u0001\u0000\u0000\u0000\u000e\u0085\u0001\u0000\u0000\u0000"+
		"\u0010\u00b7\u0001\u0000\u0000\u0000\u0012\u00ba\u0001\u0000\u0000\u0000"+
		"\u0014\u00ca\u0001\u0000\u0000\u0000\u0016\u00f7\u0001\u0000\u0000\u0000"+
		"\u0018\u0139\u0001\u0000\u0000\u0000\u001a\u013e\u0001\u0000\u0000\u0000"+
		"\u001c\u0141\u0001\u0000\u0000\u0000\u001e\u0143\u0001\u0000\u0000\u0000"+
		" \u0160\u0001\u0000\u0000\u0000\"\u0170\u0001\u0000\u0000\u0000$\u01cc"+
		"\u0001\u0000\u0000\u0000&\u01cf\u0001\u0000\u0000\u0000(\u01f3\u0001\u0000"+
		"\u0000\u0000*,\u0005\u0013\u0000\u0000+*\u0001\u0000\u0000\u0000+,\u0001"+
		"\u0000\u0000\u0000,-\u0001\u0000\u0000\u0000-2\u0003\b\u0004\u0000./\u0005"+
		"\u0013\u0000\u0000/1\u0003\b\u0004\u00000.\u0001\u0000\u0000\u000014\u0001"+
		"\u0000\u0000\u000020\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u0000"+
		"35\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u000057\u0005\u0000\u0000"+
		"\u000168\u0005\u0013\u0000\u000076\u0001\u0000\u0000\u000078\u0001\u0000"+
		"\u0000\u00008\u0001\u0001\u0000\u0000\u00009:\u0005\u0001\u0000\u0000"+
		":E\u0005\u0017\u0000\u0000;=\u0005\u0002\u0000\u0000<>\u0005\u0013\u0000"+
		"\u0000=<\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>?\u0001\u0000"+
		"\u0000\u0000?A\u0003\"\u0011\u0000@B\u0005\u0013\u0000\u0000A@\u0001\u0000"+
		"\u0000\u0000AB\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000CD\u0005"+
		"\u0003\u0000\u0000DF\u0001\u0000\u0000\u0000E;\u0001\u0000\u0000\u0000"+
		"EF\u0001\u0000\u0000\u0000F\u0003\u0001\u0000\u0000\u0000GI\u0005\u0013"+
		"\u0000\u0000HG\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000IJ\u0001"+
		"\u0000\u0000\u0000JL\u0003\u0002\u0001\u0000KH\u0001\u0000\u0000\u0000"+
		"LO\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000"+
		"\u0000NQ\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000PR\u0005\u0013"+
		"\u0000\u0000QP\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RV\u0001"+
		"\u0000\u0000\u0000SU\u0005\u0017\u0000\u0000TS\u0001\u0000\u0000\u0000"+
		"UX\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000"+
		"\u0000W\u0005\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000YZ\u0007"+
		"\u0000\u0000\u0000Z\u0007\u0001\u0000\u0000\u0000[_\u0003\u0010\b\u0000"+
		"\\_\u0003\u0014\n\u0000]_\u0003\u0016\u000b\u0000^[\u0001\u0000\u0000"+
		"\u0000^\\\u0001\u0000\u0000\u0000^]\u0001\u0000\u0000\u0000_\t\u0001\u0000"+
		"\u0000\u0000`b\u0005\u0004\u0000\u0000ac\u0005\u0013\u0000\u0000ba\u0001"+
		"\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000"+
		"di\u0003 \u0010\u0000ef\u0005\u0013\u0000\u0000fh\u0003 \u0010\u0000g"+
		"e\u0001\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000"+
		"\u0000ij\u0001\u0000\u0000\u0000jm\u0001\u0000\u0000\u0000ki\u0001\u0000"+
		"\u0000\u0000ln\u0005\u0013\u0000\u0000ml\u0001\u0000\u0000\u0000mn\u0001"+
		"\u0000\u0000\u0000no\u0001\u0000\u0000\u0000op\u0005\u0005\u0000\u0000"+
		"p\u000b\u0001\u0000\u0000\u0000qs\u0005\u0006\u0000\u0000rt\u0005\u0013"+
		"\u0000\u0000sr\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tu\u0001"+
		"\u0000\u0000\u0000u\u0080\u0005\u0017\u0000\u0000vx\u0005\u0013\u0000"+
		"\u0000wv\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xy\u0001\u0000"+
		"\u0000\u0000y{\u0005\u0007\u0000\u0000z|\u0005\u0013\u0000\u0000{z\u0001"+
		"\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000"+
		"}\u007f\u0005\u0017\u0000\u0000~w\u0001\u0000\u0000\u0000\u007f\u0082"+
		"\u0001\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0080\u0081\u0001"+
		"\u0000\u0000\u0000\u0081\u0083\u0001\u0000\u0000\u0000\u0082\u0080\u0001"+
		"\u0000\u0000\u0000\u0083\u0084\u0005\b\u0000\u0000\u0084\r\u0001\u0000"+
		"\u0000\u0000\u0085\u0087\u0005\u0006\u0000\u0000\u0086\u0088\u0005\u0013"+
		"\u0000\u0000\u0087\u0086\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000"+
		"\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u0094\u0003\u0018"+
		"\f\u0000\u008a\u008c\u0005\u0013\u0000\u0000\u008b\u008a\u0001\u0000\u0000"+
		"\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000"+
		"\u0000\u008d\u008f\u0005\u0007\u0000\u0000\u008e\u0090\u0005\u0013\u0000"+
		"\u0000\u008f\u008e\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000"+
		"\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0093\u0003\u0018\f\u0000"+
		"\u0092\u008b\u0001\u0000\u0000\u0000\u0093\u0096\u0001\u0000\u0000\u0000"+
		"\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000"+
		"\u0095\u0097\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000\u0000"+
		"\u0097\u0098\u0005\b\u0000\u0000\u0098\u000f\u0001\u0000\u0000\u0000\u0099"+
		"\u009b\u0003\u0004\u0002\u0000\u009a\u0099\u0001\u0000\u0000\u0000\u009a"+
		"\u009b\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c"+
		"\u009d\u0007\u0001\u0000\u0000\u009d\u009f\u0005\u0017\u0000\u0000\u009e"+
		"\u00a0\u0005\u0013\u0000\u0000\u009f\u009e\u0001\u0000\u0000\u0000\u009f"+
		"\u00a0\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1"+
		"\u00a3\u0005\t\u0000\u0000\u00a2\u00a4\u0005\u0013\u0000\u0000\u00a3\u00a2"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a5"+
		"\u0001\u0000\u0000\u0000\u00a5\u00b8\u0003\"\u0011\u0000\u00a6\u00a8\u0003"+
		"\u0004\u0002\u0000\u00a7\u00a6\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001"+
		"\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9\u00aa\u0007"+
		"\u0001\u0000\u0000\u00aa\u00ab\u0005\u0017\u0000\u0000\u00ab\u00ac\u0005"+
		"\n\u0000\u0000\u00ac\u00b5\u0003\u0018\f\u0000\u00ad\u00af\u0005\u0013"+
		"\u0000\u0000\u00ae\u00ad\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000"+
		"\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0\u00b2\u0005\t\u0000"+
		"\u0000\u00b1\u00b3\u0005\u0013\u0000\u0000\u00b2\u00b1\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b6\u0003\"\u0011\u0000\u00b5\u00ae\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b8\u0001\u0000\u0000\u0000"+
		"\u00b7\u009a\u0001\u0000\u0000\u0000\u00b7\u00a7\u0001\u0000\u0000\u0000"+
		"\u00b8\u0011\u0001\u0000\u0000\u0000\u00b9\u00bb\u0005\u0013\u0000\u0000"+
		"\u00ba\u00b9\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00bd\u0005\u0017\u0000\u0000"+
		"\u00bd\u00be\u0005\n\u0000\u0000\u00be\u00c7\u0003\u0018\f\u0000\u00bf"+
		"\u00c1\u0005\u0013\u0000\u0000\u00c0\u00bf\u0001\u0000\u0000\u0000\u00c0"+
		"\u00c1\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2"+
		"\u00c4\u0005\t\u0000\u0000\u00c3\u00c5\u0005\u0013\u0000\u0000\u00c4\u00c3"+
		"\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u00c6"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c8\u0003\"\u0011\u0000\u00c7\u00c0\u0001"+
		"\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u0013\u0001"+
		"\u0000\u0000\u0000\u00c9\u00cb\u0003\u0004\u0002\u0000\u00ca\u00c9\u0001"+
		"\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001"+
		"\u0000\u0000\u0000\u00cc\u00cd\u0005\u0011\u0000\u0000\u00cd\u00cf\u0005"+
		"\u0017\u0000\u0000\u00ce\u00d0\u0003\f\u0006\u0000\u00cf\u00ce\u0001\u0000"+
		"\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000"+
		"\u0000\u0000\u00d1\u00d3\u0005\u0002\u0000\u0000\u00d2\u00d4\u0005\u0013"+
		"\u0000\u0000\u00d3\u00d2\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000"+
		"\u0000\u0000\u00d4\u00e6\u0001\u0000\u0000\u0000\u00d5\u00d7\u0005\u0013"+
		"\u0000\u0000\u00d6\u00d5\u0001\u0000\u0000\u0000\u00d6\u00d7\u0001\u0000"+
		"\u0000\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000\u00d8\u00e3\u0003\u0012"+
		"\t\u0000\u00d9\u00db\u0005\u0013\u0000\u0000\u00da\u00d9\u0001\u0000\u0000"+
		"\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00dc\u0001\u0000\u0000"+
		"\u0000\u00dc\u00de\u0005\u0007\u0000\u0000\u00dd\u00df\u0005\u0013\u0000"+
		"\u0000\u00de\u00dd\u0001\u0000\u0000\u0000\u00de\u00df\u0001\u0000\u0000"+
		"\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u00e2\u0003\u0012\t\u0000"+
		"\u00e1\u00da\u0001\u0000\u0000\u0000\u00e2\u00e5\u0001\u0000\u0000\u0000"+
		"\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001\u0000\u0000\u0000"+
		"\u00e4\u00e7\u0001\u0000\u0000\u0000\u00e5\u00e3\u0001\u0000\u0000\u0000"+
		"\u00e6\u00d6\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000"+
		"\u00e7\u00e9\u0001\u0000\u0000\u0000\u00e8\u00ea\u0005\u0013\u0000\u0000"+
		"\u00e9\u00e8\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000"+
		"\u00ea\u00eb\u0001\u0000\u0000\u0000\u00eb\u00ee\u0005\u0003\u0000\u0000"+
		"\u00ec\u00ed\u0005\n\u0000\u0000\u00ed\u00ef\u0003\u0018\f\u0000\u00ee"+
		"\u00ec\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef"+
		"\u00f1\u0001\u0000\u0000\u0000\u00f0\u00f2\u0005\u0013\u0000\u0000\u00f1"+
		"\u00f0\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000\u0000\u00f2"+
		"\u00f4\u0001\u0000\u0000\u0000\u00f3\u00f5\u0003\n\u0005\u0000\u00f4\u00f3"+
		"\u0001\u0000\u0000\u0000\u00f4\u00f5\u0001\u0000\u0000\u0000\u00f5\u0015"+
		"\u0001\u0000\u0000\u0000\u00f6\u00f8\u0003\u0004\u0002\u0000\u00f7\u00f6"+
		"\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000\u00f8\u00f9"+
		"\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005\u0012\u0000\u0000\u00fa\u00fc"+
		"\u0005\u0017\u0000\u0000\u00fb\u00fd\u0003\f\u0006\u0000\u00fc\u00fb\u0001"+
		"\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u0113\u0001"+
		"\u0000\u0000\u0000\u00fe\u0100\u0005\u0013\u0000\u0000\u00ff\u00fe\u0001"+
		"\u0000\u0000\u0000\u00ff\u0100\u0001\u0000\u0000\u0000\u0100\u0101\u0001"+
		"\u0000\u0000\u0000\u0101\u0103\u0005\n\u0000\u0000\u0102\u0104\u0005\u0013"+
		"\u0000\u0000\u0103\u0102\u0001\u0000\u0000\u0000\u0103\u0104\u0001\u0000"+
		"\u0000\u0000\u0104\u0105\u0001\u0000\u0000\u0000\u0105\u0110\u0003\u0018"+
		"\f\u0000\u0106\u0108\u0005\u0013\u0000\u0000\u0107\u0106\u0001\u0000\u0000"+
		"\u0000\u0107\u0108\u0001\u0000\u0000\u0000\u0108\u0109\u0001\u0000\u0000"+
		"\u0000\u0109\u010b\u0005\u0007\u0000\u0000\u010a\u010c\u0005\u0013\u0000"+
		"\u0000\u010b\u010a\u0001\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000"+
		"\u0000\u010c\u010d\u0001\u0000\u0000\u0000\u010d\u010f\u0003\u0018\f\u0000"+
		"\u010e\u0107\u0001\u0000\u0000\u0000\u010f\u0112\u0001\u0000\u0000\u0000"+
		"\u0110\u010e\u0001\u0000\u0000\u0000\u0110\u0111\u0001\u0000\u0000\u0000"+
		"\u0111\u0114\u0001\u0000\u0000\u0000\u0112\u0110\u0001\u0000\u0000\u0000"+
		"\u0113\u00ff\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000\u0000\u0000"+
		"\u0114\u012a\u0001\u0000\u0000\u0000\u0115\u0117\u0005\u0004\u0000\u0000"+
		"\u0116\u0118\u0005\u0013\u0000\u0000\u0117\u0116\u0001\u0000\u0000\u0000"+
		"\u0117\u0118\u0001\u0000\u0000\u0000\u0118\u011b\u0001\u0000\u0000\u0000"+
		"\u0119\u011c\u0003\u0010\b\u0000\u011a\u011c\u0003\u0014\n\u0000\u011b"+
		"\u0119\u0001\u0000\u0000\u0000\u011b\u011a\u0001\u0000\u0000\u0000\u011c"+
		"\u0122\u0001\u0000\u0000\u0000\u011d\u011e\u0005\u0013\u0000\u0000\u011e"+
		"\u0121\u0003\u0010\b\u0000\u011f\u0121\u0003\u0014\n\u0000\u0120\u011d"+
		"\u0001\u0000\u0000\u0000\u0120\u011f\u0001\u0000\u0000\u0000\u0121\u0124"+
		"\u0001\u0000\u0000\u0000\u0122\u0120\u0001\u0000\u0000\u0000\u0122\u0123"+
		"\u0001\u0000\u0000\u0000\u0123\u0126\u0001\u0000\u0000\u0000\u0124\u0122"+
		"\u0001\u0000\u0000\u0000\u0125\u0127\u0005\u0013\u0000\u0000\u0126\u0125"+
		"\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000\u0000\u0000\u0127\u0128"+
		"\u0001\u0000\u0000\u0000\u0128\u0129\u0005\u0005\u0000\u0000\u0129\u012b"+
		"\u0001\u0000\u0000\u0000\u012a\u0115\u0001\u0000\u0000\u0000\u012a\u012b"+
		"\u0001\u0000\u0000\u0000\u012b\u0017\u0001\u0000\u0000\u0000\u012c\u013a"+
		"\u0005\u0017\u0000\u0000\u012d\u012f\u0005\u0002\u0000\u0000\u012e\u0130"+
		"\u0005\u0013\u0000\u0000\u012f\u012e\u0001\u0000\u0000\u0000\u012f\u0130"+
		"\u0001\u0000\u0000\u0000\u0130\u0131\u0001\u0000\u0000\u0000\u0131\u0133"+
		"\u0003\u0018\f\u0000\u0132\u0134\u0005\u0013\u0000\u0000\u0133\u0132\u0001"+
		"\u0000\u0000\u0000\u0133\u0134\u0001\u0000\u0000\u0000\u0134\u0135\u0001"+
		"\u0000\u0000\u0000\u0135\u0136\u0005\u0003\u0000\u0000\u0136\u013a\u0001"+
		"\u0000\u0000\u0000\u0137\u013a\u0003\u001e\u000f\u0000\u0138\u013a\u0003"+
		"\u001a\r\u0000\u0139\u012c\u0001\u0000\u0000\u0000\u0139\u012d\u0001\u0000"+
		"\u0000\u0000\u0139\u0137\u0001\u0000\u0000\u0000\u0139\u0138\u0001\u0000"+
		"\u0000\u0000\u013a\u013c\u0001\u0000\u0000\u0000\u013b\u013d\u0003\u001c"+
		"\u000e\u0000\u013c\u013b\u0001\u0000\u0000\u0000\u013c\u013d\u0001\u0000"+
		"\u0000\u0000\u013d\u0019\u0001\u0000\u0000\u0000\u013e\u013f\u0005\u0017"+
		"\u0000\u0000\u013f\u0140\u0003\u000e\u0007\u0000\u0140\u001b\u0001\u0000"+
		"\u0000\u0000\u0141\u0142\u0005\u000b\u0000\u0000\u0142\u001d\u0001\u0000"+
		"\u0000\u0000\u0143\u0155\u0005\u0002\u0000\u0000\u0144\u0146\u0005\u0013"+
		"\u0000\u0000\u0145\u0144\u0001\u0000\u0000\u0000\u0145\u0146\u0001\u0000"+
		"\u0000\u0000\u0146\u0147\u0001\u0000\u0000\u0000\u0147\u0152\u0003\u0018"+
		"\f\u0000\u0148\u014a\u0005\u0013\u0000\u0000\u0149\u0148\u0001\u0000\u0000"+
		"\u0000\u0149\u014a\u0001\u0000\u0000\u0000\u014a\u014b\u0001\u0000\u0000"+
		"\u0000\u014b\u014d\u0005\u0007\u0000\u0000\u014c\u014e\u0005\u0013\u0000"+
		"\u0000\u014d\u014c\u0001\u0000\u0000\u0000\u014d\u014e\u0001\u0000\u0000"+
		"\u0000\u014e\u014f\u0001\u0000\u0000\u0000\u014f\u0151\u0003\u0018\f\u0000"+
		"\u0150\u0149\u0001\u0000\u0000\u0000\u0151\u0154\u0001\u0000\u0000\u0000"+
		"\u0152\u0150\u0001\u0000\u0000\u0000\u0152\u0153\u0001\u0000\u0000\u0000"+
		"\u0153\u0156\u0001\u0000\u0000\u0000\u0154\u0152\u0001\u0000\u0000\u0000"+
		"\u0155\u0145\u0001\u0000\u0000\u0000\u0155\u0156\u0001\u0000\u0000\u0000"+
		"\u0156\u0157\u0001\u0000\u0000\u0000\u0157\u0158\u0005\u0003\u0000\u0000"+
		"\u0158\u015a\u0005\f\u0000\u0000\u0159\u015b\u0005\u0013\u0000\u0000\u015a"+
		"\u0159\u0001\u0000\u0000\u0000\u015a\u015b\u0001\u0000\u0000\u0000\u015b"+
		"\u015c\u0001\u0000\u0000\u0000\u015c\u015d\u0003\u0018\f\u0000\u015d\u001f"+
		"\u0001\u0000\u0000\u0000\u015e\u0161\u0003\"\u0011\u0000\u015f\u0161\u0003"+
		"\b\u0004\u0000\u0160\u015e\u0001\u0000\u0000\u0000\u0160\u015f\u0001\u0000"+
		"\u0000\u0000\u0161!\u0001\u0000\u0000\u0000\u0162\u0171\u0003\u0006\u0003"+
		"\u0000\u0163\u0171\u0005\u0016\u0000\u0000\u0164\u0166\u0005\u0002\u0000"+
		"\u0000\u0165\u0167\u0005\u0013\u0000\u0000\u0166\u0165\u0001\u0000\u0000"+
		"\u0000\u0166\u0167\u0001\u0000\u0000\u0000\u0167\u0168\u0001\u0000\u0000"+
		"\u0000\u0168\u016a\u0003\"\u0011\u0000\u0169\u016b\u0005\u0013\u0000\u0000"+
		"\u016a\u0169\u0001\u0000\u0000\u0000\u016a\u016b\u0001\u0000\u0000\u0000"+
		"\u016b\u016c\u0001\u0000\u0000\u0000\u016c\u016d\u0005\u0003\u0000\u0000"+
		"\u016d\u0171\u0001\u0000\u0000\u0000\u016e\u0171\u0005\u0017\u0000\u0000"+
		"\u016f\u0171\u0003$\u0012\u0000\u0170\u0162\u0001\u0000\u0000\u0000\u0170"+
		"\u0163\u0001\u0000\u0000\u0000\u0170\u0164\u0001\u0000\u0000\u0000\u0170"+
		"\u016e\u0001\u0000\u0000\u0000\u0170\u016f\u0001\u0000\u0000\u0000\u0171"+
		"\u0173\u0001\u0000\u0000\u0000\u0172\u0174\u0003(\u0014\u0000\u0173\u0172"+
		"\u0001\u0000\u0000\u0000\u0173\u0174\u0001\u0000\u0000\u0000\u0174\u0176"+
		"\u0001\u0000\u0000\u0000\u0175\u0177\u0003&\u0013\u0000\u0176\u0175\u0001"+
		"\u0000\u0000\u0000\u0176\u0177\u0001\u0000\u0000\u0000\u0177#\u0001\u0000"+
		"\u0000\u0000\u0178\u017a\u0005\u0004\u0000\u0000\u0179\u017b\u0005\u0013"+
		"\u0000\u0000\u017a\u0179\u0001\u0000\u0000\u0000\u017a\u017b\u0001\u0000"+
		"\u0000\u0000\u017b\u017c\u0001\u0000\u0000\u0000\u017c\u0187\u0003\u0012"+
		"\t\u0000\u017d\u017f\u0005\u0013\u0000\u0000\u017e\u017d\u0001\u0000\u0000"+
		"\u0000\u017e\u017f\u0001\u0000\u0000\u0000\u017f\u0180\u0001\u0000\u0000"+
		"\u0000\u0180\u0182\u0005\u0007\u0000\u0000\u0181\u0183\u0005\u0013\u0000"+
		"\u0000\u0182\u0181\u0001\u0000\u0000\u0000\u0182\u0183\u0001\u0000\u0000"+
		"\u0000\u0183\u0184\u0001\u0000\u0000\u0000\u0184\u0186\u0003\u0012\t\u0000"+
		"\u0185\u017e\u0001\u0000\u0000\u0000\u0186\u0189\u0001\u0000\u0000\u0000"+
		"\u0187\u0185\u0001\u0000\u0000\u0000\u0187\u0188\u0001\u0000\u0000\u0000"+
		"\u0188\u018b\u0001\u0000\u0000\u0000\u0189\u0187\u0001\u0000\u0000\u0000"+
		"\u018a\u018c\u0005\u0013\u0000\u0000\u018b\u018a\u0001\u0000\u0000\u0000"+
		"\u018b\u018c\u0001\u0000\u0000\u0000\u018c\u018d\u0001\u0000\u0000\u0000"+
		"\u018d\u018f\u0005\f\u0000\u0000\u018e\u0190\u0005\u0013\u0000\u0000\u018f"+
		"\u018e\u0001\u0000\u0000\u0000\u018f\u0190\u0001\u0000\u0000\u0000\u0190"+
		"\u0192\u0001\u0000\u0000\u0000\u0191\u0193\u0003 \u0010\u0000\u0192\u0191"+
		"\u0001\u0000\u0000\u0000\u0192\u0193\u0001\u0000\u0000\u0000\u0193\u0198"+
		"\u0001\u0000\u0000\u0000\u0194\u0195\u0005\u0013\u0000\u0000\u0195\u0197"+
		"\u0003 \u0010\u0000\u0196\u0194\u0001\u0000\u0000\u0000\u0197\u019a\u0001"+
		"\u0000\u0000\u0000\u0198\u0196\u0001\u0000\u0000\u0000\u0198\u0199\u0001"+
		"\u0000\u0000\u0000\u0199\u019c\u0001\u0000\u0000\u0000\u019a\u0198\u0001"+
		"\u0000\u0000\u0000\u019b\u019d\u0005\u0013\u0000\u0000\u019c\u019b\u0001"+
		"\u0000\u0000\u0000\u019c\u019d\u0001\u0000\u0000\u0000\u019d\u019e\u0001"+
		"\u0000\u0000\u0000\u019e\u019f\u0005\u0005\u0000\u0000\u019f\u01cd\u0001"+
		"\u0000\u0000\u0000\u01a0\u01a2\u0005\u0004\u0000\u0000\u01a1\u01a3\u0005"+
		"\u0013\u0000\u0000\u01a2\u01a1\u0001\u0000\u0000\u0000\u01a2\u01a3\u0001"+
		"\u0000\u0000\u0000\u01a3\u01b9\u0001\u0000\u0000\u0000\u01a4\u01a6\u0005"+
		"\u0013\u0000\u0000\u01a5\u01a4\u0001\u0000\u0000\u0000\u01a5\u01a6\u0001"+
		"\u0000\u0000\u0000\u01a6\u01a7\u0001\u0000\u0000\u0000\u01a7\u01b2\u0005"+
		"\u0017\u0000\u0000\u01a8\u01aa\u0005\u0013\u0000\u0000\u01a9\u01a8\u0001"+
		"\u0000\u0000\u0000\u01a9\u01aa\u0001\u0000\u0000\u0000\u01aa\u01ab\u0001"+
		"\u0000\u0000\u0000\u01ab\u01ad\u0005\u0007\u0000\u0000\u01ac\u01ae\u0005"+
		"\u0013\u0000\u0000\u01ad\u01ac\u0001\u0000\u0000\u0000\u01ad\u01ae\u0001"+
		"\u0000\u0000\u0000\u01ae\u01af\u0001\u0000\u0000\u0000\u01af\u01b1\u0005"+
		"\u0017\u0000\u0000\u01b0\u01a9\u0001\u0000\u0000\u0000\u01b1\u01b4\u0001"+
		"\u0000\u0000\u0000\u01b2\u01b0\u0001\u0000\u0000\u0000\u01b2\u01b3\u0001"+
		"\u0000\u0000\u0000\u01b3\u01b6\u0001\u0000\u0000\u0000\u01b4\u01b2\u0001"+
		"\u0000\u0000\u0000\u01b5\u01b7\u0005\u0013\u0000\u0000\u01b6\u01b5\u0001"+
		"\u0000\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7\u01b8\u0001"+
		"\u0000\u0000\u0000\u01b8\u01ba\u0005\f\u0000\u0000\u01b9\u01a5\u0001\u0000"+
		"\u0000\u0000\u01b9\u01ba\u0001\u0000\u0000\u0000\u01ba\u01bc\u0001\u0000"+
		"\u0000\u0000\u01bb\u01bd\u0005\u0013\u0000\u0000\u01bc\u01bb\u0001\u0000"+
		"\u0000\u0000\u01bc\u01bd\u0001\u0000\u0000\u0000\u01bd\u01bf\u0001\u0000"+
		"\u0000\u0000\u01be\u01c0\u0003 \u0010\u0000\u01bf\u01be\u0001\u0000\u0000"+
		"\u0000\u01bf\u01c0\u0001\u0000\u0000\u0000\u01c0\u01c5\u0001\u0000\u0000"+
		"\u0000\u01c1\u01c2\u0005\u0013\u0000\u0000\u01c2\u01c4\u0003 \u0010\u0000"+
		"\u01c3\u01c1\u0001\u0000\u0000\u0000\u01c4\u01c7\u0001\u0000\u0000\u0000"+
		"\u01c5\u01c3\u0001\u0000\u0000\u0000\u01c5\u01c6\u0001\u0000\u0000\u0000"+
		"\u01c6\u01c9\u0001\u0000\u0000\u0000\u01c7\u01c5\u0001\u0000\u0000\u0000"+
		"\u01c8\u01ca\u0005\u0013\u0000\u0000\u01c9\u01c8\u0001\u0000\u0000\u0000"+
		"\u01c9\u01ca\u0001\u0000\u0000\u0000\u01ca\u01cb\u0001\u0000\u0000\u0000"+
		"\u01cb\u01cd\u0005\u0005\u0000\u0000\u01cc\u0178\u0001\u0000\u0000\u0000"+
		"\u01cc\u01a0\u0001\u0000\u0000\u0000\u01cd%\u0001\u0000\u0000\u0000\u01ce"+
		"\u01d0\u0003\u000e\u0007\u0000\u01cf\u01ce\u0001\u0000\u0000\u0000\u01cf"+
		"\u01d0\u0001\u0000\u0000\u0000\u01d0\u01d1\u0001\u0000\u0000\u0000\u01d1"+
		"\u01d3\u0005\u0002\u0000\u0000\u01d2\u01d4\u0005\u0013\u0000\u0000\u01d3"+
		"\u01d2\u0001\u0000\u0000\u0000\u01d3\u01d4\u0001\u0000\u0000\u0000\u01d4"+
		"\u01e6\u0001\u0000\u0000\u0000\u01d5\u01d7\u0005\u0013\u0000\u0000\u01d6"+
		"\u01d5\u0001\u0000\u0000\u0000\u01d6\u01d7\u0001\u0000\u0000\u0000\u01d7"+
		"\u01d8\u0001\u0000\u0000\u0000\u01d8\u01e3\u0003\"\u0011\u0000\u01d9\u01db"+
		"\u0005\u0013\u0000\u0000\u01da\u01d9\u0001\u0000\u0000\u0000\u01da\u01db"+
		"\u0001\u0000\u0000\u0000\u01db\u01dc\u0001\u0000\u0000\u0000\u01dc\u01de"+
		"\u0005\u0007\u0000\u0000\u01dd\u01df\u0005\u0013\u0000\u0000\u01de\u01dd"+
		"\u0001\u0000\u0000\u0000\u01de\u01df\u0001\u0000\u0000\u0000\u01df\u01e0"+
		"\u0001\u0000\u0000\u0000\u01e0\u01e2\u0003\"\u0011\u0000\u01e1\u01da\u0001"+
		"\u0000\u0000\u0000\u01e2\u01e5\u0001\u0000\u0000\u0000\u01e3\u01e1\u0001"+
		"\u0000\u0000\u0000\u01e3\u01e4\u0001\u0000\u0000\u0000\u01e4\u01e7\u0001"+
		"\u0000\u0000\u0000\u01e5\u01e3\u0001\u0000\u0000\u0000\u01e6\u01d6\u0001"+
		"\u0000\u0000\u0000\u01e6\u01e7\u0001\u0000\u0000\u0000\u01e7\u01e9\u0001"+
		"\u0000\u0000\u0000\u01e8\u01ea\u0005\u0013\u0000\u0000\u01e9\u01e8\u0001"+
		"\u0000\u0000\u0000\u01e9\u01ea\u0001\u0000\u0000\u0000\u01ea\u01eb\u0001"+
		"\u0000\u0000\u0000\u01eb\u01ed\u0005\u0003\u0000\u0000\u01ec\u01ee\u0003"+
		"&\u0013\u0000\u01ed\u01ec\u0001\u0000\u0000\u0000\u01ed\u01ee\u0001\u0000"+
		"\u0000\u0000\u01ee\u01f0\u0001\u0000\u0000\u0000\u01ef\u01f1\u0003(\u0014"+
		"\u0000\u01f0\u01ef\u0001\u0000\u0000\u0000\u01f0\u01f1\u0001\u0000\u0000"+
		"\u0000\u01f1\'\u0001\u0000\u0000\u0000\u01f2\u01f4\u0005\u0013\u0000\u0000"+
		"\u01f3\u01f2\u0001\u0000\u0000\u0000\u01f3\u01f4\u0001\u0000\u0000\u0000"+
		"\u01f4\u01f5\u0001\u0000\u0000\u0000\u01f5\u01f6\u0005\r\u0000\u0000\u01f6"+
		"\u01f8\u0005\u0017\u0000\u0000\u01f7\u01f9\u0003(\u0014\u0000\u01f8\u01f7"+
		"\u0001\u0000\u0000\u0000\u01f8\u01f9\u0001\u0000\u0000\u0000\u01f9\u01fb"+
		"\u0001\u0000\u0000\u0000\u01fa\u01fc\u0003&\u0013\u0000\u01fb\u01fa\u0001"+
		"\u0000\u0000\u0000\u01fb\u01fc\u0001\u0000\u0000\u0000\u01fc)\u0001\u0000"+
		"\u0000\u0000n+27=AEHMQV^bimsw{\u0080\u0087\u008b\u008f\u0094\u009a\u009f"+
		"\u00a3\u00a7\u00ae\u00b2\u00b5\u00b7\u00ba\u00c0\u00c4\u00c7\u00ca\u00cf"+
		"\u00d3\u00d6\u00da\u00de\u00e3\u00e6\u00e9\u00ee\u00f1\u00f4\u00f7\u00fc"+
		"\u00ff\u0103\u0107\u010b\u0110\u0113\u0117\u011b\u0120\u0122\u0126\u012a"+
		"\u012f\u0133\u0139\u013c\u0145\u0149\u014d\u0152\u0155\u015a\u0160\u0166"+
		"\u016a\u0170\u0173\u0176\u017a\u017e\u0182\u0187\u018b\u018f\u0192\u0198"+
		"\u019c\u01a2\u01a5\u01a9\u01ad\u01b2\u01b6\u01b9\u01bc\u01bf\u01c5\u01c9"+
		"\u01cc\u01cf\u01d3\u01d6\u01da\u01de\u01e3\u01e6\u01e9\u01ed\u01f0\u01f3"+
		"\u01f8\u01fb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}