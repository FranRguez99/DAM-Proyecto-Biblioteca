package safa.ad_biblioteca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import safa.ad_biblioteca.model.Conexion;
import safa.ad_biblioteca.model.Prestamo;
import safa.ad_biblioteca.model.Usuario;
import safa.ad_biblioteca.model.Libro;


import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

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
    private Button btnLoginInicio;

    @FXML
    private Button btnLoginNuevo;

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
    private ComboBox<String> cbCategoria;

    @FXML
    private TableColumn<Usuario, String> colApellidos;

    @FXML
    private TableColumn<Libro, String> colAutor;

    @FXML
    private TableColumn<Libro, String> colCategoria;

    @FXML
    private TableColumn<Usuario, String> colDNI;

    @FXML
    private TableColumn<Libro, Integer> colDisponibles;

    @FXML
    private TableColumn<Usuario, String> colDomicilio;

    @FXML
    private TableColumn<Usuario, String> colEmail;

    @FXML
    private TableColumn<Libro, String> colISBN;

    @FXML
    private TableColumn<Libro, String> colIdioma;

    @FXML
    private TableColumn<Usuario, String> colNombre;

    @FXML
    private TableColumn<Usuario, String> colTelefono;

    @FXML
    private TableColumn<Libro, String> colTitulo;

    @FXML
    private TableColumn<Prestamo, String> colPrestamoDNI;

    @FXML
    private TableColumn<Prestamo, String> colPrestamoISBN;

    @FXML
    private ImageView foto1;

    @FXML
    private ImageView foto2;

    @FXML
    private ImageView foto3;

    @FXML
    private ImageView foto4;

    @FXML
    private ImageView foto5;

    @FXML
    private ImageView foto6;

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
    private Pane panelLogin;

    @FXML
    private Pane panelUsuarios;

    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfAutor;

    @FXML
    private TextField tfBuscarLibro;

    @FXML
    private TextField tfBuscarUsuarios;

    @FXML
    private TextField tfClave;

    @FXML
    private TextField tfDNI;

    @FXML
    private TextField tfDevolucionDNI;

    @FXML
    private TextField tfDevolucionISBN;

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
    private TextField tfLoginClave;

    @FXML
    private TextField tfLoginDNI;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPaginas;

    @FXML
    private TextField tfPrestamoDNI;

    @FXML
    private TextField tfPrestamoISBN;

    @FXML
    private TextField tfTelefono;

    @FXML
    private TextField tfTitulo;

    @FXML
    private TableView<Libro> tvLibros;

    @FXML
    private TableView<Usuario> tvUsuarios;

    @FXML
    private TableView<Prestamo> tvPrestamos;

    // Atributos
    Conexion conexion = new Conexion();
    Boolean editaUsuario;
    Boolean editaLibro;
    Boolean sesionAdmin = true;
    Boolean sesionIniciada = false;

    // Métodos

    /* MÉTODOS PESTAÑA LOGIN */

    @FXML
    void login() throws SQLException {
        String usuario = tfLoginDNI.getText();
        String clave = tfLoginClave.getText();

        if (usuario.equals("admin") && clave.equals("admin")) {
            inicio();
            sesionIniciada = true;
        } else {
            Statement stmt = conexion.createStatement();
            String sql = "SELECT * FROM usuarios WHERE DNI='" + usuario + "' AND clave='" + clave + "'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                sesionAdmin = false;
                inicio();
            } else {
                ventanaDialogo("ERROR", "Usuario o contraseña incorrectos");
            }
        }


    }

    void inicio() {
        panelLogin.setVisible(false);
        panelPrincipal.setVisible(true);
        if (!sesionAdmin) {
            // Ocultar botones
            btnPrincipalPrestamos.setVisible(false);
            btnLibrosDevoluciones.setVisible(false);
            btnLibrosPrestamos.setVisible(false);
            btnPrincipalDevoluciones.setVisible(false);
            btnPrincipalUsuarios.setVisible(false);
            btnLibrosUsuarios.setVisible(false);
            btnLibrosNuevo.setVisible(false);
            btnLibrosModificar.setVisible(false);
            btnLibrosBorrar.setVisible(false);

            // Ocultar fotos
            foto1.setVisible(false);
            foto2.setVisible(false);
            foto3.setVisible(false);
            foto4.setVisible(false);
            foto5.setVisible(false);
            foto6.setVisible(false);
        }
    }

    /* MÉTODOS PESTAÑA LIBROS */

    // Boton aceptar
    @FXML
    void aceptarLibros(ActionEvent event) throws SQLException {
        if (editaLibro) {
            actualizarLibro();
        } else {
            insertarLibro();
        }
        ;
    }

    // Boton borrar
    @FXML
    void borrarLibro(ActionEvent event) throws SQLException {
        try {
            String ISBN = tvLibros.getSelectionModel().getSelectedItem().getISBN();
            Alert confirmar = ventanaConfirmacion("ELIMINAR LIBRO", "Eliminar libro", "¿Está seguro de que desea eliminar este libro?");
            Optional<ButtonType> resultado = confirmar.showAndWait();
            if (resultado.get() == confirmar.getButtonTypes().get(0)) {
                eliminarLibro(ISBN);
                cargarTablaLibros();
            }
        } catch (NullPointerException e) {
            ventanaDialogo("ERROR", "No hay ningún libro seleccionado");
        }
    }

    // Boton modificar
    @FXML
    void modificarLibro(ActionEvent event) throws SQLException {
        try {
            cargarFormLibros(tvLibros.getSelectionModel().getSelectedItem().getISBN());
            cambiarVistaFormLibro();
            editaLibro = true;
        } catch (NullPointerException npe) {
            ventanaDialogo("ERROR", "No hay ningún libro seleccionado");
        }

    }

    private void cargarFormLibros(String ISBN) throws SQLException {
        Libro libro = null;
        Statement stmt = conexion.conexion.createStatement();

        String query = "SELECT * FROM libros WHERE ISBN = '" + ISBN + "'";
        ResultSet datos = stmt.executeQuery(query);
        while (datos.next()) {
            libro = new Libro(datos.getString("ISBN"), datos.getString("titulo"), datos.getString("autor"), datos.getString("categoria"), datos.getString("idioma"), datos.getString("paginas"), datos.getString("ejemplares"), datos.getInt("disponible"));
        }

        tfISBN.setEditable(false);
        tfISBN.setText(libro.getISBN());
        tfTitulo.setText(libro.getTitulo());
        tfAutor.setText(libro.getAutor());
        cbCategoria.setValue(libro.getCategoria());
        tfIdioma.setText(libro.getIdioma());
        tfPaginas.setText(libro.getPaginas());
        tfEjemplares.setText(libro.getEjemplares());
    }

    @FXML
    void nuevoLibro(ActionEvent event) {
        cambiarVistaFormLibro();
        editaLibro = false;
        tfISBN.setEditable(true);
    }

    // Para mostrar el formulario de registro libros
    void cambiarVistaFormLibro() {
        panelLibros.setVisible(false);
        panelRegistroLibros.setVisible(true);
    }

    // Para mostrar el panel principal de libros
    void cambiarVistaVolverLibro() {
        panelRegistroLibros.setVisible(false);
        panelLibros.setVisible(true);
    }

    // oculta la preview de libros general y muestra el libro concreto
    void cambiarVistaBusqueda() {
        panelLibros.setVisible(true);
    }

    @FXML
    void volverLibros() {
        cambiarVistaVolverLibro();
        // Vaciar campos formulario libros
        tfISBN.setText("");
        tfTitulo.setText("");
        tfAutor.setText("");
        cbCategoria.setValue("");
        tfIdioma.setText("");
        tfPaginas.setText("");
        tfEjemplares.setText("");
        panelPrincipal.setVisible(true);
    }

    @FXML
    void cargarTablaLibros() throws SQLException {
        ObservableList<Libro> listaLibros = FXCollections.observableArrayList();

        Statement stmt = conexion.conexion.createStatement();

        String query = "SELECT * FROM libros";
        String filtro = tfBuscarLibro.getText();
        if (!filtro.equals("")) {
            query += " WHERE " + "ISBN LIKE '%" + filtro + "%' OR " + "titulo LIKE '%" + filtro + "%' OR " + "categoria LIKE '%" + filtro + "%' OR " + "idioma LIKE '%" + filtro + "%';";
        }

        ResultSet datos = stmt.executeQuery(query);
        while (datos.next()) {
            listaLibros.add(new Libro(datos.getString("ISBN"), datos.getString("titulo"), datos.getString("autor"), datos.getString("categoria"), datos.getString("idioma"), datos.getString("paginas"), datos.getString("ejemplares"), datos.getInt("disponible")));

        }

        tvLibros.setItems(listaLibros);

        colISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        colDisponibles.setCellValueFactory(new PropertyValueFactory<>("disponible"));
    }


    Libro leeValoresLibro() {
        String ISBN = leerCampo("ISBN", tfISBN.getText(), ".{10,13}");
        String titulo = leerCampo("Titulo", tfTitulo.getText(), ".{1,50}");
        String autor = leerCampo("Autor", tfAutor.getText(), ".{1,50}");
        String categoria = leerCampo("Categoria", (String) cbCategoria.getValue(), ".{1,50}");
        String idioma = leerCampo("Idioma", tfIdioma.getText(), ".{1,20}");
        String paginas = leerCampo("Paginas", tfPaginas.getText(), ".{1,3}");
        String ejemplares = leerCampo("Ejemplares", tfEjemplares.getText(), ".{1,3}");
        return new Libro(ISBN, titulo, autor, categoria, idioma, paginas, ejemplares);

    }

    private boolean compruebaISBN(String ISBN, StringBuilder mensajeError) {
        if (ISBN == null) {
            mensajeError.append("ISBN (Introduzca un ISBN válido)\n");
            tfISBN.setId("tfError");
            tfPrestamoISBN.setId("tfError");
            return true;
        } else {
            tfISBN.setId("tfNormal");
            tfPrestamoISBN.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaTitulo(String Titulo, StringBuilder mensajeError) {
        if (Titulo == null) {
            mensajeError.append("Titulo (No puede estar vacío. Máximo 50 caracteres)\n");
            tfTitulo.setId("tfError");
            return true;
        } else {
            tfTitulo.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaAutor(String Autor, StringBuilder mensajeError) {
        if (Autor == null) {
            mensajeError.append("Autor (No puede estar vacío. Máximo 50 caracteres)\n");
            tfAutor.setId("tfError");
            return true;
        } else {
            tfAutor.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaCategoria(String Categoria, StringBuilder mensajeError) {
        if (Categoria == null) {    // if (!cBoxCategoria.getSelectedItem().toString().matches("^\\s*$")) {
            mensajeError.append("Categoría (Escriba una categoría)\n");
            cbCategoria.setId("tfError");
            return true;
        } else {
            cbCategoria.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaIdioma(String Idioma, StringBuilder mensajeError) {
        if (Idioma == null) {
            mensajeError.append("Idioma (No puede estar vacío. Máximo 20 caracteres)\n");
            tfIdioma.setId("tfError");
            return true;
        } else {
            tfIdioma.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaPaginas(String Paginas, StringBuilder mensajeError) {
        if (Paginas == null) {
            mensajeError.append("Páginas (No puede estar vacío.)\n");
            tfPaginas.setId("tfError");
            return true;
        } else {
            tfPaginas.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaEjemplares(String Ejemplares, StringBuilder mensajeError) {
        if (Ejemplares == null) {
            mensajeError.append("Ejemplares (No puede estar vacío.)\n");
            tfEjemplares.setId("tfError");
            return true;
        } else {
            tfEjemplares.setId("tfNormal");
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
            error.setContentText("Hay error en los siguientes campos:\n" + mensajeError.toString());
            error.showAndWait();
        }
        return hayError;

    }

    // INSERT
    private void insertarLibro() throws SQLException {
        Libro libro = leeValoresLibro();
        if (!mensajeErrorLibro(libro)) {
            consultaInsertarLibro(libro);
            cargarTablaLibros();
            volverLibros();
        }
    }

    private void consultaInsertarLibro(Libro libro) throws SQLException {
        String sql = "INSERT INTO libros (ISBN, titulo, autor, categoria, idioma, paginas, ejemplares, disponible) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // Consulta para insertar libro en la base de datos
        try (PreparedStatement stmt = conexion.conexion.prepareStatement(sql)) {
            int i = 1;
            stmt.setString(1, libro.getISBN());
            stmt.setString(2, libro.getTitulo());
            stmt.setString(3, libro.getAutor());
            stmt.setString(4, libro.getCategoria());
            stmt.setString(5, libro.getIdioma());
            stmt.setString(6, libro.getPaginas());
            stmt.setString(7, libro.getEjemplares());
            stmt.setString(8, libro.getDisponible().toString());


            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("INSERTAR LIBROS", "Libro insertado con éxito");
        }
    }


    // UPDATE
    void actualizarLibro() throws SQLException {
        Libro libro = leeValoresLibro();
        if (!mensajeErrorLibro(libro)) {
            consultaActualizarLibro(libro);
            cargarTablaLibros();
            volverLibros();
        }
    }

    void consultaActualizarLibro(Libro libro) throws SQLException {
        String sql = "UPDATE libros SET titulo=?, autor=?, categoria=?, idioma=?, paginas=?, ejemplares=? WHERE ISBN=?";
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
        String sql = "DELETE FROM libros WHERE ISBN = ?";
        try (PreparedStatement stmt = conexion.conexion.prepareStatement(sql)) {
            stmt.setString(1, ISBN);
            stmt.executeUpdate();
        }
    }

    /* MÉTODOS PESTAÑA PRÉSTAMO */
    @FXML
    void insertaPrestamo() throws SQLException {
        try {
            if (!mensajeErrorPrestamo()) {
                compruebaUsuario();
                consultaInsertaPrestamo();
                tfPrestamoDNI.setText("");
                tfPrestamoISBN.setText("");
                cargarTablaDevoluciones();
            }
        } catch (SQLIntegrityConstraintViolationException exception) {
            ventanaDialogo("ERROR", "El usuario y/o el libro no se encuentra registrado");
        } catch (SancionException se) {
            ventanaDialogo("ERROR", "Este usuario se encuentra sancionado");
        }

    }

    void compruebaUsuario() throws SQLException, SancionException {
        Usuario usuario = null;
        Statement stmt = conexion.conexion.createStatement();
        String DNI = tfPrestamoDNI.getText();
        String query = "SELECT * FROM usuarios WHERE DNI = '" + DNI + "'";
        ResultSet datos = stmt.executeQuery(query);
        while (datos.next()) {
            usuario = new Usuario(datos.getString("DNI"), datos.getString("nombre"), datos.getString("apellidos"), datos.getString("domicilio"), datos.getString("telefono"), datos.getString("email"), datos.getInt("sancionado"), datos.getString("clave"), datos.getDate("fecha_Sancion"));
        }
        try{
            long diferencia = new Date().getTime() - usuario.getFecha_Sancion().getTime();
            if (diferencia >= 30 * 24 * 60 * 60 * 1000) {
                String sql = "UPDATE usuarios SET sancionado = 0 WHERE DNI = ?";
                try (PreparedStatement stmtUpdate = conexion.conexion.prepareStatement(sql)) {

                    stmtUpdate.setString(1, DNI);
                    stmtUpdate.executeUpdate();// Ejecutar la consulta
                }
            } else {
                ventanaDialogo("USUARIO SANCIONADO", "El usuario ha sido sancionado por un retraso " + "en la entrega");
                throw new SancionException("Usuario sancionado");
            }
        } catch (Exception ignored){

        }

    }

    void consultaInsertaPrestamo() throws SQLException {
        String sql = "INSERT INTO prestamos (DNI_usuario, ISBN, fecha_salida) VALUES (?,?,?)";
        try (PreparedStatement stmt = conexion.conexion.prepareStatement(sql)) {

            stmt.setString(1, tfPrestamoDNI.getText());
            stmt.setString(2, tfPrestamoISBN.getText());
            stmt.setString(3, String.valueOf(LocalDate.now()));
            System.out.println(stmt);
            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("NUEVO PRÉSTAMO", "Préstamo realizado con éxito");
        }
    }

    boolean mensajeErrorPrestamo() {
        boolean hayError = false;
        StringBuilder mensajeError = new StringBuilder();

        String DNI = leerCampo("DNI", tfPrestamoDNI.getText(), "[0-9]{8}[A-Za-z]");
        String ISBN = leerCampo("ISBN", tfPrestamoISBN.getText(), ".{10,13}");

        hayError = compruebaDNI(DNI, mensajeError) ? true : hayError;
        hayError = compruebaISBN(ISBN, mensajeError) ? true : hayError;


        if (hayError) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("CAMPOS ERRÓNEOS");
            error.setContentText("Hay error en los siguientes campos:\n" + mensajeError.toString());
            error.showAndWait();
        }
        return hayError;
    }

    /* MÉTODOS PESTAÑA DEVOLUCIONES */

    @FXML
    void devolverLibro() throws SQLException, ParseException {
        if (!mensajeErrorDevolucion()) {
            consultaFechaDevolucion();
            comprobarSancion();
            tfDevolucionDNI.setText("");
            tfPrestamoISBN.setText("");
            cargarTablaDevoluciones();

        }
    }

    private void comprobarSancion() throws SQLException, ParseException {
        String DNI = leerCampo("DNI", tfDevolucionDNI.getText(), "[0-9]{8}[A-Za-z]");
        String ISBN = leerCampo("ISBN", tfDevolucionISBN.getText(), ".{10,13}");

        Prestamo prestamo = null;
        Usuario usuario = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Statement stmt = conexion.conexion.createStatement();
        String query = "SELECT * FROM prestamos WHERE DNI_usuario = '" + DNI + "' AND ISBN = '" + ISBN + "'";
        ResultSet datos = stmt.executeQuery(query);
        while (datos.next()) {
            prestamo = new Prestamo(datos.getString("DNI_usuario"), datos.getString("ISBN"), format.parse(datos.getString("fecha_salida")), format.parse(datos.getString("fecha_devolucion")));
        }

        long diferencia = prestamo.getFecha_devolucion().getTime() - prestamo.getFecha_salida().getTime();
        if (diferencia >= 7 * 24 * 60 * 60 * 1000) {
            //String update =
            String sql = "UPDATE usuarios SET sancionado = 1 WHERE DNI = ?";
            try (PreparedStatement stmtUpdate = conexion.conexion.prepareStatement(sql)) {

                stmtUpdate.setString(1, DNI);
                stmtUpdate.executeUpdate();// Ejecutar la consulta
                ventanaDialogo("USUARIO SANCIONADO", "El usuario ha sido sancionado por un retraso " + "en la entrega");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    boolean mensajeErrorDevolucion() {
        boolean hayError = false;
        StringBuilder mensajeError = new StringBuilder();

        String DNI = leerCampo("DNI", tfDevolucionDNI.getText(), "[0-9]{8}[A-Za-z]");
        String ISBN = leerCampo("ISBN", tfDevolucionISBN.getText(), ".{10,13}");

        hayError = compruebaDNI(DNI, mensajeError) ? true : hayError;
        hayError = compruebaISBN(ISBN, mensajeError) ? true : hayError;


        if (hayError) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("CAMPOS ERRÓNEOS");
            error.setContentText("Hay error en los siguientes campos:\n" + mensajeError.toString());
            error.showAndWait();
        }
        return hayError;
    }

    void consultaFechaDevolucion() {
        String sql = "UPDATE prestamos SET fecha_devolucion = ? WHERE DNI_usuario=? AND ISBN=?";
        try (PreparedStatement stmt = conexion.conexion.prepareStatement(sql)) {

            stmt.setString(1, String.valueOf(LocalDate.now()));
            stmt.setString(2, tfDevolucionDNI.getText());
            stmt.setString(3, tfDevolucionISBN.getText());

            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("DEVOLVER LIBRO", "Libro devuelto con éxito");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void cargarTablaDevoluciones() throws SQLException {
        ObservableList<Prestamo> listaPrestamos = FXCollections.observableArrayList();

        Statement stmt = conexion.conexion.createStatement();

        String query = "SELECT * FROM prestamos";

        ResultSet datos = stmt.executeQuery(query);
        while (datos.next()) {
            listaPrestamos.add(new Prestamo(datos.getString("DNI_usuario"), datos.getString("ISBN")));

        }

        tvPrestamos.setItems(listaPrestamos);

        colPrestamoDNI.setCellValueFactory(new PropertyValueFactory<>("DNI_usuario"));
        colPrestamoISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
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
        try {
            String DNI = tvUsuarios.getSelectionModel().getSelectedItem().getDNI();
            Alert confirmar = ventanaConfirmacion("ELIMINAR USUARIO", "Eliminar usuario", "¿Está seguro de que desea eliminar a este usuario?");
            Optional<ButtonType> resultado = confirmar.showAndWait();
            if (resultado.get() == confirmar.getButtonTypes().get(0)) {
                eliminarUsuario(DNI);
                cargarTablaUsuarios();
            }
        } catch (NullPointerException e) {
            ventanaDialogo("ERROR", "No hay ningún usuario seleccionado");
        }
    }

    @FXML
    void modificarUsuario() throws SQLException {
        try {
            cambiarVistaFormUsuario();
            editaUsuario = true;
            cargarFormUsuarios(tvUsuarios.getSelectionModel().getSelectedItem().getDNI());
        } catch (NullPointerException npe) {
            ventanaDialogo("ERROR", "No hay ningún usuario seleccionado");
        }

    }

    private void cargarFormUsuarios(String DNI) throws SQLException {
        Usuario usuario = null;
        Statement stmt = conexion.conexion.createStatement();

        String query = "SELECT * FROM usuarios WHERE DNI = '" + DNI + "'";
        ResultSet datos = stmt.executeQuery(query);
        while (datos.next()) {
            usuario = new Usuario(datos.getString("DNI"), datos.getString("nombre"), datos.getString("apellidos"), datos.getString("domicilio"), datos.getString("telefono"), datos.getString("email"), datos.getString("clave"));
        }
        tfDNI.setEditable(false);
        tfDNI.setText(usuario.getDNI());
        tfNombre.setText(usuario.getNombre());
        tfApellidos.setText(usuario.getApellidos());
        tfDomicilio.setText(usuario.getDomicilio());
        tfTelefono.setText(usuario.getTelefono());
        tfEmail.setText(usuario.getEmail());
        tfClave.setText(usuario.getClave());
    }


    @FXML
    void nuevoUsuario() {
        cambiarVistaFormUsuario();
        editaUsuario = false;
        tfDNI.setEditable(true);
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
        if (!filtro.equals("")) {
            query += " WHERE " + "DNI LIKE '%" + filtro + "%' OR " + "nombre LIKE '%" + filtro + "%' OR " + "apellidos LIKE '%" + filtro + "%' OR " + "domicilio LIKE '%" + filtro + "%' OR " + "telefono LIKE '%" + filtro + "%' OR " + "email LIKE '%" + filtro + "%' OR " + "clave LIKE '%" + filtro + "%';";
        }

        ResultSet datos = stmt.executeQuery(query);
        while (datos.next()) {
            listaUsuarios.add(new Usuario(datos.getString("DNI"), datos.getString("nombre"), datos.getString("apellidos"), datos.getString("domicilio"), datos.getString("telefono"), datos.getString("email"), datos.getString("clave")));
        }

        tvUsuarios.setItems(listaUsuarios);

        colDNI.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
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
            tfPrestamoDNI.setId("tfError");
            return true;
        } else {
            tfDNI.setId("tfNormal");
            tfPrestamoDNI.setId("tfNormal");
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
            mensajeError.append("Clave (Debe tener al menos 8 caracteres, 1 número, 1 mayúscula y 1 minúscula)\n");
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
        String sql = "INSERT INTO usuarios (DNI, nombre, apellidos, domicilio, telefono, email, clave) " + "VALUES (?, ?, ?, ?, ?, ?, ?)"; // Consulta para insertar usuarios en la base de datos
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
    String leerCampo(String nombreCampoL, String texto, String criterioValidacion) {
        return (texto == null || !texto.matches(criterioValidacion)) ? null : texto;
    }

    private static void ventanaDialogo(String titulo, String mensaje) {
        // Ventana de error
        Dialog<String> ventana = new Dialog<>();
        ventana.setTitle(titulo);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ventana.setContentText(mensaje);
        ventana.getDialogPane().getButtonTypes().add(type);
        ventana.showAndWait();
    }

    public static Alert ventanaConfirmacion(String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);

        ButtonType buttonTypeYes = new ButtonType("Si");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        return alert;
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
        if (!sesionIniciada) {
            panelLogin.setVisible(true);
        } else {
            panelUsuarios.setVisible(true);
        }
        panelPrincipal.setVisible(false);
        panelLibros.setVisible(false);
        panelPrestamos.setVisible(false);
        panelDevoluciones.setVisible(false);
        panelRegistroUsuarios.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cargarTablaLibros();
            cargarTablaUsuarios();
            cargarTablaDevoluciones();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<String> categorias = Arrays.asList("Terror", "Ficción", "Drama", "Bélica", "Infantil", "Comedia", "Aventuras", "Cómic");
        cbCategoria.getItems().addAll(categorias);
    }
}