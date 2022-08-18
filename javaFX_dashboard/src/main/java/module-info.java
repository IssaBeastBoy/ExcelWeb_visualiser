module com.example.dashboard {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires poi;
    requires poi.ooxml;
    requires com.google.common;
    requires xlsx.streamer;
    requires javafx.graphics;

    opens com.example.dashboard;
    exports com.example.dashboard;
}