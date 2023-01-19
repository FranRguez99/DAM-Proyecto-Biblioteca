package safa.ad_biblioteca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import safa.ad_biblioteca.model.Conexion;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {

    // Elementos JavaFX
    @FXML
    private Button btnDevolDevolver;

    @FXML
    private Button btnDevolucionesLibros;

    @FXML
    private Button btnDevolucionesPrestamos;

    @FXML
    private Button btnDevolucionesPrincipal;

    @FXML
    private Button btnDevolucionesUsuarios;

    @FXML
    private Button btnLibrosBorrar;

    @FXML
    private Button btnLibrosBuscar;

    @FXML
    private Button btnLibrosDevoluciones;

    @FXML
    private Button btnLibrosModificar;

    @FXML
    private Button btnLibrosNuevo;

    @FXML
    private Button btnLibrosPrestamos;

    @FXML
    private Button btnLibrosPrincipal;

    @FXML
    private Button btnLibrosUsuarios;

    @FXML
    private Button btnPrestamosDevoluciones;

    @FXML
    private Button btnPrestamosLibros;

    @FXML
    private Button btnPrestamosPrestar;

    @FXML
    private Button btnPrestamosPrincipal;

    @FXML
    private Button btnPrestamosUsuarios;

    @FXML
    private Button btnPrincipalDevoluciones;

    @FXML
    private Button btnPrincipalLibros;

    @FXML
    private Button btnPrincipalPrestamos;

    @FXML
    private Button btnPrincipalUsuarios;

    @FXML
    private Button btnRegLibREgistrar;

    @FXML
    private Button btnRegLibVolver;

    @FXML
    private Button btnRegUsuarioRegistrar;

    @FXML
    private Button btnRegUsuarioVolver;

    @FXML
    private Button btnUsuariosBorrar;

    @FXML
    private Button btnUsuariosBuscar;

    @FXML
    private Button btnUsuariosDevoluciones;

    @FXML
    private Button btnUsuariosLibros;

    @FXML
    private Button btnUsuariosModificar;

    @FXML
    private Button btnUsuariosNuevo;

    @FXML
    private Button btnUsuariosPrestamos;

    @FXML
    private Button btnUsuariosPrincipal;

    @FXML
    private Pane panelDevoluciones;

    @FXML
    private Pane panelLibros;

    @FXML
    private Pane panelPrestamos;

    @FXML
    private Pane panelPrincipal;

    @FXML
    private Pane panelRegistroLibros;

    @FXML
    private Pane panelRegistroUsuarios;

    @FXML
    private Pane panelUsuarios;

    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfDNI;

    @FXML
    private TextField tfDomicilio;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;


    // Atributos
    Conexion conexion = new Conexion();
    Boolean editaUsuario;

    // Métodos
    @FXML
    void aceptarUsuarios(ActionEvent event) {

    }

    @FXML
    void borrarUsuario(ActionEvent event) {

    }

    @FXML
    void modificarUsuario() {
        cambiarVistaFormUsuario();
        editaUsuario = true;
    }

    @FXML
    void nuevoUsuario() {
        cambiarVistaFormUsuario();
        editaUsuario = false;
    }

    void cambiarVistaFormUsuario() {
        panelUsuarios.setVisible(false);
        panelRegistroUsuarios.setVisible(true);
    }

    ArrayList<Object> leeValoresUsuario() {
        ArrayList<Object> valores = new ArrayList<Object>();
        valores.add(leerDNI());
        valores.add(leerNombre());
        valores.add(leerApellidos());
        valores.add(leerDomicilio());
        valores.add(leerTelefono());
        valores.add(leerEmail());
        return valores;
    }

    String leerDNI() {
        String dni = tfDNI.getText();
        if (compruebaDNI(dni)) {
            return dni;
        } else {
            return null;
        }
    }

    boolean compruebaDNI(String dni) {
        return dni.length() == 9 && dni.matches("[0-9]{8}[A-Za-z]") && Character.isLetter(dni.charAt(8));
    }

    String leerNombre() {
        return tfNombre.getText();
    }

    String leerApellidos() {
        return tfApellidos.getText();
    }

    String leerDomicilio() {
        return tfDomicilio.getText();
    }

    String leerTelefono() {
        return tfTelefono.getText();
    }

    String leerEmail() {
        return tfEmail.getText();
    }

    void insertarUsuario() throws SQLException {
        ArrayList<Object> valores = leeValoresUsuario();

        String sql = "INSERT INTO usuarios (DNI, nombre, apellidos, domicilio, telefono, email, sancionado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexion.conexion.prepareStatement(sql);

        /* todo
        stmt.setString(1, value1);
        stmt.setString(2, value2);
        stmt.setString(3, value3);
        stmt.setString(4, value4);
        stmt.setString(5, value5);
        stmt.setString(6, value6);
        stmt.setInt(7, value7);
        */

        stmt.executeUpdate(); // Ejecutar la consulta

        // Cerrar las conexiones
        stmt.close();
        conexion.conexion.close();
    }

    @FXML
    void verPrincipal() {
        panelPrincipal.setVisible(true);
        panelLibros.setVisible(false);
        panelPrestamos.setVisible(false);
        panelDevoluciones.setVisible(false);
        panelUsuarios.setVisible(false);
    }

    @FXML
    void verDevoluciones() {
        panelPrincipal.setVisible(false);
        panelLibros.setVisible(false);
        panelPrestamos.setVisible(false);
        panelDevoluciones.setVisible(true);
        panelUsuarios.setVisible(false);
    }

    @FXML
    void verLibros() {
        panelPrincipal.setVisible(false);
        panelLibros.setVisible(true);
        panelPrestamos.setVisible(false);
        panelDevoluciones.setVisible(false);
        panelUsuarios.setVisible(false);
    }

    @FXML
    void verPrestamos() {
        panelPrincipal.setVisible(false);
        panelLibros.setVisible(false);
        panelPrestamos.setVisible(true);
        panelDevoluciones.setVisible(false);
        panelUsuarios.setVisible(false);
    }

    @FXML
    void verUsuarios() {
        panelPrincipal.setVisible(false);
        panelLibros.setVisible(false);
        panelPrestamos.setVisible(false);
        panelDevoluciones.setVisible(false);
        panelUsuarios.setVisible(true);
    }

    @FXML
    void volverLibros(ActionEvent event) {
        verLibros();
        // Vaciar campos
    }

    @FXML
    void volverUsuario(ActionEvent event) {
        verUsuarios();
        // Vaciar campos
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}