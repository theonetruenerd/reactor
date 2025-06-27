// Generated from C:/Users/TarunChapman/IdeaProjects/reactor/src/main/java/com/tc/reactor/support/languages/xaml/syntaxchecker/Xaml.g4 by ANTLR 4.13.2
package com.tc.reactor.support.languages.xaml.syntaxchecker;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class XamlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, OPEN=4, CLOSE=5, CLOSE_TAG=6, SELF_CLOSING=7, 
		NAME=8, STRING=9, WS=10;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "OPEN", "CLOSE", "CLOSE_TAG", "SELF_CLOSING", 
			"NAME", "STRING", "WS"
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


	public XamlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Xaml.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\n=\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0005\u0007,\b\u0007\n\u0007\f\u0007/\t\u0007\u0001"+
		"\b\u0001\b\u0005\b3\b\b\n\b\f\b6\t\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u00014\u0000\n\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0001\u0000\u0003"+
		"\u0004\u0000::AZ__az\u0005\u0000--0:AZ__az\u0003\u0000\t\n\r\r  >\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0001\u0015"+
		"\u0001\u0000\u0000\u0000\u0003\u0017\u0001\u0000\u0000\u0000\u0005\u001d"+
		"\u0001\u0000\u0000\u0000\u0007\u001f\u0001\u0000\u0000\u0000\t!\u0001"+
		"\u0000\u0000\u0000\u000b#\u0001\u0000\u0000\u0000\r&\u0001\u0000\u0000"+
		"\u0000\u000f)\u0001\u0000\u0000\u0000\u00110\u0001\u0000\u0000\u0000\u0013"+
		"9\u0001\u0000\u0000\u0000\u0015\u0016\u0005=\u0000\u0000\u0016\u0002\u0001"+
		"\u0000\u0000\u0000\u0017\u0018\u0005x\u0000\u0000\u0018\u0019\u0005m\u0000"+
		"\u0000\u0019\u001a\u0005l\u0000\u0000\u001a\u001b\u0005n\u0000\u0000\u001b"+
		"\u001c\u0005s\u0000\u0000\u001c\u0004\u0001\u0000\u0000\u0000\u001d\u001e"+
		"\u0005:\u0000\u0000\u001e\u0006\u0001\u0000\u0000\u0000\u001f \u0005<"+
		"\u0000\u0000 \b\u0001\u0000\u0000\u0000!\"\u0005>\u0000\u0000\"\n\u0001"+
		"\u0000\u0000\u0000#$\u0005<\u0000\u0000$%\u0005/\u0000\u0000%\f\u0001"+
		"\u0000\u0000\u0000&\'\u0005/\u0000\u0000\'(\u0005>\u0000\u0000(\u000e"+
		"\u0001\u0000\u0000\u0000)-\u0007\u0000\u0000\u0000*,\u0007\u0001\u0000"+
		"\u0000+*\u0001\u0000\u0000\u0000,/\u0001\u0000\u0000\u0000-+\u0001\u0000"+
		"\u0000\u0000-.\u0001\u0000\u0000\u0000.\u0010\u0001\u0000\u0000\u0000"+
		"/-\u0001\u0000\u0000\u000004\u0005\"\u0000\u000013\t\u0000\u0000\u0000"+
		"21\u0001\u0000\u0000\u000036\u0001\u0000\u0000\u000045\u0001\u0000\u0000"+
		"\u000042\u0001\u0000\u0000\u000057\u0001\u0000\u0000\u000064\u0001\u0000"+
		"\u0000\u000078\u0005\"\u0000\u00008\u0012\u0001\u0000\u0000\u00009:\u0007"+
		"\u0002\u0000\u0000:;\u0001\u0000\u0000\u0000;<\u0006\t\u0000\u0000<\u0014"+
		"\u0001\u0000\u0000\u0000\u0003\u0000-4\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}