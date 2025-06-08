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
			setState(118);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
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
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				match(T__3);
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
			setState(120);
			match(T__5);
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(121);
				match(NL);
				}
			}

			setState(124);
			match(ID);
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6 || _la==NL) {
				{
				{
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(125);
					match(NL);
					}
				}

				setState(128);
				match(T__6);
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(129);
					match(NL);
					}
				}

				setState(132);
				match(ID);
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(138);
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
			setState(140);
			match(T__5);
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(141);
				match(NL);
				}
			}

			setState(144);
			type();
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6 || _la==NL) {
				{
				{
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(145);
					match(NL);
					}
				}

				setState(148);
				match(T__6);
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(149);
					match(NL);
					}
				}

				setState(152);
				type();
				}
				}
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(158);
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
			setState(190);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(161);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(160);
					modifier();
					}
					break;
				}
				setState(163);
				_la = _input.LA(1);
				if ( !(_la==VAL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(164);
				match(ID);
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(165);
					match(NL);
					}
				}

				setState(168);
				match(T__8);
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(169);
					match(NL);
					}
				}

				setState(172);
				expr();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(174);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(173);
					modifier();
					}
					break;
				}
				setState(176);
				_la = _input.LA(1);
				if ( !(_la==VAL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(177);
				match(ID);
				setState(178);
				match(T__9);
				setState(179);
				type();
				setState(188);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(181);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(180);
						match(NL);
						}
					}

					setState(183);
					match(T__8);
					setState(185);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(184);
						match(NL);
						}
					}

					setState(187);
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
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(192);
				match(NL);
				}
			}

			setState(195);
			match(ID);
			setState(196);
			match(T__9);
			setState(197);
			type();
			setState(206);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(198);
					match(NL);
					}
				}

				setState(201);
				match(T__8);
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(202);
					match(NL);
					}
				}

				setState(205);
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
			setState(209);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(208);
				modifier();
				}
				break;
			}
			setState(211);
			match(FUN);
			setState(212);
			match(ID);
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(213);
				typeParamList();
				}
			}

			setState(216);
			match(T__1);
			setState(218);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(217);
				match(NL);
				}
				break;
			}
			setState(237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(221);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
				case 1:
					{
					setState(220);
					match(NL);
					}
					break;
				}
				setState(223);
				parameter();
				setState(234);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(225);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(224);
							match(NL);
							}
						}

						setState(227);
						match(T__6);
						setState(229);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
						case 1:
							{
							setState(228);
							match(NL);
							}
							break;
						}
						setState(231);
						parameter();
						}
						} 
					}
					setState(236);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
				}
				}
				break;
			}
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(239);
				match(NL);
				}
			}

			setState(242);
			match(T__2);
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(243);
				match(T__9);
				setState(244);
				type();
				}
			}

			setState(248);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(247);
				match(NL);
				}
				break;
			}
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(250);
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
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				{
				setState(253);
				modifier();
				}
				break;
			}
			setState(256);
			match(CLASS);
			setState(257);
			match(ID);
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(258);
				typeParamList();
				}
			}

			setState(282);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				{
				setState(262);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(261);
					match(NL);
					}
				}

				setState(264);
				match(T__9);
				{
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(265);
					match(NL);
					}
				}

				setState(268);
				type();
				setState(279);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(270);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(269);
							match(NL);
							}
						}

						setState(272);
						match(T__6);
						setState(274);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(273);
							match(NL);
							}
						}

						setState(276);
						type();
						}
						} 
					}
					setState(281);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
				}
				}
				}
				break;
			}
			setState(305);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(284);
				match(T__3);
				setState(286);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
				case 1:
					{
					setState(285);
					match(NL);
					}
					break;
				}
				setState(290);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
				case 1:
					{
					setState(288);
					variable();
					}
					break;
				case 2:
					{
					setState(289);
					function();
					}
					break;
				}
				setState(297);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(295);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
						case 1:
							{
							setState(292);
							match(NL);
							setState(293);
							variable();
							}
							break;
						case 2:
							{
							setState(294);
							function();
							}
							break;
						}
						} 
					}
					setState(299);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
				}
				setState(301);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(300);
					match(NL);
					}
				}

				setState(303);
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
			setState(320);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				{
				setState(307);
				match(ID);
				}
				break;
			case 2:
				{
				setState(308);
				match(T__1);
				setState(310);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(309);
					match(NL);
					}
				}

				setState(312);
				type();
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(313);
					match(NL);
					}
				}

				setState(316);
				match(T__2);
				}
				break;
			case 3:
				{
				setState(318);
				functionType();
				}
				break;
			case 4:
				{
				setState(319);
				applyType();
				}
				break;
			}
			setState(323);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				{
				setState(322);
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
			setState(325);
			match(ID);
			setState(326);
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
			setState(328);
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
			setState(330);
			match(T__1);
			setState(348);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8912900L) != 0)) {
				{
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(331);
					match(NL);
					}
				}

				setState(334);
				type();
				setState(345);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__6 || _la==NL) {
					{
					{
					setState(336);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(335);
						match(NL);
						}
					}

					setState(338);
					match(T__6);
					setState(340);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(339);
						match(NL);
						}
					}

					setState(342);
					type();
					}
					}
					setState(347);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(350);
			match(T__2);
			setState(351);
			match(T__11);
			setState(353);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(352);
				match(NL);
				}
			}

			setState(355);
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
			setState(359);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(357);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(358);
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
			setState(375);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case DEC:
				{
				setState(361);
				number();
				}
				break;
			case STRING:
				{
				setState(362);
				match(STRING);
				}
				break;
			case T__1:
				{
				setState(363);
				match(T__1);
				setState(365);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(364);
					match(NL);
					}
				}

				setState(367);
				expr();
				setState(369);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(368);
					match(NL);
					}
				}

				setState(371);
				match(T__2);
				}
				break;
			case ID:
				{
				setState(373);
				match(ID);
				}
				break;
			case T__3:
				{
				setState(374);
				lambda();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(378);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
			case 1:
				{
				setState(377);
				name();
				}
				break;
			}
			setState(381);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1 || _la==T__5) {
				{
				setState(380);
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
			setState(467);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(383);
				match(T__3);
				setState(385);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
				case 1:
					{
					setState(384);
					match(NL);
					}
					break;
				}
				setState(387);
				parameter();
				setState(398);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(389);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(388);
							match(NL);
							}
						}

						setState(391);
						match(T__6);
						setState(393);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
						case 1:
							{
							setState(392);
							match(NL);
							}
							break;
						}
						setState(395);
						parameter();
						}
						} 
					}
					setState(400);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
				}
				setState(402);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(401);
					match(NL);
					}
				}

				setState(404);
				match(T__11);
				setState(406);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
				case 1:
					{
					setState(405);
					match(NL);
					}
					break;
				}
				setState(409);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
				case 1:
					{
					setState(408);
					stmt();
					}
					break;
				}
				setState(415);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(411);
						match(NL);
						setState(412);
						stmt();
						}
						} 
					}
					setState(417);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
				}
				setState(419);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(418);
					match(NL);
					}
				}

				setState(421);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(423);
				match(T__3);
				setState(425);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
				case 1:
					{
					setState(424);
					match(NL);
					}
					break;
				}
				setState(448);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
				case 1:
					{
					setState(428);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(427);
						match(NL);
						}
					}

					setState(430);
					match(ID);
					setState(441);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(432);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==NL) {
								{
								setState(431);
								match(NL);
								}
							}

							setState(434);
							match(T__6);
							setState(436);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==NL) {
								{
								setState(435);
								match(NL);
								}
							}

							setState(438);
							match(ID);
							}
							} 
						}
						setState(443);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
					}
					setState(445);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(444);
						match(NL);
						}
					}

					setState(447);
					match(T__11);
					}
					break;
				}
				setState(451);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
				case 1:
					{
					setState(450);
					match(NL);
					}
					break;
				}
				setState(454);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					{
					setState(453);
					stmt();
					}
					break;
				}
				setState(460);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(456);
						match(NL);
						setState(457);
						stmt();
						}
						} 
					}
					setState(462);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
				}
				setState(464);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(463);
					match(NL);
					}
				}

				setState(466);
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
			setState(470);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(469);
				typeArgList();
				}
			}

			setState(472);
			match(T__1);
			setState(474);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				{
				setState(473);
				match(NL);
				}
				break;
			}
			setState(493);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
			case 1:
				{
				setState(477);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(476);
					match(NL);
					}
				}

				setState(479);
				expr();
				setState(490);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,104,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(481);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(480);
							match(NL);
							}
						}

						setState(483);
						match(T__6);
						setState(485);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(484);
							match(NL);
							}
						}

						setState(487);
						expr();
						}
						} 
					}
					setState(492);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,104,_ctx);
				}
				}
				break;
			}
			setState(496);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(495);
				match(NL);
				}
			}

			setState(498);
			match(T__2);
			setState(500);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
			case 1:
				{
				setState(499);
				invoke();
				}
				break;
			}
			setState(503);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
			case 1:
				{
				setState(502);
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
			setState(506);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(505);
				match(NL);
				}
			}

			setState(508);
			match(T__12);
			setState(509);
			match(ID);
			setState(511);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				{
				setState(510);
				name();
				}
				break;
			}
			setState(514);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
			case 1:
				{
				setState(513);
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
		"\u0004\u0001\u0018\u0205\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
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
		"\u0003\u0005n\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0003\u0005t\b\u0005\u0001\u0005\u0003\u0005w\b\u0005\u0001\u0006\u0001"+
		"\u0006\u0003\u0006{\b\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u007f"+
		"\b\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0083\b\u0006\u0001\u0006"+
		"\u0005\u0006\u0086\b\u0006\n\u0006\f\u0006\u0089\t\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0003\u0007\u008f\b\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007\u0093\b\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0097"+
		"\b\u0007\u0001\u0007\u0005\u0007\u009a\b\u0007\n\u0007\f\u0007\u009d\t"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0003\b\u00a2\b\b\u0001\b\u0001"+
		"\b\u0001\b\u0003\b\u00a7\b\b\u0001\b\u0001\b\u0003\b\u00ab\b\b\u0001\b"+
		"\u0001\b\u0003\b\u00af\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u00b6\b\b\u0001\b\u0001\b\u0003\b\u00ba\b\b\u0001\b\u0003\b\u00bd\b"+
		"\b\u0003\b\u00bf\b\b\u0001\t\u0003\t\u00c2\b\t\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0003\t\u00c8\b\t\u0001\t\u0001\t\u0003\t\u00cc\b\t\u0001\t\u0003"+
		"\t\u00cf\b\t\u0001\n\u0003\n\u00d2\b\n\u0001\n\u0001\n\u0001\n\u0003\n"+
		"\u00d7\b\n\u0001\n\u0001\n\u0003\n\u00db\b\n\u0001\n\u0003\n\u00de\b\n"+
		"\u0001\n\u0001\n\u0003\n\u00e2\b\n\u0001\n\u0001\n\u0003\n\u00e6\b\n\u0001"+
		"\n\u0005\n\u00e9\b\n\n\n\f\n\u00ec\t\n\u0003\n\u00ee\b\n\u0001\n\u0003"+
		"\n\u00f1\b\n\u0001\n\u0001\n\u0001\n\u0003\n\u00f6\b\n\u0001\n\u0003\n"+
		"\u00f9\b\n\u0001\n\u0003\n\u00fc\b\n\u0001\u000b\u0003\u000b\u00ff\b\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0104\b\u000b\u0001\u000b"+
		"\u0003\u000b\u0107\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u010b\b"+
		"\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u010f\b\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u0113\b\u000b\u0001\u000b\u0005\u000b\u0116\b\u000b"+
		"\n\u000b\f\u000b\u0119\t\u000b\u0003\u000b\u011b\b\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u011f\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0123"+
		"\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u0128\b\u000b"+
		"\n\u000b\f\u000b\u012b\t\u000b\u0001\u000b\u0003\u000b\u012e\b\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u0132\b\u000b\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u0137\b\f\u0001\f\u0001\f\u0003\f\u013b\b\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0003\f\u0141\b\f\u0001\f\u0003\f\u0144\b\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u014d\b"+
		"\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0151\b\u000f\u0001\u000f\u0001"+
		"\u000f\u0003\u000f\u0155\b\u000f\u0001\u000f\u0005\u000f\u0158\b\u000f"+
		"\n\u000f\f\u000f\u015b\t\u000f\u0003\u000f\u015d\b\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0003\u000f\u0162\b\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0003\u0010\u0168\b\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0003\u0011\u016e\b\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u0172\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u0178\b\u0011\u0001\u0011\u0003\u0011\u017b\b\u0011\u0001\u0011"+
		"\u0003\u0011\u017e\b\u0011\u0001\u0012\u0001\u0012\u0003\u0012\u0182\b"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0186\b\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u018a\b\u0012\u0001\u0012\u0005\u0012\u018d\b\u0012"+
		"\n\u0012\f\u0012\u0190\t\u0012\u0001\u0012\u0003\u0012\u0193\b\u0012\u0001"+
		"\u0012\u0001\u0012\u0003\u0012\u0197\b\u0012\u0001\u0012\u0003\u0012\u019a"+
		"\b\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u019e\b\u0012\n\u0012\f\u0012"+
		"\u01a1\t\u0012\u0001\u0012\u0003\u0012\u01a4\b\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u01aa\b\u0012\u0001\u0012\u0003"+
		"\u0012\u01ad\b\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u01b1\b\u0012"+
		"\u0001\u0012\u0001\u0012\u0003\u0012\u01b5\b\u0012\u0001\u0012\u0005\u0012"+
		"\u01b8\b\u0012\n\u0012\f\u0012\u01bb\t\u0012\u0001\u0012\u0003\u0012\u01be"+
		"\b\u0012\u0001\u0012\u0003\u0012\u01c1\b\u0012\u0001\u0012\u0003\u0012"+
		"\u01c4\b\u0012\u0001\u0012\u0003\u0012\u01c7\b\u0012\u0001\u0012\u0001"+
		"\u0012\u0005\u0012\u01cb\b\u0012\n\u0012\f\u0012\u01ce\t\u0012\u0001\u0012"+
		"\u0003\u0012\u01d1\b\u0012\u0001\u0012\u0003\u0012\u01d4\b\u0012\u0001"+
		"\u0013\u0003\u0013\u01d7\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u01db"+
		"\b\u0013\u0001\u0013\u0003\u0013\u01de\b\u0013\u0001\u0013\u0001\u0013"+
		"\u0003\u0013\u01e2\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u01e6\b"+
		"\u0013\u0001\u0013\u0005\u0013\u01e9\b\u0013\n\u0013\f\u0013\u01ec\t\u0013"+
		"\u0003\u0013\u01ee\b\u0013\u0001\u0013\u0003\u0013\u01f1\b\u0013\u0001"+
		"\u0013\u0001\u0013\u0003\u0013\u01f5\b\u0013\u0001\u0013\u0003\u0013\u01f8"+
		"\b\u0013\u0001\u0014\u0003\u0014\u01fb\b\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0003\u0014\u0200\b\u0014\u0001\u0014\u0003\u0014\u0203\b"+
		"\u0014\u0001\u0014\u0000\u0000\u0015\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(\u0000\u0002\u0001"+
		"\u0000\u0014\u0015\u0001\u0000\u000f\u0010\u0265\u0000+\u0001\u0000\u0000"+
		"\u0000\u00029\u0001\u0000\u0000\u0000\u0004M\u0001\u0000\u0000\u0000\u0006"+
		"Y\u0001\u0000\u0000\u0000\b^\u0001\u0000\u0000\u0000\nv\u0001\u0000\u0000"+
		"\u0000\fx\u0001\u0000\u0000\u0000\u000e\u008c\u0001\u0000\u0000\u0000"+
		"\u0010\u00be\u0001\u0000\u0000\u0000\u0012\u00c1\u0001\u0000\u0000\u0000"+
		"\u0014\u00d1\u0001\u0000\u0000\u0000\u0016\u00fe\u0001\u0000\u0000\u0000"+
		"\u0018\u0140\u0001\u0000\u0000\u0000\u001a\u0145\u0001\u0000\u0000\u0000"+
		"\u001c\u0148\u0001\u0000\u0000\u0000\u001e\u014a\u0001\u0000\u0000\u0000"+
		" \u0167\u0001\u0000\u0000\u0000\"\u0177\u0001\u0000\u0000\u0000$\u01d3"+
		"\u0001\u0000\u0000\u0000&\u01d6\u0001\u0000\u0000\u0000(\u01fa\u0001\u0000"+
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
		"pw\u0001\u0000\u0000\u0000qs\u0005\u0004\u0000\u0000rt\u0005\u0013\u0000"+
		"\u0000sr\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tu\u0001\u0000"+
		"\u0000\u0000uw\u0005\u0005\u0000\u0000v`\u0001\u0000\u0000\u0000vq\u0001"+
		"\u0000\u0000\u0000w\u000b\u0001\u0000\u0000\u0000xz\u0005\u0006\u0000"+
		"\u0000y{\u0005\u0013\u0000\u0000zy\u0001\u0000\u0000\u0000z{\u0001\u0000"+
		"\u0000\u0000{|\u0001\u0000\u0000\u0000|\u0087\u0005\u0017\u0000\u0000"+
		"}\u007f\u0005\u0013\u0000\u0000~}\u0001\u0000\u0000\u0000~\u007f\u0001"+
		"\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0082\u0005"+
		"\u0007\u0000\u0000\u0081\u0083\u0005\u0013\u0000\u0000\u0082\u0081\u0001"+
		"\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u0084\u0001"+
		"\u0000\u0000\u0000\u0084\u0086\u0005\u0017\u0000\u0000\u0085~\u0001\u0000"+
		"\u0000\u0000\u0086\u0089\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000"+
		"\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u008a\u0001\u0000"+
		"\u0000\u0000\u0089\u0087\u0001\u0000\u0000\u0000\u008a\u008b\u0005\b\u0000"+
		"\u0000\u008b\r\u0001\u0000\u0000\u0000\u008c\u008e\u0005\u0006\u0000\u0000"+
		"\u008d\u008f\u0005\u0013\u0000\u0000\u008e\u008d\u0001\u0000\u0000\u0000"+
		"\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000"+
		"\u0090\u009b\u0003\u0018\f\u0000\u0091\u0093\u0005\u0013\u0000\u0000\u0092"+
		"\u0091\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093"+
		"\u0094\u0001\u0000\u0000\u0000\u0094\u0096\u0005\u0007\u0000\u0000\u0095"+
		"\u0097\u0005\u0013\u0000\u0000\u0096\u0095\u0001\u0000\u0000\u0000\u0096"+
		"\u0097\u0001\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098"+
		"\u009a\u0003\u0018\f\u0000\u0099\u0092\u0001\u0000\u0000\u0000\u009a\u009d"+
		"\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000\u0000\u009b\u009c"+
		"\u0001\u0000\u0000\u0000\u009c\u009e\u0001\u0000\u0000\u0000\u009d\u009b"+
		"\u0001\u0000\u0000\u0000\u009e\u009f\u0005\b\u0000\u0000\u009f\u000f\u0001"+
		"\u0000\u0000\u0000\u00a0\u00a2\u0003\u0004\u0002\u0000\u00a1\u00a0\u0001"+
		"\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a4\u0007\u0001\u0000\u0000\u00a4\u00a6\u0005"+
		"\u0017\u0000\u0000\u00a5\u00a7\u0005\u0013\u0000\u0000\u00a6\u00a5\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001"+
		"\u0000\u0000\u0000\u00a8\u00aa\u0005\t\u0000\u0000\u00a9\u00ab\u0005\u0013"+
		"\u0000\u0000\u00aa\u00a9\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000"+
		"\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00bf\u0003\"\u0011"+
		"\u0000\u00ad\u00af\u0003\u0004\u0002\u0000\u00ae\u00ad\u0001\u0000\u0000"+
		"\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b1\u0007\u0001\u0000\u0000\u00b1\u00b2\u0005\u0017\u0000"+
		"\u0000\u00b2\u00b3\u0005\n\u0000\u0000\u00b3\u00bc\u0003\u0018\f\u0000"+
		"\u00b4\u00b6\u0005\u0013\u0000\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b9\u0005\t\u0000\u0000\u00b8\u00ba\u0005\u0013\u0000\u0000\u00b9"+
		"\u00b8\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba"+
		"\u00bb\u0001\u0000\u0000\u0000\u00bb\u00bd\u0003\"\u0011\u0000\u00bc\u00b5"+
		"\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd\u00bf"+
		"\u0001\u0000\u0000\u0000\u00be\u00a1\u0001\u0000\u0000\u0000\u00be\u00ae"+
		"\u0001\u0000\u0000\u0000\u00bf\u0011\u0001\u0000\u0000\u0000\u00c0\u00c2"+
		"\u0005\u0013\u0000\u0000\u00c1\u00c0\u0001\u0000\u0000\u0000\u00c1\u00c2"+
		"\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c4"+
		"\u0005\u0017\u0000\u0000\u00c4\u00c5\u0005\n\u0000\u0000\u00c5\u00ce\u0003"+
		"\u0018\f\u0000\u00c6\u00c8\u0005\u0013\u0000\u0000\u00c7\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000"+
		"\u0000\u0000\u00c9\u00cb\u0005\t\u0000\u0000\u00ca\u00cc\u0005\u0013\u0000"+
		"\u0000\u00cb\u00ca\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000"+
		"\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd\u00cf\u0003\"\u0011\u0000"+
		"\u00ce\u00c7\u0001\u0000\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000"+
		"\u00cf\u0013\u0001\u0000\u0000\u0000\u00d0\u00d2\u0003\u0004\u0002\u0000"+
		"\u00d1\u00d0\u0001\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000"+
		"\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3\u00d4\u0005\u0011\u0000\u0000"+
		"\u00d4\u00d6\u0005\u0017\u0000\u0000\u00d5\u00d7\u0003\f\u0006\u0000\u00d6"+
		"\u00d5\u0001\u0000\u0000\u0000\u00d6\u00d7\u0001\u0000\u0000\u0000\u00d7"+
		"\u00d8\u0001\u0000\u0000\u0000\u00d8\u00da\u0005\u0002\u0000\u0000\u00d9"+
		"\u00db\u0005\u0013\u0000\u0000\u00da\u00d9\u0001\u0000\u0000\u0000\u00da"+
		"\u00db\u0001\u0000\u0000\u0000\u00db\u00ed\u0001\u0000\u0000\u0000\u00dc"+
		"\u00de\u0005\u0013\u0000\u0000\u00dd\u00dc\u0001\u0000\u0000\u0000\u00dd"+
		"\u00de\u0001\u0000\u0000\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df"+
		"\u00ea\u0003\u0012\t\u0000\u00e0\u00e2\u0005\u0013\u0000\u0000\u00e1\u00e0"+
		"\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e3"+
		"\u0001\u0000\u0000\u0000\u00e3\u00e5\u0005\u0007\u0000\u0000\u00e4\u00e6"+
		"\u0005\u0013\u0000\u0000\u00e5\u00e4\u0001\u0000\u0000\u0000\u00e5\u00e6"+
		"\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000\u00e7\u00e9"+
		"\u0003\u0012\t\u0000\u00e8\u00e1\u0001\u0000\u0000\u0000\u00e9\u00ec\u0001"+
		"\u0000\u0000\u0000\u00ea\u00e8\u0001\u0000\u0000\u0000\u00ea\u00eb\u0001"+
		"\u0000\u0000\u0000\u00eb\u00ee\u0001\u0000\u0000\u0000\u00ec\u00ea\u0001"+
		"\u0000\u0000\u0000\u00ed\u00dd\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001"+
		"\u0000\u0000\u0000\u00ee\u00f0\u0001\u0000\u0000\u0000\u00ef\u00f1\u0005"+
		"\u0013\u0000\u0000\u00f0\u00ef\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001"+
		"\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000\u0000\u00f2\u00f5\u0005"+
		"\u0003\u0000\u0000\u00f3\u00f4\u0005\n\u0000\u0000\u00f4\u00f6\u0003\u0018"+
		"\f\u0000\u00f5\u00f3\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f8\u0001\u0000\u0000\u0000\u00f7\u00f9\u0005\u0013\u0000"+
		"\u0000\u00f8\u00f7\u0001\u0000\u0000\u0000\u00f8\u00f9\u0001\u0000\u0000"+
		"\u0000\u00f9\u00fb\u0001\u0000\u0000\u0000\u00fa\u00fc\u0003\n\u0005\u0000"+
		"\u00fb\u00fa\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000"+
		"\u00fc\u0015\u0001\u0000\u0000\u0000\u00fd\u00ff\u0003\u0004\u0002\u0000"+
		"\u00fe\u00fd\u0001\u0000\u0000\u0000\u00fe\u00ff\u0001\u0000\u0000\u0000"+
		"\u00ff\u0100\u0001\u0000\u0000\u0000\u0100\u0101\u0005\u0012\u0000\u0000"+
		"\u0101\u0103\u0005\u0017\u0000\u0000\u0102\u0104\u0003\f\u0006\u0000\u0103"+
		"\u0102\u0001\u0000\u0000\u0000\u0103\u0104\u0001\u0000\u0000\u0000\u0104"+
		"\u011a\u0001\u0000\u0000\u0000\u0105\u0107\u0005\u0013\u0000\u0000\u0106"+
		"\u0105\u0001\u0000\u0000\u0000\u0106\u0107\u0001\u0000\u0000\u0000\u0107"+
		"\u0108\u0001\u0000\u0000\u0000\u0108\u010a\u0005\n\u0000\u0000\u0109\u010b"+
		"\u0005\u0013\u0000\u0000\u010a\u0109\u0001\u0000\u0000\u0000\u010a\u010b"+
		"\u0001\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010c\u0117"+
		"\u0003\u0018\f\u0000\u010d\u010f\u0005\u0013\u0000\u0000\u010e\u010d\u0001"+
		"\u0000\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000\u010f\u0110\u0001"+
		"\u0000\u0000\u0000\u0110\u0112\u0005\u0007\u0000\u0000\u0111\u0113\u0005"+
		"\u0013\u0000\u0000\u0112\u0111\u0001\u0000\u0000\u0000\u0112\u0113\u0001"+
		"\u0000\u0000\u0000\u0113\u0114\u0001\u0000\u0000\u0000\u0114\u0116\u0003"+
		"\u0018\f\u0000\u0115\u010e\u0001\u0000\u0000\u0000\u0116\u0119\u0001\u0000"+
		"\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0117\u0118\u0001\u0000"+
		"\u0000\u0000\u0118\u011b\u0001\u0000\u0000\u0000\u0119\u0117\u0001\u0000"+
		"\u0000\u0000\u011a\u0106\u0001\u0000\u0000\u0000\u011a\u011b\u0001\u0000"+
		"\u0000\u0000\u011b\u0131\u0001\u0000\u0000\u0000\u011c\u011e\u0005\u0004"+
		"\u0000\u0000\u011d\u011f\u0005\u0013\u0000\u0000\u011e\u011d\u0001\u0000"+
		"\u0000\u0000\u011e\u011f\u0001\u0000\u0000\u0000\u011f\u0122\u0001\u0000"+
		"\u0000\u0000\u0120\u0123\u0003\u0010\b\u0000\u0121\u0123\u0003\u0014\n"+
		"\u0000\u0122\u0120\u0001\u0000\u0000\u0000\u0122\u0121\u0001\u0000\u0000"+
		"\u0000\u0123\u0129\u0001\u0000\u0000\u0000\u0124\u0125\u0005\u0013\u0000"+
		"\u0000\u0125\u0128\u0003\u0010\b\u0000\u0126\u0128\u0003\u0014\n\u0000"+
		"\u0127\u0124\u0001\u0000\u0000\u0000\u0127\u0126\u0001\u0000\u0000\u0000"+
		"\u0128\u012b\u0001\u0000\u0000\u0000\u0129\u0127\u0001\u0000\u0000\u0000"+
		"\u0129\u012a\u0001\u0000\u0000\u0000\u012a\u012d\u0001\u0000\u0000\u0000"+
		"\u012b\u0129\u0001\u0000\u0000\u0000\u012c\u012e\u0005\u0013\u0000\u0000"+
		"\u012d\u012c\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000\u0000\u0000"+
		"\u012e\u012f\u0001\u0000\u0000\u0000\u012f\u0130\u0005\u0005\u0000\u0000"+
		"\u0130\u0132\u0001\u0000\u0000\u0000\u0131\u011c\u0001\u0000\u0000\u0000"+
		"\u0131\u0132\u0001\u0000\u0000\u0000\u0132\u0017\u0001\u0000\u0000\u0000"+
		"\u0133\u0141\u0005\u0017\u0000\u0000\u0134\u0136\u0005\u0002\u0000\u0000"+
		"\u0135\u0137\u0005\u0013\u0000\u0000\u0136\u0135\u0001\u0000\u0000\u0000"+
		"\u0136\u0137\u0001\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000\u0000"+
		"\u0138\u013a\u0003\u0018\f\u0000\u0139\u013b\u0005\u0013\u0000\u0000\u013a"+
		"\u0139\u0001\u0000\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b"+
		"\u013c\u0001\u0000\u0000\u0000\u013c\u013d\u0005\u0003\u0000\u0000\u013d"+
		"\u0141\u0001\u0000\u0000\u0000\u013e\u0141\u0003\u001e\u000f\u0000\u013f"+
		"\u0141\u0003\u001a\r\u0000\u0140\u0133\u0001\u0000\u0000\u0000\u0140\u0134"+
		"\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0140\u013f"+
		"\u0001\u0000\u0000\u0000\u0141\u0143\u0001\u0000\u0000\u0000\u0142\u0144"+
		"\u0003\u001c\u000e\u0000\u0143\u0142\u0001\u0000\u0000\u0000\u0143\u0144"+
		"\u0001\u0000\u0000\u0000\u0144\u0019\u0001\u0000\u0000\u0000\u0145\u0146"+
		"\u0005\u0017\u0000\u0000\u0146\u0147\u0003\u000e\u0007\u0000\u0147\u001b"+
		"\u0001\u0000\u0000\u0000\u0148\u0149\u0005\u000b\u0000\u0000\u0149\u001d"+
		"\u0001\u0000\u0000\u0000\u014a\u015c\u0005\u0002\u0000\u0000\u014b\u014d"+
		"\u0005\u0013\u0000\u0000\u014c\u014b\u0001\u0000\u0000\u0000\u014c\u014d"+
		"\u0001\u0000\u0000\u0000\u014d\u014e\u0001\u0000\u0000\u0000\u014e\u0159"+
		"\u0003\u0018\f\u0000\u014f\u0151\u0005\u0013\u0000\u0000\u0150\u014f\u0001"+
		"\u0000\u0000\u0000\u0150\u0151\u0001\u0000\u0000\u0000\u0151\u0152\u0001"+
		"\u0000\u0000\u0000\u0152\u0154\u0005\u0007\u0000\u0000\u0153\u0155\u0005"+
		"\u0013\u0000\u0000\u0154\u0153\u0001\u0000\u0000\u0000\u0154\u0155\u0001"+
		"\u0000\u0000\u0000\u0155\u0156\u0001\u0000\u0000\u0000\u0156\u0158\u0003"+
		"\u0018\f\u0000\u0157\u0150\u0001\u0000\u0000\u0000\u0158\u015b\u0001\u0000"+
		"\u0000\u0000\u0159\u0157\u0001\u0000\u0000\u0000\u0159\u015a\u0001\u0000"+
		"\u0000\u0000\u015a\u015d\u0001\u0000\u0000\u0000\u015b\u0159\u0001\u0000"+
		"\u0000\u0000\u015c\u014c\u0001\u0000\u0000\u0000\u015c\u015d\u0001\u0000"+
		"\u0000\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u015f\u0005\u0003"+
		"\u0000\u0000\u015f\u0161\u0005\f\u0000\u0000\u0160\u0162\u0005\u0013\u0000"+
		"\u0000\u0161\u0160\u0001\u0000\u0000\u0000\u0161\u0162\u0001\u0000\u0000"+
		"\u0000\u0162\u0163\u0001\u0000\u0000\u0000\u0163\u0164\u0003\u0018\f\u0000"+
		"\u0164\u001f\u0001\u0000\u0000\u0000\u0165\u0168\u0003\"\u0011\u0000\u0166"+
		"\u0168\u0003\b\u0004\u0000\u0167\u0165\u0001\u0000\u0000\u0000\u0167\u0166"+
		"\u0001\u0000\u0000\u0000\u0168!\u0001\u0000\u0000\u0000\u0169\u0178\u0003"+
		"\u0006\u0003\u0000\u016a\u0178\u0005\u0016\u0000\u0000\u016b\u016d\u0005"+
		"\u0002\u0000\u0000\u016c\u016e\u0005\u0013\u0000\u0000\u016d\u016c\u0001"+
		"\u0000\u0000\u0000\u016d\u016e\u0001\u0000\u0000\u0000\u016e\u016f\u0001"+
		"\u0000\u0000\u0000\u016f\u0171\u0003\"\u0011\u0000\u0170\u0172\u0005\u0013"+
		"\u0000\u0000\u0171\u0170\u0001\u0000\u0000\u0000\u0171\u0172\u0001\u0000"+
		"\u0000\u0000\u0172\u0173\u0001\u0000\u0000\u0000\u0173\u0174\u0005\u0003"+
		"\u0000\u0000\u0174\u0178\u0001\u0000\u0000\u0000\u0175\u0178\u0005\u0017"+
		"\u0000\u0000\u0176\u0178\u0003$\u0012\u0000\u0177\u0169\u0001\u0000\u0000"+
		"\u0000\u0177\u016a\u0001\u0000\u0000\u0000\u0177\u016b\u0001\u0000\u0000"+
		"\u0000\u0177\u0175\u0001\u0000\u0000\u0000\u0177\u0176\u0001\u0000\u0000"+
		"\u0000\u0178\u017a\u0001\u0000\u0000\u0000\u0179\u017b\u0003(\u0014\u0000"+
		"\u017a\u0179\u0001\u0000\u0000\u0000\u017a\u017b\u0001\u0000\u0000\u0000"+
		"\u017b\u017d\u0001\u0000\u0000\u0000\u017c\u017e\u0003&\u0013\u0000\u017d"+
		"\u017c\u0001\u0000\u0000\u0000\u017d\u017e\u0001\u0000\u0000\u0000\u017e"+
		"#\u0001\u0000\u0000\u0000\u017f\u0181\u0005\u0004\u0000\u0000\u0180\u0182"+
		"\u0005\u0013\u0000\u0000\u0181\u0180\u0001\u0000\u0000\u0000\u0181\u0182"+
		"\u0001\u0000\u0000\u0000\u0182\u0183\u0001\u0000\u0000\u0000\u0183\u018e"+
		"\u0003\u0012\t\u0000\u0184\u0186\u0005\u0013\u0000\u0000\u0185\u0184\u0001"+
		"\u0000\u0000\u0000\u0185\u0186\u0001\u0000\u0000\u0000\u0186\u0187\u0001"+
		"\u0000\u0000\u0000\u0187\u0189\u0005\u0007\u0000\u0000\u0188\u018a\u0005"+
		"\u0013\u0000\u0000\u0189\u0188\u0001\u0000\u0000\u0000\u0189\u018a\u0001"+
		"\u0000\u0000\u0000\u018a\u018b\u0001\u0000\u0000\u0000\u018b\u018d\u0003"+
		"\u0012\t\u0000\u018c\u0185\u0001\u0000\u0000\u0000\u018d\u0190\u0001\u0000"+
		"\u0000\u0000\u018e\u018c\u0001\u0000\u0000\u0000\u018e\u018f\u0001\u0000"+
		"\u0000\u0000\u018f\u0192\u0001\u0000\u0000\u0000\u0190\u018e\u0001\u0000"+
		"\u0000\u0000\u0191\u0193\u0005\u0013\u0000\u0000\u0192\u0191\u0001\u0000"+
		"\u0000\u0000\u0192\u0193\u0001\u0000\u0000\u0000\u0193\u0194\u0001\u0000"+
		"\u0000\u0000\u0194\u0196\u0005\f\u0000\u0000\u0195\u0197\u0005\u0013\u0000"+
		"\u0000\u0196\u0195\u0001\u0000\u0000\u0000\u0196\u0197\u0001\u0000\u0000"+
		"\u0000\u0197\u0199\u0001\u0000\u0000\u0000\u0198\u019a\u0003 \u0010\u0000"+
		"\u0199\u0198\u0001\u0000\u0000\u0000\u0199\u019a\u0001\u0000\u0000\u0000"+
		"\u019a\u019f\u0001\u0000\u0000\u0000\u019b\u019c\u0005\u0013\u0000\u0000"+
		"\u019c\u019e\u0003 \u0010\u0000\u019d\u019b\u0001\u0000\u0000\u0000\u019e"+
		"\u01a1\u0001\u0000\u0000\u0000\u019f\u019d\u0001\u0000\u0000\u0000\u019f"+
		"\u01a0\u0001\u0000\u0000\u0000\u01a0\u01a3\u0001\u0000\u0000\u0000\u01a1"+
		"\u019f\u0001\u0000\u0000\u0000\u01a2\u01a4\u0005\u0013\u0000\u0000\u01a3"+
		"\u01a2\u0001\u0000\u0000\u0000\u01a3\u01a4\u0001\u0000\u0000\u0000\u01a4"+
		"\u01a5\u0001\u0000\u0000\u0000\u01a5\u01a6\u0005\u0005\u0000\u0000\u01a6"+
		"\u01d4\u0001\u0000\u0000\u0000\u01a7\u01a9\u0005\u0004\u0000\u0000\u01a8"+
		"\u01aa\u0005\u0013\u0000\u0000\u01a9\u01a8\u0001\u0000\u0000\u0000\u01a9"+
		"\u01aa\u0001\u0000\u0000\u0000\u01aa\u01c0\u0001\u0000\u0000\u0000\u01ab"+
		"\u01ad\u0005\u0013\u0000\u0000\u01ac\u01ab\u0001\u0000\u0000\u0000\u01ac"+
		"\u01ad\u0001\u0000\u0000\u0000\u01ad\u01ae\u0001\u0000\u0000\u0000\u01ae"+
		"\u01b9\u0005\u0017\u0000\u0000\u01af\u01b1\u0005\u0013\u0000\u0000\u01b0"+
		"\u01af\u0001\u0000\u0000\u0000\u01b0\u01b1\u0001\u0000\u0000\u0000\u01b1"+
		"\u01b2\u0001\u0000\u0000\u0000\u01b2\u01b4\u0005\u0007\u0000\u0000\u01b3"+
		"\u01b5\u0005\u0013\u0000\u0000\u01b4\u01b3\u0001\u0000\u0000\u0000\u01b4"+
		"\u01b5\u0001\u0000\u0000\u0000\u01b5\u01b6\u0001\u0000\u0000\u0000\u01b6"+
		"\u01b8\u0005\u0017\u0000\u0000\u01b7\u01b0\u0001\u0000\u0000\u0000\u01b8"+
		"\u01bb\u0001\u0000\u0000\u0000\u01b9\u01b7\u0001\u0000\u0000\u0000\u01b9"+
		"\u01ba\u0001\u0000\u0000\u0000\u01ba\u01bd\u0001\u0000\u0000\u0000\u01bb"+
		"\u01b9\u0001\u0000\u0000\u0000\u01bc\u01be\u0005\u0013\u0000\u0000\u01bd"+
		"\u01bc\u0001\u0000\u0000\u0000\u01bd\u01be\u0001\u0000\u0000\u0000\u01be"+
		"\u01bf\u0001\u0000\u0000\u0000\u01bf\u01c1\u0005\f\u0000\u0000\u01c0\u01ac"+
		"\u0001\u0000\u0000\u0000\u01c0\u01c1\u0001\u0000\u0000\u0000\u01c1\u01c3"+
		"\u0001\u0000\u0000\u0000\u01c2\u01c4\u0005\u0013\u0000\u0000\u01c3\u01c2"+
		"\u0001\u0000\u0000\u0000\u01c3\u01c4\u0001\u0000\u0000\u0000\u01c4\u01c6"+
		"\u0001\u0000\u0000\u0000\u01c5\u01c7\u0003 \u0010\u0000\u01c6\u01c5\u0001"+
		"\u0000\u0000\u0000\u01c6\u01c7\u0001\u0000\u0000\u0000\u01c7\u01cc\u0001"+
		"\u0000\u0000\u0000\u01c8\u01c9\u0005\u0013\u0000\u0000\u01c9\u01cb\u0003"+
		" \u0010\u0000\u01ca\u01c8\u0001\u0000\u0000\u0000\u01cb\u01ce\u0001\u0000"+
		"\u0000\u0000\u01cc\u01ca\u0001\u0000\u0000\u0000\u01cc\u01cd\u0001\u0000"+
		"\u0000\u0000\u01cd\u01d0\u0001\u0000\u0000\u0000\u01ce\u01cc\u0001\u0000"+
		"\u0000\u0000\u01cf\u01d1\u0005\u0013\u0000\u0000\u01d0\u01cf\u0001\u0000"+
		"\u0000\u0000\u01d0\u01d1\u0001\u0000\u0000\u0000\u01d1\u01d2\u0001\u0000"+
		"\u0000\u0000\u01d2\u01d4\u0005\u0005\u0000\u0000\u01d3\u017f\u0001\u0000"+
		"\u0000\u0000\u01d3\u01a7\u0001\u0000\u0000\u0000\u01d4%\u0001\u0000\u0000"+
		"\u0000\u01d5\u01d7\u0003\u000e\u0007\u0000\u01d6\u01d5\u0001\u0000\u0000"+
		"\u0000\u01d6\u01d7\u0001\u0000\u0000\u0000\u01d7\u01d8\u0001\u0000\u0000"+
		"\u0000\u01d8\u01da\u0005\u0002\u0000\u0000\u01d9\u01db\u0005\u0013\u0000"+
		"\u0000\u01da\u01d9\u0001\u0000\u0000\u0000\u01da\u01db\u0001\u0000\u0000"+
		"\u0000\u01db\u01ed\u0001\u0000\u0000\u0000\u01dc\u01de\u0005\u0013\u0000"+
		"\u0000\u01dd\u01dc\u0001\u0000\u0000\u0000\u01dd\u01de\u0001\u0000\u0000"+
		"\u0000\u01de\u01df\u0001\u0000\u0000\u0000\u01df\u01ea\u0003\"\u0011\u0000"+
		"\u01e0\u01e2\u0005\u0013\u0000\u0000\u01e1\u01e0\u0001\u0000\u0000\u0000"+
		"\u01e1\u01e2\u0001\u0000\u0000\u0000\u01e2\u01e3\u0001\u0000\u0000\u0000"+
		"\u01e3\u01e5\u0005\u0007\u0000\u0000\u01e4\u01e6\u0005\u0013\u0000\u0000"+
		"\u01e5\u01e4\u0001\u0000\u0000\u0000\u01e5\u01e6\u0001\u0000\u0000\u0000"+
		"\u01e6\u01e7\u0001\u0000\u0000\u0000\u01e7\u01e9\u0003\"\u0011\u0000\u01e8"+
		"\u01e1\u0001\u0000\u0000\u0000\u01e9\u01ec\u0001\u0000\u0000\u0000\u01ea"+
		"\u01e8\u0001\u0000\u0000\u0000\u01ea\u01eb\u0001\u0000\u0000\u0000\u01eb"+
		"\u01ee\u0001\u0000\u0000\u0000\u01ec\u01ea\u0001\u0000\u0000\u0000\u01ed"+
		"\u01dd\u0001\u0000\u0000\u0000\u01ed\u01ee\u0001\u0000\u0000\u0000\u01ee"+
		"\u01f0\u0001\u0000\u0000\u0000\u01ef\u01f1\u0005\u0013\u0000\u0000\u01f0"+
		"\u01ef\u0001\u0000\u0000\u0000\u01f0\u01f1\u0001\u0000\u0000\u0000\u01f1"+
		"\u01f2\u0001\u0000\u0000\u0000\u01f2\u01f4\u0005\u0003\u0000\u0000\u01f3"+
		"\u01f5\u0003&\u0013\u0000\u01f4\u01f3\u0001\u0000\u0000\u0000\u01f4\u01f5"+
		"\u0001\u0000\u0000\u0000\u01f5\u01f7\u0001\u0000\u0000\u0000\u01f6\u01f8"+
		"\u0003(\u0014\u0000\u01f7\u01f6\u0001\u0000\u0000\u0000\u01f7\u01f8\u0001"+
		"\u0000\u0000\u0000\u01f8\'\u0001\u0000\u0000\u0000\u01f9\u01fb\u0005\u0013"+
		"\u0000\u0000\u01fa\u01f9\u0001\u0000\u0000\u0000\u01fa\u01fb\u0001\u0000"+
		"\u0000\u0000\u01fb\u01fc\u0001\u0000\u0000\u0000\u01fc\u01fd\u0005\r\u0000"+
		"\u0000\u01fd\u01ff\u0005\u0017\u0000\u0000\u01fe\u0200\u0003(\u0014\u0000"+
		"\u01ff\u01fe\u0001\u0000\u0000\u0000\u01ff\u0200\u0001\u0000\u0000\u0000"+
		"\u0200\u0202\u0001\u0000\u0000\u0000\u0201\u0203\u0003&\u0013\u0000\u0202"+
		"\u0201\u0001\u0000\u0000\u0000\u0202\u0203\u0001\u0000\u0000\u0000\u0203"+
		")\u0001\u0000\u0000\u0000p+27=AEHMQV^bimsvz~\u0082\u0087\u008e\u0092\u0096"+
		"\u009b\u00a1\u00a6\u00aa\u00ae\u00b5\u00b9\u00bc\u00be\u00c1\u00c7\u00cb"+
		"\u00ce\u00d1\u00d6\u00da\u00dd\u00e1\u00e5\u00ea\u00ed\u00f0\u00f5\u00f8"+
		"\u00fb\u00fe\u0103\u0106\u010a\u010e\u0112\u0117\u011a\u011e\u0122\u0127"+
		"\u0129\u012d\u0131\u0136\u013a\u0140\u0143\u014c\u0150\u0154\u0159\u015c"+
		"\u0161\u0167\u016d\u0171\u0177\u017a\u017d\u0181\u0185\u0189\u018e\u0192"+
		"\u0196\u0199\u019f\u01a3\u01a9\u01ac\u01b0\u01b4\u01b9\u01bd\u01c0\u01c3"+
		"\u01c6\u01cc\u01d0\u01d3\u01d6\u01da\u01dd\u01e1\u01e5\u01ea\u01ed\u01f0"+
		"\u01f4\u01f7\u01fa\u01ff\u0202";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}