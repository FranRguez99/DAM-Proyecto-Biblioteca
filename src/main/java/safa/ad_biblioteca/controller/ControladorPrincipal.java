package safa.ad_biblioteca.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import safa.ad_biblioteca.model.Conexion;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {
    @FXML
    private Label welcomeText;

    // Atributos
    Conexion conexion = new Conexion();


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControladorUsuarios.ejecutar();
    }

}