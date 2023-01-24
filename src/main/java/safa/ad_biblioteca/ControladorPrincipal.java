package safa.ad_biblioteca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private ComboBox<?> cBoxCategoria;

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

    @FXML
    private TextField tfISBN;

    @FXML
    private TextField tfTitulo;

    @FXML
    private TextField tfAutor;

    @FXML
    private TextField tfIdioma;

    @FXML
    private TextField tfPaginas;

    @FXML
    private TextField tfEjemplares;

    // Atributos
    Conexion conexion = new Conexion();
    Boolean editaUsuario;
    Boolean editaLibro;

    // Métodos
    @FXML
    void aceptarLibros(ActionEvent event) {

    }

    @FXML
    void borrarLibro(ActionEvent event) {

    }

    @FXML
    void modificarLibro(ActionEvent event) {
        cambiarVistaFormUsuarioL();
        editaLibro = true;
    }

    @FXML
    void nuevoLibro(ActionEvent event) {
        cambiarVistaFormUsuarioL();
        editaLibro = false;
    }

    void cambiarVistaFormUsuarioL() {
        panelLibros.setVisible(false);
        panelRegistroLibros.setVisible(true);
    }

    @FXML
    void volverLibros(ActionEvent event) {
        verLibros();
        // Vaciar campos
    }

    ArrayList<Object> leeValoresLibro() {
        ArrayList<Object> libros = new ArrayList<Object>();
        libros.add(leerISBN());
        libros.add(leerTitulo());
        libros.add(leerAutor());
        libros.add(leerCategoria());
        libros.add(leerIdioma());
        libros.add(leerPaginas());

        return libros;
    }

    //AYUDA
    String leerISBN() {
        String ISBN = tfISBN.getText();
        if (compruebaISBN(ISBN)) {
            return ISBN;
        } else {
            return null;
        }
    }

    boolean compruebaISBN(String ISBN) {
        return ISBN.length() <= 13; // Así??
    }

    String leerTitulo() {
        return tfTitulo.getText();
    }

    String leerAutor() {
        return tfAutor.getText();
    }

    // AYUDA
    String leerCategoria() {
        return cBoxCategoria.getValue().toString();
    }

    String leerIdioma() {
        return tfIdioma.getText();
    }

    String leerPaginas() {
        return tfPaginas.getText();
    }

    String leerEjemplares() {
        return tfEjemplares.getText();
    }

    void insertarLibro() throws SQLException {
        ArrayList<Object> libros = leeValoresLibro(); //DUDA tngo q poner libros o valores como esta en tu insertarUsuario ¿

        String sql = "INSERT INTO libros (ISBN, titulo, autor, categoria, idioma, paginas, ejemplares) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexion.conexion.prepareStatement(sql);

        /* todo
        stmt.setString(1, );
        stmt.setString(2, value2);
        stmt.setString(3, value3);
        stmt.setString(4, value4);
        stmt.setString(5, value5);
        stmt.setInt(6, value6);
        stmt.setInt(7, value7);
        */

        stmt.executeUpdate(); // Ejecutar la consulta

        // Cerrar las conexiones
        stmt.close();
        conexion.conexion.close();

    }

    /* MÉTODOS PESTAÑA USUARIO */

    @FXML
    void aceptarUsuarios(ActionEvent event) throws SQLException {
        if (editaUsuario){
            actualizarUsuario();
        } else {
            insertarUsuario();
        }
        volverUsuario();
    }

    @FXML
    void borrarUsuario() throws SQLException {
        eliminarUsuario("77864953V");
        // todo lectura de la tabla para recoger el valor del dni de la misma
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

    @FXML
    void volverUsuario() {
        verUsuarios();
        tfDNI.setText("");
        tfNombre.setText("");
        tfApellidos.setText("");
        tfDomicilio.setText("");
        tfTelefono.setText("");
        tfEmail.setText("");
    }

    String leerCampo(String nombreCampo, String texto, String criterioValidacion) {
        if (texto.matches(criterioValidacion)) {
            return texto;
        } else {
            return null;
        }
    }

    ArrayList<Object> leerValoresUsuario() {
        ArrayList<Object> valores = new ArrayList<Object>();
        valores.add(leerCampo("DNI", tfDNI.getText(), "[0-9]{8}[A-Za-z]")); // Criterio: Que tenga formato de DNI
        valores.add(leerCampo("Nombre", tfNombre.getText(), ".{1,20}"));
        valores.add(leerCampo("Apellidos", tfApellidos.getText(), ".{1,40}"));
        valores.add(leerCampo("Domicilio", tfDomicilio.getText(), ".{1,40}"));
        valores.add(leerCampo("Telefono", tfTelefono.getText(), "^[0-9]{9}$")); // Criterio: que sean 9 cifras
        valores.add(leerCampo("Email", tfEmail.getText(), "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")); // Criterio: Que tenga formato de email
        return valores;
    }

    private boolean compruebaDNI(String dni, StringBuilder mensajeError) {
        if (dni == null) {
            mensajeError.append("DNI (Introduzca un DNI válido)\n");
            return true;
        }
        return false;
    }

    private boolean compruebaNombre(String nombre, StringBuilder mensajeError) {
        if (nombre == null) {
            mensajeError.append("Nombre (No puede estar vacío. Máximo 20 caracteres)\n");
            return true;
        }
        return false;
    }

    private boolean compruebaApellidos(String apellidos, StringBuilder mensajeError) {
        if (apellidos == null) {
            mensajeError.append("Apellidos (No puede estar vacío. Máximo 40 caracteres)\n");
            return true;
        }
        return false;
    }

    private boolean compruebaDomicilio(String domicilio, StringBuilder mensajeError) {
        if (domicilio == null) {
            mensajeError.append("Domicilio (No puede estar vacío. Máximo 40 caracteres)\n");
            return true;
        }
        return false;
    }

    private boolean compruebaTelefono(String telefono, StringBuilder mensajeError) {
        if (telefono == null) {
            mensajeError.append("Teléfono (9 dígitos)\n");
            return true;
        }
        return false;
    }

    private boolean compruebaEmail(String email, StringBuilder mensajeError) {
        if (email == null) {
            mensajeError.append("Email (Debe tener un formato válido)\n");
            return true;
        }
        return false;
    }

    boolean mensajeErrorUsuario(ArrayList<Object> valores) {
        boolean hayError = false;
        StringBuilder mensajeError = new StringBuilder();

        hayError = compruebaDNI((String) valores.get(0), mensajeError) ? true : hayError;
        hayError = compruebaNombre((String) valores.get(1), mensajeError) ? true : hayError;
        hayError = compruebaApellidos((String) valores.get(2), mensajeError) ? true : hayError;
        hayError = compruebaDomicilio((String) valores.get(3), mensajeError) ? true : hayError;
        hayError = compruebaTelefono((String) valores.get(4), mensajeError) ? true : hayError;
        hayError = compruebaEmail((String) valores.get(5), mensajeError) ? true : hayError;

        if (hayError) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("CAMPOS ERRÓNEOS");
            error.setContentText("Hay error en los siguientes campos: " + mensajeError.toString());
            error.showAndWait();
        }
        return hayError;
    }

    // INSERT
    void insertarUsuario() throws SQLException {
        ArrayList<Object> valores = leerValoresUsuario();
        if (!mensajeErrorUsuario(valores)) {
            consultaInsertarUsuario(valores);
        }
    }

    void consultaInsertarUsuario(ArrayList<Object> valores) throws SQLException {
        String sql = "INSERT INTO usuarios (DNI, nombre, apellidos, domicilio, telefono, email) " +
                "VALUES (?, ?, ?, ?, ?, ?)"; // Consulta para insertar usuarios en la base de datos
        try (PreparedStatement stmt = conexion.conexion.prepareStatement(sql)) {
            int i = 1;
            for (Object valor : valores) {
                stmt.setString(i++, (String) valor);
            }
            stmt.executeUpdate(); // Ejecutar la consulta
        }
    }

    // UPDATE
    void actualizarUsuario() throws SQLException {
        ArrayList<Object> valores = leerValoresUsuario();
        if (!mensajeErrorUsuario(valores)) {
            consultaActualizarUsuario(valores);
        }
    }

    void consultaActualizarUsuario(ArrayList<Object> valores) throws SQLException {
        String sql = "UPDATE usuarios SET nombre=?, apellidos=?, domicilio=?, telefono=?, email=? WHERE DNI=?";
        try (PreparedStatement stmt = conexion.conexion.prepareStatement(sql)) {
            int i = 1;
            for (int j=1; j<valores.size(); j++) {
                stmt.setString(i++, (String) valores.get(j));
            }
            stmt.setString(i, (String) valores.get(0));
            stmt.executeUpdate();
        }
    }

    // DELETE
    void eliminarUsuario(String DNI) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE DNI = ?";
        try (PreparedStatement stmt = conexion.conexion.prepareStatement(sql)) {
            stmt.setString(1, DNI);
            stmt.executeUpdate();
        }
    }



    /* BOTONES DE NAVEGACIÓN */
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
        panelRegistroUsuarios.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}