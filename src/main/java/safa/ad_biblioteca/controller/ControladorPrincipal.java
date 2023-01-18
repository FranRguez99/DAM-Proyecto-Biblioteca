package safa.ad_biblioteca.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import safa.ad_biblioteca.model.Conexion;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {

    // Elementos JavaFX
    @FXML
    private Button btnPrincipalLibros;

    @FXML
    private Pane panelRegistroLibros;

    @FXML
    private Pane panelUsuarios;

    @FXML
    private Button btnRegLibVolver;

    @FXML
    private Button btnPrestamosPrestar;

    @FXML
    private Button btnPrincipalUsuarios;

    @FXML
    private Button btnUsuariosPrincipal;

    @FXML
    private Button btnDevolLibros;

    @FXML
    private Button btnPrestamosDevol;

    @FXML
    private Button btnUsuariosModif;

    @FXML
    private Pane panelDevol;

    @FXML
    private Button btnPrincipalDevoluciones;

    @FXML
    private Button btnPrincipalPrestamos;

    @FXML
    private Button btnLibrosModificar;

    @FXML
    private Button btnUsuariosBorrar;

    @FXML
    private Button btnRegLibREgistrar;

    @FXML
    private Button btnLibrosUsuarios;

    @FXML
    private Pane panelRegistroUsuarios;

    @FXML
    private Button btn;

    @FXML
    private Button btnLibrosNuevo;

    @FXML
    private Button btnPrestamosUsuarios;

    @FXML
    private Button btnDevolPrincipal;

    @FXML
    private Button btnDevolDevolver;

    @FXML
    private Pane panelPrincipal;

    @FXML
    private Button btnDevolUsuarios;

    @FXML
    private Button btnLibrosBorrar;

    @FXML
    private Button btnUsuariosLibros;

    @FXML
    private Button btnUsuariosNuevo;

    @FXML
    private Button btnDevolPrestamos;

    @FXML
    private Button btnUsuariosPrestamos;

    @FXML
    private Button btnUsuariosBuscar;

    @FXML
    private Button btnUsuariosPrincipal1;

    @FXML
    private Button btnRegUsuarioVolver;

    @FXML
    private Pane panelLibros;

    @FXML
    private Button btnLibrosPrincipal;

    @FXML
    private Button btnRegUsuarioRegistrar;

    @FXML
    private Pane panelPrestamos;

    @FXML
    private Button btnLibrosPrestamos;

    @FXML
    private Button btnLibrosDevoluciones;

    @FXML
    private Button btnUsuariosDevoluciones;

    // Atributos
    Conexion conexion = new Conexion();

    // Métodos
    @FXML
    void verDevoluciones() {
        // todo
    }

    @FXML
    void verLibros(ActionEvent event) {
        panelPrincipal.setVisible(false);
        panelLibros.setVisible(true);
    }

    @FXML
    void verPrestamos(ActionEvent event) {
        // todo
    }

    @FXML
    void verUsuarios() {
        panelPrincipal.setVisible(false);
        panelUsuarios.setVisible(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControladorUsuarios.ejecutar();
    }

}