module safa.ad_biblioteca {
    requires javafx.controls;
    requires javafx.fxml;


    opens safa.ad_biblioteca to javafx.fxml;
    exports safa.ad_biblioteca;
}