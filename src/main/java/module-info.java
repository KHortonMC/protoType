module prototype {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;

    requires org.junit.jupiter.api;
    requires org.junit.platform.commons;

    opens prototype to javafx.fxml;
    exports prototype;

    exports prototype.projectiles;
    opens prototype.projectiles to javafx.fxml;

    exports prototype.objects;
    opens prototype.objects to javafx.fxml, org.junit.platform.commons;

    exports prototype.controllers;
    opens prototype.controllers to javafx.fxml;

    exports prototype.support;
    opens prototype.support to javafx.fxml;

    exports prototype.managers;
    opens prototype.managers to javafx.fxml;
}