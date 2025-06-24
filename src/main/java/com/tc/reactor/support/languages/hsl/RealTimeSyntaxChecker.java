package com.tc.reactor.support.languages.hsl;

import org.antlr.v4.runtime.*;
import com.tc.reactor.support.languages.hsl.syntaxchecker.*;

public class RealTimeSyntaxChecker {
    public void checkSyntax(String sourceCode) {
        CharStream input = CharStreams.fromString(sourceCode);
        HslLexerLexer lexer = new HslLexerLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HslLexerParser parser = new HslLexerParser(tokens);

        parser.removeErrorListeners(); // Remove default error listeners
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                                    int charPositionInLine, String msg, RecognitionException e) {
                System.err.printf("Syntax Error at line %d:%d - %s%n", line, charPositionInLine, msg);
            }
        });
        parser.hslFile();
    }
}