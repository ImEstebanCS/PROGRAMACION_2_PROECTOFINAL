package co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.builder;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.Vendedor;
import java.util.ArrayList;

public class VendedorBuilder {
    private String nombre;
    private String apellidos;
    private String cedula;
    private String direccion;
    private String usuario;
    private String contrasena;

    public VendedorBuilder() {
    }

    public VendedorBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public VendedorBuilder conApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public VendedorBuilder conCedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public VendedorBuilder conDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public VendedorBuilder conUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public VendedorBuilder conContrasena(String contrasena) {
        this.contrasena = contrasena;
        return this;
    }

    public Vendedor build() {
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(nombre);
        vendedor.setApellidos(apellidos);
        vendedor.setCedula(cedula);
        vendedor.setDireccion(direccion);
        vendedor.setUsuario(usuario);
        vendedor.setContrasena(contrasena);
        vendedor.setProductos(new ArrayList<>());
        vendedor.setContactos(new ArrayList<>());
        vendedor.setMuroMensajes(new ArrayList<>());
        vendedor.setComentarios(new ArrayList<>());
        return vendedor;
    }
}
