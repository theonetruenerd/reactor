module com.tc.reactor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires org.fxmisc.richtext;

    opens com.tc.reactor to javafx.fxml;
    exports com.tc.reactor;

    opens com.tc.reactor.ui to javafx.fxml;
}