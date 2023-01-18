module safa.ad_biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens safa.ad_biblioteca to javafx.fxml;
    exports safa.ad_biblioteca;
    exports safa.ad_biblioteca.model;
    opens safa.ad_biblioteca.model to javafx.fxml;
}