package safa.ad_biblioteca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import safa.ad_biblioteca.model.Conexion;
import safa.ad_biblioteca.model.Usuario;
import safa.ad_biblioteca.model.Libro;


import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {

    // Elementos JavaFX
    @FXML
    private HBox HBoxDatosLibroBuscado;

    @FXML
    private ListView<String> ListViewDatosLibro;

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
    private ImageView imageViewLib1;

    @FXML
    private ImageView imageViewLib2;

    @FXML
    private ImageView imageViewLib3;

    @FXML
    private ImageView imageViewLib4;

    @FXML
    private ImageView imageViewLib5;

    @FXML
    private ImageView imgViewLibroBuscado;

    @FXML
    private Pane panelDevoluciones;

    @FXML
    private Pane panelLibros;

    @FXML
    private Pane panelLibrosPreview;

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
    private TextField tfAutor;

    @FXML
    private TextField tfBuscarISBN;

    @FXML
    private TextField tfBuscarUsuarios;

    @FXML
    private TextField tfCategoria;

    @FXML
    private TextField tfClave;

    @FXML
    private TextField tfDNI;

    @FXML
    private TextField tfDomicilio;

    @FXML
    private TextField tfEjemplares;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfISBN;

    @FXML
    private TextField tfIdioma;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPaginas;

    @FXML
    private TextField tfTelefono;

    @FXML
    private TextField tfTitulo;

    @FXML
    private TableView<Usuario> tvUsuarios;

    @FXML
    private TableColumn<Usuario, String> colDNI;

    @FXML
    private TableColumn<Usuario, String> colNombre;

    @FXML
    private TableColumn<Usuario, String> colApellidos;

    @FXML
    private TableColumn<Usuario, String> colDomicilio;

    @FXML
    private TableColumn<Usuario, String> colTelefono;
    
    @FXML
private TableColumn<Usuario, String> colEmail;

    @FXML
    private TextField tfBuscarLibro;
    


    @FXML
    void searchBook(ActionEvent event) {
       btnLibrosBuscar.setOnAction(e -> searchBook());
    }
    // Atributos
    Conexion conexion = new Conexion();
    Boolean editaUsuario;
    Boolean editaLibro;


    // Métodos

    /* MÉTODOS PESTAÑA LIBROS */

    // Boton aceptar
    @FXML
    void aceptarLibros(ActionEvent event) throws SQLException {
        if (editaLibro) {
            actualizarLibro();
        } else {
            insertarLibro();

        } volverLibros();
    }
    // Boton borrar
    @FXML
    void borrarLibro(ActionEvent event) throws SQLException {
        eliminarLibro("");
        // todo lectura de la tabla para recoger el valor del ISBN de la misma
    }
    // Boton modificar
    @FXML
    void modificarLibro(ActionEvent event) {
        cambiarVistaFormUsuarioL();
        editaLibro = true;
    }
    // Boton nuevo libro -> cambia de vista al formulario de registro
    @FXML
    void nuevoLibro(ActionEvent event) {
        cambiarVistaFormUsuarioL();
        editaLibro = false;
    }
    // Para mostrar el formulario de registro libros
    void cambiarVistaFormUsuarioL() {
        panelLibros.setVisible(false);
        panelRegistroLibros.setVisible(true);
    }
    // Para mostrar el panel principal de libros
    void cambiarVistaVolverLibro() {
        panelRegistroLibros.setVisible(false);
        panelLibros.setVisible(true);
        panelLibrosPreview.setVisible(true);
        HBoxDatosLibroBuscado.setVisible(false);
    }
    // oculta la preview de libros general y muestra el libro concreto
    void cambiarVistaBusqueda() {
        panelLibros.setVisible(true);
        panelLibrosPreview.setVisible(false); // Desactivar el panel de libros de bienvenida
        HBoxDatosLibroBuscado.setVisible(true); // Activar el de la vista de libro buscado
    }
    @FXML
    void volverLibros() {
        cambiarVistaVolverLibro();
        // Vaciar campos formulario libros
        tfISBN.setText("");
        tfTitulo.setText("");
        tfAutor.setText("");
        tfCategoria.setText("");
        tfIdioma.setText("");
        tfPaginas.setText("");
        tfEjemplares.setText("");
        panelPrincipal.setVisible(true);
    }


    @FXML
    private void searchBook() {
        ListViewDatosLibro.getItems().clear();
        String tituloBuscado = tfBuscarLibro.getText();

        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca", "root", "root");
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM libro WHERE titulo LIKE '%" + tituloBuscado + "%' OR autor LIKE '%" + tituloBuscado + "%' OR categoria LIKE '%" + tituloBuscado + "%'");

            ListViewDatosLibro.getItems().clear();
            while (rs.next()) {
                String titulo = rs.getString("titulo");
                Libro libro = new Libro(titulo);
                ListViewDatosLibro.getItems().add(titulo);

            }

            ListViewDatosLibro.refresh();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    String leerCampoLibro(String nombreCampoL, String texto, String criterioValidacion) {
        if (texto.matches(criterioValidacion)) {
            return texto;
        } else {
            return null;
        }
    }

    Libro leeValoresLibro() {
        String ISBN = leerCampoLibro("ISBN", tfISBN.getText(), ".{10,13}");
        String titulo = leerCampoLibro("Titulo", tfTitulo.getText(), ".{1,50}");
        String autor = leerCampoLibro("Autor", tfAutor.getText(), ".{1,50}");
        String categoria = leerCampoLibro("Categoria", tfCategoria.getText(), ".{1,50}");
        String idioma = leerCampoLibro("Idioma", tfIdioma.getText(), ".{1,20}");
        String paginas = leerCampoLibro("Paginas", tfPaginas.getText(), ".{1,3}");
        String ejemplares = leerCampoLibro("Ejemplares", tfEjemplares.getText(), ".{1,3}");
        return new Libro(ISBN, titulo, autor, categoria, idioma, paginas, ejemplares);

    }

    private boolean compruebaISBN(String ISBN, StringBuilder mensajeError) {
        if (ISBN == null) {
            mensajeError.append("ISBN (Introduzca un ISBN válido)\n");
            return true;
        }
        return false;
    }

    private boolean compruebaTitulo(String Titulo, StringBuilder mensajeError) {
        if (Titulo == null) {
            mensajeError.append("Titulo (No puede estar vacío. Máximo 50 caracteres)\n");
            return true;
        }
        return false;
    }

    private boolean compruebaAutor(String Autor, StringBuilder mensajeError) {
        if (Autor == null) {
            mensajeError.append("Autor (No puede estar vacío. Máximo 50 caracteres)\n");
            return true;
        }
        return false;
    }

    private boolean compruebaCategoria(String Categoria, StringBuilder mensajeError) {
        if (Categoria == null) {    // if (!cBoxCategoria.getSelectedItem().toString().matches("^\\s*$")) {

            mensajeError.append("Categoría (Escriba una categoría)\n");
            return true;
        }
        return false;
    }

    private boolean compruebaIdioma(String Idioma, StringBuilder mensajeError) {
        if (Idioma == null) {
            mensajeError.append("Idioma (No puede estar vacío. Máximo 20 caracteres)\n");
            return true;
        }
        return false;
    }

    private boolean compruebaPaginas(String Paginas, StringBuilder mensajeError) {
        if (Paginas == null) {
            mensajeError.append("Páginas (No puede estar vacío.)\n");
            return true;
        }
        return false;
    }

    private boolean compruebaEjemplares(String Ejemplares, StringBuilder mensajeError) {
        if (Ejemplares == null) {
            mensajeError.append("Ejemplares (No puede estar vacío.)\n");
            return true;
        }
        return false;
    }

    boolean mensajeErrorLibro(Libro libro) {
        boolean hayError = false;
        StringBuilder mensajeError = new StringBuilder();

        hayError = compruebaISBN(libro.getISBN(), mensajeError) ? true : hayError;
        hayError = compruebaTitulo(libro.getTitulo(), mensajeError) ? true : hayError;
        hayError = compruebaAutor(libro.getAutor(), mensajeError) ? true : hayError;
        hayError = compruebaCategoria(libro.getCategoria(), mensajeError) ? true : hayError;
        hayError = compruebaIdioma(libro.getIdioma(), mensajeError) ? true : hayError;
        hayError = compruebaPaginas(libro.getPaginas(), mensajeError) ? true : hayError;
        hayError = compruebaEjemplares(libro.getEjemplares(), mensajeError) ? true : hayError;


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
    private void insertarLibro() throws SQLException {
        Libro libro = leeValoresLibro();
        if (!mensajeErrorLibro(libro)) {
            consultaInsertarLibro(libro);
        }
    }

    private void consultaInsertarLibro(Libro libro) throws SQLException {
        String sql = "INSERT INTO libro (ISBN, titulo, autor, categoria, idioma, paginas, ejemplares) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"; // Consulta para insertar libro en la base de datos
        try (PreparedStatement stmt = conexion.conexion.prepareStatement(sql)) {
            int i = 1;
            stmt.setString(1, libro.getISBN());
            stmt.setString(2, libro.getTitulo());
            stmt.setString(3, libro.getAutor());
            stmt.setString(4, libro.getCategoria());
            stmt.setString(5, libro.getIdioma());
            stmt.setString(6, libro.getPaginas());
            stmt.setString(7, libro.getEjemplares());


            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("INSERTAR LIBROS", "Libro insertado con éxito");
        }
    }


    // UPDATE
void actualizarLibro() throws SQLException {
        Libro libro = leeValoresLibro();
        if (!mensajeErrorLibro(libro)) {
            consultaActualizarLibro(libro);
        }
    }
    
    void consultaActualizarLibro(Libro libro) throws SQLException {
        String sql = "UPDATE libro SET titulo=?, autor=?, categoria=?, idioma=?, paginas=?, ejemplares=? WHERE ISBN=?";
        try (PreparedStatement stmt = conexion.conexion.prepareStatement(sql)) {
            int i = 1;
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getCategoria());
            stmt.setString(4, libro.getIdioma());
            stmt.setString(5, libro.getPaginas());
            stmt.setString(6, libro.getEjemplares());
            stmt.setString(7, libro.getISBN());


            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("ACTUALIZAR LIBRO", "Libro actualizado con éxito");
        }
    }


    // DELETE
    void eliminarLibro(String ISBN) throws SQLException {
        String sql = "DELETE FROM libro WHERE ISBN = ?";
        try (PreparedStatement stmt = conexion.conexion.prepareStatement(sql)) {
            stmt.setString(1, ISBN);
            stmt.executeUpdate();
        }
    }

    /* MÉTODOS PESTAÑA USUARIO */

    @FXML
    void aceptarUsuarios(ActionEvent event) throws SQLException {
        if (editaUsuario) {
            actualizarUsuario();
        } else {
            insertarUsuario();
        }

    }

    @FXML
    void borrarUsuario() throws SQLException {
        eliminarUsuario(tvUsuarios.getSelectionModel().getSelectedItem().getDNI());
        cargarTablaUsuarios();
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
        tfDNI.setId("tfNormal");
        tfNombre.setText("");
        tfNombre.setId("tfNormal");
        tfApellidos.setText("");
        tfApellidos.setId("tfNormal");
        tfDomicilio.setText("");
        tfDomicilio.setId("tfNormal");
        tfTelefono.setText("");
        tfTelefono.setId("tfNormal");
        tfEmail.setText("");
        tfEmail.setId("tfNormal");
        tfClave.setText("");
        tfClave.setId("tfNormal");
    }

    @FXML
    void cargarTablaUsuarios() throws SQLException {
        ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();

        Statement stmt = conexion.conexion.createStatement();

        String query = "SELECT * FROM usuarios";
        String filtro = tfBuscarUsuarios.getText();
        if(!filtro.equals("")){
            query += " WHERE " +
                    "DNI LIKE '%" + filtro + "%' OR " +
                    "nombre LIKE '%" + filtro + "%' OR " +
                    "apellidos LIKE '%" + filtro + "%' OR " +
                    "domicilio LIKE '%" + filtro + "%' OR " +
                    "telefono LIKE '%" + filtro + "%' OR " +
                    "email LIKE '%" + filtro + "%' OR " +
                    "clave LIKE '%" + filtro + "%';";
        }

        ResultSet datos = stmt.executeQuery(query);
        while(datos.next()){
            listaUsuarios.add(new Usuario(datos.getString("DNI"), datos.getString("nombre"),
                    datos.getString("apellidos"), datos.getString("domicilio"),
                    datos.getString("telefono"), datos.getString("email"),
                    datos.getString("clave")));
        }

        tvUsuarios.setItems(listaUsuarios);

        colDNI.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    String leerCampo(String nombreCampo, String texto, String criterioValidacion) {
        if (texto.matches(criterioValidacion)) {
            return texto;
        } else {
            return null;
        }
    }

    Usuario leerValoresUsuario() {
        String DNI = leerCampo("DNI", tfDNI.getText(), "[0-9]{8}[A-Za-z]"); // Criterio: Que tenga formato de DNI
        String nombre = leerCampo("Nombre", tfNombre.getText(), ".{1,20}");
        String apellidos = leerCampo("Apellidos", tfApellidos.getText(), ".{1,40}");
        String domicilio = leerCampo("Domicilio", tfDomicilio.getText(), ".{1,40}");
        String telefono = leerCampo("Telefono", tfTelefono.getText(), "^[0-9]{9}$"); // Criterio: que sean 9 cifras
        String email = leerCampo("Email", tfEmail.getText(), "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$"); // Criterio: Que tenga formato de email
        String clave = leerCampo("Clave", tfClave.getText(), "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$"); // Criterio: 1 mayus, 1 minus, 8 caracteres
        return new Usuario(DNI, nombre, apellidos, domicilio, telefono, email, clave);
    }

    private boolean compruebaDNI(String dni, StringBuilder mensajeError) {
        if (dni == null) {
            mensajeError.append("DNI (Introduzca un DNI válido)\n");
            tfDNI.setId("tfError");
            return true;
        } else {
            tfDNI.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaNombre(String nombre, StringBuilder mensajeError) {
        if (nombre == null) {
            mensajeError.append("Nombre (No puede estar vacío. Máximo 20 caracteres)\n");
            tfNombre.setId("tfError");
            return true;
        } else {
            tfNombre.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaApellidos(String apellidos, StringBuilder mensajeError) {
        if (apellidos == null) {
            mensajeError.append("Apellidos (No puede estar vacío. Máximo 40 caracteres)\n");
            tfApellidos.setId("tfError");
            return true;
        } else {
            tfApellidos.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaDomicilio(String domicilio, StringBuilder mensajeError) {
        if (domicilio == null) {
            mensajeError.append("Domicilio (No puede estar vacío. Máximo 40 caracteres)\n");
            tfDomicilio.setId("tfError");
            return true;
        } else {
            tfDomicilio.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaTelefono(String telefono, StringBuilder mensajeError) {
        if (telefono == null) {
            mensajeError.append("Teléfono (9 dígitos)\n");
            tfTelefono.setId("tfError");
            return true;
        } else {
            tfTelefono.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaEmail(String email, StringBuilder mensajeError) {
        if (email == null) {
            mensajeError.append("Email (Debe tener un formato válido)\n");
            tfEmail.setId("tfError");
            return true;
        } else {
            tfEmail.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaClave(String email, StringBuilder mensajeError) {
        if (email == null) {
            mensajeError.append("Clave (Debe tener al menos 8 caracteres, 1 mayúscula y 1 minúscula)\n");
            tfClave.setId("tfError");
            return true;
        } else {
            tfClave.setId("tfNormal");
        }
        return false;
    }

    boolean mensajeErrorUsuario(Usuario usuario) {
        boolean hayError = false;
        StringBuilder mensajeError = new StringBuilder();

        hayError = compruebaDNI(usuario.getDNI(), mensajeError) ? true : hayError;
        hayError = compruebaNombre(usuario.getNombre(), mensajeError) ? true : hayError;
        hayError = compruebaApellidos(usuario.getApellidos(), mensajeError) ? true : hayError;
        hayError = compruebaDomicilio(usuario.getDomicilio(), mensajeError) ? true : hayError;
        hayError = compruebaTelefono(usuario.getTelefono(), mensajeError) ? true : hayError;
        hayError = compruebaEmail(usuario.getEmail(), mensajeError) ? true : hayError;
        hayError = compruebaClave(usuario.getClave(), mensajeError) ? true : hayError;

        if (hayError) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("CAMPOS ERRÓNEOS");
            error.setContentText("Hay error en los siguientes campos:\n" + mensajeError.toString());
            error.showAndWait();
        }
        return hayError;
    }

    // INSERT
    void insertarUsuario() throws SQLException {
        Usuario usuario = leerValoresUsuario();
        if (!mensajeErrorUsuario(usuario)) {
            consultaInsertarUsuario(usuario);
            cargarTablaUsuarios();
            volverUsuario();
        }
    }

    void consultaInsertarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (DNI, nombre, apellidos, domicilio, telefono, email, clave) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"; // Consulta para insertar usuarios en la base de datos
        try (PreparedStatement stmt = conexion.conexion.prepareStatement(sql)) {
            int i = 1;

            stmt.setString(1, usuario.getDNI());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellidos());
            stmt.setString(4, usuario.getDomicilio());
            stmt.setString(5, usuario.getTelefono());
            stmt.setString(6, usuario.getEmail());
            stmt.setString(7, usuario.getClave());


            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("INSERTAR USUARIO", "Usuario insertado con éxito");
        }
    }

    // UPDATE
    void actualizarUsuario() throws SQLException {
        Usuario usuario = leerValoresUsuario();
        if (!mensajeErrorUsuario(usuario)) {
            consultaActualizarUsuario(usuario);
            cargarTablaUsuarios();
            volverUsuario();
        }
    }

    void consultaActualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nombre=?, apellidos=?, domicilio=?, telefono=?, email=?, clave=? WHERE DNI=?";
        try (PreparedStatement stmt = conexion.conexion.prepareStatement(sql)) {
            int i = 1;

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellidos());
            stmt.setString(3, usuario.getDomicilio());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getClave());
            stmt.setString(7, usuario.getDNI());

            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("ACTUALIZAR USUARIO", "Usuario actualizado con éxito");
        }
    }

    // DELETE
    void eliminarUsuario(String DNI) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE DNI = ?";
        try (PreparedStatement stmt = conexion.conexion.prepareStatement(sql)) {
            stmt.setString(1, DNI);

            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("ELIMINAR USUARIO", "Usuario eliminado con éxito");
        }
    }

    /* MÉTODOS COMUNES */
    private static void ventanaDialogo(String titulo, String mensaje) {
        // Ventana de error
        Dialog<String> ventana = new Dialog<>();
        ventana.setTitle(titulo);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ventana.setContentText(mensaje);
        ventana.getDialogPane().getButtonTypes().add(type);
        ventana.showAndWait();
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
        try {
            cargarTablaUsuarios();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}