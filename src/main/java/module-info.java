module ku.cs {
    requires javafx.controls;
    requires opencsv;
    requires javafx.fxml;

    opens ku.cs to javafx.fxml;
    exports ku.cs;

    exports ku.cs.shop.controllers;
    opens ku.cs.shop.controllers to javafx.fxml;
    exports ku.cs.shop.services;
    opens ku.cs.shop.services to javafx.fxml;
}