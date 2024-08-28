module com.wordpress.prasanthxyz.pvorg {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;

    opens com.wordpress.prasanthxyz.pvorg to javafx.fxml,com.fasterxml.jackson.databind;
    exports com.wordpress.prasanthxyz.pvorg;
    exports com.wordpress.prasanthxyz.pvorg.models;
}