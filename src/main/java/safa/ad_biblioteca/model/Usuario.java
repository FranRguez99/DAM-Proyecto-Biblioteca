package safa.ad_biblioteca.model;

import java.util.Date;

public class Usuario {

    String DNI;
    String nombre;
    String apellidos;
    String domicilio;
    String telefono;
    String email;
    Boolean sancionado;
    String clave;
    Date fecha_Sancion;



    public Usuario(String DNI, String nombre, String apellidos, String domicilio, String telefono, String email, String clave) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.email = email;
        this.sancionado = false;
        this.clave = clave;
        this.fecha_Sancion = null;
    }

    public Usuario(String DNI, String nombre, String apellidos, String domicilio, String telefono, String email, int sancionado, String clave, Date fecha_Sancion) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.email = email;
        this.sancionado = sancionado == 1;
        this.clave = clave;
        this.fecha_Sancion = fecha_Sancion;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSancionado() {
        return sancionado;
    }

    public void setSancionado(Boolean sancionado) {
        this.sancionado = sancionado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFecha_Sancion() {
        return fecha_Sancion;
    }

    public void setFecha_Sancion(Date fecha_Sancion) {
        this.fecha_Sancion = fecha_Sancion;
    }
}
