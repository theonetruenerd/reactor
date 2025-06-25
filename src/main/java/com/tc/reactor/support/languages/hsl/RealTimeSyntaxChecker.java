package com.tc.reactor.support.languages.hsl;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import com.tc.reactor.support.languages.hsl.syntaxchecker.*;
import org.antlr.v4.runtime.*;

public class RealTimeSyntaxChecker {

    @FXML
    private TextArea outputTextArea;

    public void checkSyntax(String sourceCode) {
        CharStream input = CharStreams.fromString(sourceCode);
        HslLexerLexer lexer = new HslLexerLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HslLexerParser parser = new HslLexerParser(tokens);

        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                                    int charPositionInLine, String msg, RecognitionException e) {
                String errorMessage = String.format("Syntax Error at line %d:%d - %s%n", line, charPositionInLine, msg);

                // Update the output TextArea in a thread-safe way
                Platform.runLater(() -> outputTextArea.appendText(errorMessage));
            }

        });
        parser.hslFile();
    }

}