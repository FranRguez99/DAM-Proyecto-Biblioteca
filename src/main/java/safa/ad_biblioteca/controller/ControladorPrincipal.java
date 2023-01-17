package safa.ad_biblioteca.controller;

import javafx.fxml.Initializable;
import safa.ad_biblioteca.model.Conexion;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {

    // Elementos JavaFX


    // Atributos
    Conexion conexion = new Conexion();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControladorUsuarios.ejecutar();
    }

}