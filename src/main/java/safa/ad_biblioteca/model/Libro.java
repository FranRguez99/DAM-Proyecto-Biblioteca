package safa.ad_biblioteca.model;

import javafx.scene.image.ImageView;

import java.io.Serializable;

// Clase Libro
public class Libro {
    String ISBN;
    String titulo;
    String autor;
    String categoria;
    String idioma;
    String paginas;
    String ejemplares;

    @Override
    public String toString() {
        return "Titulo: " + titulo + " - Autor: " + autor + " - Categoria: " + categoria;
    }


    public Libro(String ISBN, String titulo, String autor, String categoria, String idioma, String paginas, String ejemplares) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.idioma = idioma;
        this.paginas = paginas;
        this.ejemplares = ejemplares;

    }

    public Libro(String titulo, String autor, String ISBN) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;

    }

    public Libro(String titulo) {
        this.titulo = titulo;
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
