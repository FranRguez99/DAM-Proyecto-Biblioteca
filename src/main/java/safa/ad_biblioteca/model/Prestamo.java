package safa.ad_biblioteca.model;

import java.util.Date;

public class Prestamo {

    String DNI_usuario;
    String ISBN;
    Date fecha_salida;
    Date fecha_devolucion;

    public Prestamo(String DNI_usuario, String ISBN) {
        this.DNI_usuario = DNI_usuario;
        this.ISBN = ISBN;
    }

    public Prestamo(String DNI_usuario, String ISBN, Date fecha_salida, Date fecha_devolucion) {
        this.DNI_usuario = DNI_usuario;
        this.ISBN = ISBN;
        this.fecha_salida = fecha_salida;
        this.fecha_devolucion = fecha_devolucion;
    }

    public String getDNI_usuario() {
        return DNI_usuario;
    }

    public void setDNI_usuario(String DNI_usuario) {
        this.DNI_usuario = DNI_usuario;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }
}
