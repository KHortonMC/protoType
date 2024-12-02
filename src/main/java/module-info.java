module edu.miracosta.cs112.prototype {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens prototype to javafx.fxml;
    exports prototype;
    exports prototype.projectiles;
    opens prototype.projectiles to javafx.fxml;
    exports prototype.objects;
    opens prototype.objects to javafx.fxml;
    exports prototype.controllers;
    opens prototype.controllers to javafx.fxml;
    exports prototype.support;
    opens prototype.support to javafx.fxml;
    exports prototype.managers;
    opens prototype.managers to javafx.fxml;
}