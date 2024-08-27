module com.wordpress.prasanthxyz.pvorg {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.wordpress.prasanthxyz.pvorg to javafx.fxml;
    exports com.wordpress.prasanthxyz.pvorg;
}