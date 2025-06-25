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

        for (Token token : lexer.getAllTokens()) {
            System.out.printf("Token: '%s', Type: %d%n", token.getText(), token.getType());
        }
        lexer.reset(); // Reset the lexer so the parser can use it

        // Add custom error listener BEFORE parsing
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                                    int charPositionInLine, String msg, RecognitionException e) {
                String errorMessage = String.format("Syntax Error at line %d:%d - %s%n", line, charPositionInLine, msg);

                System.out.println(errorMessage); // Debugging output
                // Update the output TextArea in a thread-safe way
                Platform.runLater(() -> outputTextArea.appendText(errorMessage));
            }
        });

        // Parse the code (execute the entry rule)
        parser.hslFile();
    }

}