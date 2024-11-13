package co.edu.uniquindio.proyectofinal.proyecto_finalp2.model;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends  Persona {
    private List<Producto> productos;
    private List<Vendedor> contactos;
    private List<String> muroMensajes;
    private List<Comentario> comentarios;

    public Vendedor() {
        this.productos = new ArrayList<>();
        this.contactos = new ArrayList<>();
        this.muroMensajes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    public Vendedor(String nombre, String apellidos, String cedula, String direccion, String usuario, String contrasena) {
        super(nombre, apellidos, cedula, direccion, usuario, contrasena);
        this.productos = new ArrayList<>();
        this.contactos = new ArrayList<>();
        this.muroMensajes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Vendedor> getContactos() {
        return contactos;
    }

    public void setContactos(List<Vendedor> contactos) {
        this.contactos = contactos;
    }

    public List<String> getMuroMensajes() {
        return muroMensajes;
    }

    public void setMuroMensajes(List<String> muroMensajes) {
        this.muroMensajes = muroMensajes;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void agregarContacto(Vendedor vendedor) {
        if (contactos.size() < 10 && !contactos.contains(vendedor)) {
            contactos.add(vendedor);
            vendedor.getContactos().add(this);
        }
    }

    public void agregarMensaje(String mensaje) {
        muroMensajes.add(mensaje);
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }
}
