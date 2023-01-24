package safa.ad_biblioteca;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import safa.ad_biblioteca.model.Conexion;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    @FXML
    private TableView<?> tablaLibros;

    @FXML
    private TableColumn<?, ?> ISBNcol;


    @FXML
    private TableColumn<?, ?> tituloCol;

    @FXML
    private TableColumn<?, ?> autorCol;

    @FXML
    private TableColumn<?, ?> categoriaCol;

    @FXML
    private TableColumn<?, ?> idiomaCol;

    @FXML
    private TableColumn<?, ?> paginalCol;

    @FXML
    private TableColumn<?, ?> ejemplaresCol;

    // Atributos
    Conexion conexion = new Conexion();
    Boolean editaUsuario;
    Boolean editaLibro;

    // Métodos

    // Métodos pestaña Libros
    public class Libro {  // No se si quitarle los private
        private String ISBN;
        private String titulo;
        private String autor;
        private String categoria;
        private String idioma;
        private String paginas;
        private String ejemplares;

        public Libro(String ISBN, String titulo, String autor, String categoria, String idioma, String paginas, String ejemplares) {
            this.ISBN = ISBN;
            this.titulo = titulo;
            this.autor = autor;
            this.categoria = categoria;
            this.idioma = idioma;
            this.paginas = paginas;
            this.ejemplares = ejemplares;

        }

        public String getISBN() {
            return ISBN;
        }

        public void setISBN(String ISBN) {
            this.ISBN = ISBN;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }
        public String getIdioma() {
            return idioma;
        }

        public void setIdioma(String idioma) {
            this.idioma = idioma;
        }
        public String getPaginas() {
            return paginas;
        }

        public void setPaginas(String paginas) {
            this.paginas = paginas;
        }

        public String getEjemplares() {
            return ejemplares;
        }

        public void setEjemplares(String ejemplares) {
            this.ejemplares = ejemplares;
        }

    }



            // Una clase LibroDAO (Data Access Object) que se encargue de realizar las operaciones CRUD con la base de datos.
 // Esta clase tiene los métodos para insertar, actualizar y eliminar libros, así como un método para obtener todos los libros de la tabla
    public class LibroDAO {
        private Conexion conexion;

        public LibroDAO() {
            conexion = new Conexion();
        }

        public void insertarLibro(Libro libro) throws SQLException {
            String sql = "INSERT INTO libros (ISBN, titulo, autor, categoria, idioma, num_paginas, num_ejemplares) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexion.conexion.prepareStatement(sql);
            statement.setString(1, libro.getISBN());
            statement.setString(2, libro.getTitulo());
            statement.setString(3, libro.getAutor());
            statement.setString(4, libro.getCategoria());
            statement.setString(5, libro.getIdioma());
            statement.setString(6, libro.getPaginas());
            statement.setString(7, libro.getEjemplares());
            statement.executeUpdate();
        }

        public void actualizarLibro(Libro libro) throws SQLException {
            String sql = "UPDATE libros SET titulo=?, autor=?, categoria=?, idioma=?, num_paginas=?, num_ejemplares=? WHERE ISBN=?";
            PreparedStatement statement = conexion.conexion.prepareStatement(sql);
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getCategoria());
            statement.setString(4, libro.getIdioma());
            statement.setString(5, libro.getPaginas());
            statement.setString(6, libro.getEjemplares());
            statement.setString(7, libro.getISBN());
            statement.executeUpdate();
        }

        public void eliminarLibro(String ISBN) throws SQLException {

            String sql = "DELETE FROM libros WHERE ISBN=?";
            PreparedStatement statement = conexion.conexion.prepareStatement(sql);
            statement.setString(1, ISBN);
            statement.executeUpdate();
        }

        public List<Libro> obtenerLibros() throws SQLException {
            List<Libro> libros = new ArrayList<>();
            String sql = "SELECT ISBN, titulo, autor, categoria, idioma, num_paginas, num_ejemplares FROM libros";
            ResultSet resultado = conexion.conexion.createStatement().executeQuery(sql);
            while (resultado.next()) {
                Libro libro = new Libro(
                        resultado.getString("ISBN"),
                        resultado.getString("titulo"),
                        resultado.getString("categoria"),
                        resultado.getString("autor"),
                        resultado.getString("idioma"),
                        resultado.getString("paginas"),
                        resultado.getString("ejemplares")
                );
                libros.add(libro);
            }
            return libros;

        }
    }

    @FXML
    void aceptarLibros(ActionEvent event) {

    }

    @FXML
    void borrarLibro(ActionEvent event) throws SQLException {

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

    String leerCampoLibro(String nombreCampo, String texto, String criterioValidacion) {
        if (texto.matches(criterioValidacion)) {
            return texto;
        } else {
            return null;
        }
    }
    ArrayList<Object> leeValoresLibro() {
        ArrayList<Object> libros = new ArrayList<Object>();
        libros.add(leerCampoLibro("ISBN",tfISBN.getText(),".{1,13}"));
        libros.add(leerCampoLibro("Titulo",tfTitulo.getText(),".{1,50}"));
        libros.add(leerCampoLibro("Autor",tfAutor.getText(),".{1,50}"));
        libros.add(leerCampoLibro("Categoria", (String) cBoxCategoria.getValue(),"^\\s*$"));
        libros.add(leerCampoLibro("Idioma",tfIdioma.getText(),".{1,20}"));
        libros.add(leerCampoLibro("Paginas",tfPaginas.getText(),".{1,3}"));
        libros.add(leerCampoLibro("Ejemplares",tfEjemplares.getText(),".{1,3}"));

        return libros;
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

            mensajeError.append("Categoría (Seleccione una categoría)\n");
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
        if(Paginas == null) {
            mensajeError.append("Páginas (No puede estar vacío.)\n");
            return true;
        }
        return false;
    }

    private boolean compruebaEjemplares (String Ejemplares, StringBuilder mensajeError) {
        if(Ejemplares == null) {
            mensajeError.append("Ejemplares (No puede estar vacío.)\n");
            return true;
        }
        return false;
    }

    boolean mensajeErrorLibro(ArrayList<Object>libros ) {
        boolean hayError = false;
        StringBuilder mensajeError = new StringBuilder();

        hayError = compruebaISBN((String) libros.get(0), mensajeError) ? true : hayError;
        hayError = compruebaTitulo((String) libros.get(1), mensajeError) ? true : hayError;
        hayError = compruebaAutor((String) libros.get(2), mensajeError) ? true : hayError;
        hayError = compruebaCategoria((String) libros.get(3), mensajeError) ? true : hayError;
        hayError = compruebaIdioma((String) libros.get(4), mensajeError) ? true : hayError;
        hayError = compruebaPaginas((String) libros.get(5), mensajeError) ? true : hayError;
        hayError = compruebaEjemplares((String) libros.get(6), mensajeError) ? true : hayError;


        if (hayError){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("CAMPOS ERRÓNEOS");
            error.setContentText("Hay error en los siguientes campos: " + mensajeError.toString());
            error.showAndWait();
        }
        return hayError;
    }
    void insertarLibro(Libro libro) throws SQLException {
        ArrayList<Object> libros = leeValoresLibro();

        btnRegLibREgistrar.setOnAction(event -> {
            try {
                Conexion conexion = new Conexion();
                String sql = "INSERT INTO libros (ISBN, titulo, autor, categoria, idioma, num_paginas, num_ejemplares) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = conexion.conexion.prepareStatement(sql);
                statement.setString(1, libro.getISBN());
                statement.setString(2, libro.getTitulo());
                statement.setString(3, libro.getAutor());
                statement.setString(4, libro.getCategoria());
                statement.setString(5, libro.getIdioma());
                statement.setString(6, libro.getPaginas());
                statement.setString(7, libro.getEjemplares());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        // Cerrar las conexiones
        //stmt.close();
        conexion.conexion.close();

    }
    public void actualizarLibro(Libro libro) throws SQLException {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, categoria = ?, idioma = ?, num_paginas = ?, num_ejemplares = ? WHERE ISBN = ?";
        PreparedStatement statement = conexion.conexion.prepareStatement(sql);
        statement.setString(1, libro.getTitulo());
        statement.setString(2, libro.getAutor());
        statement.setString(3, libro.getCategoria());
        statement.setString(4, libro.getIdioma());
        statement.setString(5, libro.getPaginas());
        statement.setString(6, libro.getEjemplares());
        statement.setString(7, libro.getISBN());
        statement.execute();
    }
    public void eliminarLibro(Libro libro) throws SQLException {
        String sql = "DELETE FROM libros WHERE ISBN = ?";
        PreparedStatement statement = conexion.conexion.prepareStatement(sql);
        statement.setString(1, libro.getISBN());
        statement.execute();
    }

    /* MÉTODOS PESTAÑA USUARIO */

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

    String leerCampo(String nombreCampo, String texto, String criterioValidacion) {
        if (texto.matches(criterioValidacion)) {
            return texto;
        } else {
            return null;
        }
    }



    ArrayList<Object> leerValoresUsuario() {
        ArrayList<Object> valores = new ArrayList<Object>();
        valores.add(leerCampo("DNI", tfDNI.getText(), "[0-9]{8}[A-Za-z]"));
        valores.add(leerCampo("Nombre", tfNombre.getText(), ".{1,20}"));
        valores.add(leerCampo("Apellidos", tfApellidos.getText(), ".{1,40}"));
        valores.add(leerCampo("Domicilio", tfDomicilio.getText(), ".{1,40}"));
        valores.add(leerCampo("Telefono", tfTelefono.getText(), "^[0-9]{9}$"));
        valores.add(leerCampo("Email", tfEmail.getText(), "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$"));
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
        if(telefono == null) {
            mensajeError.append("Teléfono (9 dígitos)\n");
            return true;
        }
        return false;
    }

    private boolean compruebaEmail(String email, StringBuilder mensajeError) {
        if(email == null) {
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

        if (hayError){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("CAMPOS ERRÓNEOS");
            error.setContentText("Hay error en los siguientes campos: " + mensajeError.toString());
            error.showAndWait();
        }
        return hayError;
    }


    void insertarUsuario() throws SQLException {
        // Añadir un if comprobando el mensaje de error
        ArrayList<Object> valores = leerValoresUsuario();
        // Dar como parámetro al if la lista

        String sql = "INSERT INTO usuarios (DNI, nombre, apellidos, domicilio, telefono, email) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
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
    void volverLibros() {

        //* Vaciar campos formulario libros
        tfISBN.setText("");
        tfTitulo.setText("");
        tfAutor.setText("");
        cBoxCategoria.setValue(null);
        tfIdioma.setText("");
        tfPaginas.setText("");
        tfEjemplares.setText("");
        panelPrincipal.setVisible(true);
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