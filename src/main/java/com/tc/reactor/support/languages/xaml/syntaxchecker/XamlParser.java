// Generated from C:/Users/TarunChapman/IdeaProjects/reactor/src/main/java/com/tc/reactor/support/languages/xaml/syntaxchecker/Xaml.g4 by ANTLR 4.13.2
package com.tc.reactor.support.languages.xaml.syntaxchecker;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class XamlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, OPEN=4, CLOSE=5, CLOSE_TAG=6, SELF_CLOSING=7, 
		NAME=8, STRING=9, WS=10;
	public static final int
		RULE_xamlFile = 0, RULE_element = 1, RULE_tagName = 2, RULE_attributeList = 3, 
		RULE_attribute = 4, RULE_namespace = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"xamlFile", "element", "tagName", "attributeList", "attribute", "namespace"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'xmlns'", "':'", "'<'", "'>'", "'</'", "'/>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "OPEN", "CLOSE", "CLOSE_TAG", "SELF_CLOSING", 
			"NAME", "STRING", "WS"
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
	public String getGrammarFileName() { return "Xaml.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public XamlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class XamlFileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(XamlParser.EOF, 0); }
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public XamlFileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xamlFile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XamlListener ) ((XamlListener)listener).enterXamlFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XamlListener ) ((XamlListener)listener).exitXamlFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XamlVisitor ) return ((XamlVisitor<? extends T>)visitor).visitXamlFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XamlFileContext xamlFile() throws RecognitionException {
		XamlFileContext _localctx = new XamlFileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_xamlFile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPEN) {
				{
				{
				setState(12);
				element();
				}
				}
				setState(17);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(18);
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
	public static class ElementContext extends ParserRuleContext {
		public TerminalNode OPEN() { return getToken(XamlParser.OPEN, 0); }
		public TagNameContext tagName() {
			return getRuleContext(TagNameContext.class,0);
		}
		public TerminalNode CLOSE() { return getToken(XamlParser.CLOSE, 0); }
		public TerminalNode CLOSE_TAG() { return getToken(XamlParser.CLOSE_TAG, 0); }
		public TerminalNode SELF_CLOSING() { return getToken(XamlParser.SELF_CLOSING, 0); }
		public AttributeListContext attributeList() {
			return getRuleContext(AttributeListContext.class,0);
		}
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XamlListener ) ((XamlListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XamlListener ) ((XamlListener)listener).exitElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XamlVisitor ) return ((XamlVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_element);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(OPEN);
			setState(21);
			tagName();
			setState(23);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(22);
				attributeList();
				}
				break;
			}
			setState(34);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CLOSE:
				{
				setState(25);
				match(CLOSE);
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPEN) {
					{
					{
					setState(26);
					element();
					}
					}
					setState(31);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(32);
				match(CLOSE_TAG);
				}
				break;
			case SELF_CLOSING:
				{
				setState(33);
				match(SELF_CLOSING);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class TagNameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(XamlParser.NAME, 0); }
		public TagNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tagName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XamlListener ) ((XamlListener)listener).enterTagName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XamlListener ) ((XamlListener)listener).exitTagName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XamlVisitor ) return ((XamlVisitor<? extends T>)visitor).visitTagName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TagNameContext tagName() throws RecognitionException {
		TagNameContext _localctx = new TagNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_tagName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(NAME);
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
	public static class AttributeListContext extends ParserRuleContext {
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public AttributeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XamlListener ) ((XamlListener)listener).enterAttributeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XamlListener ) ((XamlListener)listener).exitAttributeList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XamlVisitor ) return ((XamlVisitor<? extends T>)visitor).visitAttributeList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeListContext attributeList() throws RecognitionException {
		AttributeListContext _localctx = new AttributeListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_attributeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NAME) {
				{
				{
				setState(38);
				attribute();
				}
				}
				setState(43);
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
	public static class AttributeContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(XamlParser.NAME, 0); }
		public TerminalNode STRING() { return getToken(XamlParser.STRING, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XamlListener ) ((XamlListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XamlListener ) ((XamlListener)listener).exitAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XamlVisitor ) return ((XamlVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(NAME);
			setState(45);
			match(T__0);
			setState(46);
			match(STRING);
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
	public static class NamespaceContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(XamlParser.STRING, 0); }
		public TerminalNode NAME() { return getToken(XamlParser.NAME, 0); }
		public NamespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XamlListener ) ((XamlListener)listener).enterNamespace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XamlListener ) ((XamlListener)listener).exitNamespace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XamlVisitor ) return ((XamlVisitor<? extends T>)visitor).visitNamespace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceContext namespace() throws RecognitionException {
		NamespaceContext _localctx = new NamespaceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_namespace);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(T__1);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(49);
				match(T__2);
				setState(50);
				match(NAME);
				}
			}

			setState(53);
			match(T__0);
			setState(54);
			match(STRING);
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
		"\u0004\u0001\n9\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0001\u0000\u0005\u0000\u000e\b\u0000\n\u0000\f\u0000"+
		"\u0011\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001\u0018\b\u0001\u0001\u0001\u0001\u0001\u0005\u0001\u001c\b"+
		"\u0001\n\u0001\f\u0001\u001f\t\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"#\b\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0005\u0003(\b\u0003\n\u0003"+
		"\f\u0003+\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0003\u00054\b\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0000\u0000\u0006\u0000\u0002\u0004\u0006"+
		"\b\n\u0000\u00008\u0000\u000f\u0001\u0000\u0000\u0000\u0002\u0014\u0001"+
		"\u0000\u0000\u0000\u0004$\u0001\u0000\u0000\u0000\u0006)\u0001\u0000\u0000"+
		"\u0000\b,\u0001\u0000\u0000\u0000\n0\u0001\u0000\u0000\u0000\f\u000e\u0003"+
		"\u0002\u0001\u0000\r\f\u0001\u0000\u0000\u0000\u000e\u0011\u0001\u0000"+
		"\u0000\u0000\u000f\r\u0001\u0000\u0000\u0000\u000f\u0010\u0001\u0000\u0000"+
		"\u0000\u0010\u0012\u0001\u0000\u0000\u0000\u0011\u000f\u0001\u0000\u0000"+
		"\u0000\u0012\u0013\u0005\u0000\u0000\u0001\u0013\u0001\u0001\u0000\u0000"+
		"\u0000\u0014\u0015\u0005\u0004\u0000\u0000\u0015\u0017\u0003\u0004\u0002"+
		"\u0000\u0016\u0018\u0003\u0006\u0003\u0000\u0017\u0016\u0001\u0000\u0000"+
		"\u0000\u0017\u0018\u0001\u0000\u0000\u0000\u0018\"\u0001\u0000\u0000\u0000"+
		"\u0019\u001d\u0005\u0005\u0000\u0000\u001a\u001c\u0003\u0002\u0001\u0000"+
		"\u001b\u001a\u0001\u0000\u0000\u0000\u001c\u001f\u0001\u0000\u0000\u0000"+
		"\u001d\u001b\u0001\u0000\u0000\u0000\u001d\u001e\u0001\u0000\u0000\u0000"+
		"\u001e \u0001\u0000\u0000\u0000\u001f\u001d\u0001\u0000\u0000\u0000 #"+
		"\u0005\u0006\u0000\u0000!#\u0005\u0007\u0000\u0000\"\u0019\u0001\u0000"+
		"\u0000\u0000\"!\u0001\u0000\u0000\u0000#\u0003\u0001\u0000\u0000\u0000"+
		"$%\u0005\b\u0000\u0000%\u0005\u0001\u0000\u0000\u0000&(\u0003\b\u0004"+
		"\u0000\'&\u0001\u0000\u0000\u0000(+\u0001\u0000\u0000\u0000)\'\u0001\u0000"+
		"\u0000\u0000)*\u0001\u0000\u0000\u0000*\u0007\u0001\u0000\u0000\u0000"+
		"+)\u0001\u0000\u0000\u0000,-\u0005\b\u0000\u0000-.\u0005\u0001\u0000\u0000"+
		"./\u0005\t\u0000\u0000/\t\u0001\u0000\u0000\u000003\u0005\u0002\u0000"+
		"\u000012\u0005\u0003\u0000\u000024\u0005\b\u0000\u000031\u0001\u0000\u0000"+
		"\u000034\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u000056\u0005\u0001"+
		"\u0000\u000067\u0005\t\u0000\u00007\u000b\u0001\u0000\u0000\u0000\u0006"+
		"\u000f\u0017\u001d\")3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}