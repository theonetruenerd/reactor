package com.tc.reactor.support.languages.hsl;

import com.tc.reactor.ui.MainView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import com.tc.reactor.support.languages.hsl.syntaxchecker.*;
import org.antlr.v4.runtime.*;

public class RealTimeSyntaxChecker {

    private MainView mainView;

    public void SetMainView (MainView mainView) {
        this.mainView = mainView;
    }

    public void checkSyntax(String sourceCode) {
        CharStream input = CharStreams.fromString(sourceCode);
        HslLexer lexer = new HslLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HslParser parser = new HslParser(tokens);

        // Add custom error listener BEFORE parsing
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                                    int charPositionInLine, String msg, RecognitionException e) {
                String errorMessage = String.format("Syntax Error at line %d:%d - %s%n", line, charPositionInLine, msg);

                // Update the output TextArea in a thread-safe way
                Platform.runLater(() -> {
                    if (mainView.outputTextArea != null) {
                        mainView.outputTextArea.appendText(errorMessage);
                    } else {
                        mainView.logsTextArea.appendText("\n> OutputTextArea is not initialized.");
                    }
                });
            }
        });

        // Parse the code (execute the entry rule)
        parser.hslFile();
    }
}