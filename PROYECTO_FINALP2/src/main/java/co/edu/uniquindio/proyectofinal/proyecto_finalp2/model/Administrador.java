package co.edu.uniquindio.proyectofinal.proyecto_finalp2.model;

public class Administrador  extends  Persona {
    public Administrador() {
        super();
    }

    public Administrador(String nombre, String apellidos, String cedula,
                         String direccion, String usuario, String contrasena) {
        super(nombre, apellidos, cedula, direccion, usuario, contrasena);
    }

    public boolean verificarCredenciales(String usuario, String contrasena) {
        return this.usuario.equals(usuario) && this.contrasena.equals(contrasena);
    }

    public boolean validarDatos() {
        return nombre != null && !nombre.isEmpty() &&
                apellidos != null && !apellidos.isEmpty() &&
                cedula != null && !cedula.isEmpty() &&
                usuario != null && !usuario.isEmpty() &&
                contrasena != null && !contrasena.isEmpty();
    }

    @Override
    public String toString() {
        return "Administrador: " + nombre + " " + apellidos + " (CC: " + cedula + ")";
    }

    public void actualizarDatos(String nombre, String apellidos,
                                String direccion, String usuario, String contrasena) {
        if (nombre != null && !nombre.isEmpty()) this.nombre = nombre;
        if (apellidos != null && !apellidos.isEmpty()) this.apellidos = apellidos;
        if (direccion != null && !direccion.isEmpty()) this.direccion = direccion;
        if (usuario != null && !usuario.isEmpty()) this.usuario = usuario;
        if (contrasena != null && !contrasena.isEmpty()) this.contrasena = contrasena;
    }

}
