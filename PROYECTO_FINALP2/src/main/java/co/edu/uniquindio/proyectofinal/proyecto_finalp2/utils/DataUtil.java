package co.edu.uniquindio.proyectofinal.proyecto_finalp2.utils;

import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.*;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.builder.ProductoBuilder;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.builder.VendedorBuilder;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.EstadoProducto;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {
    private static DataUtil instance;
    private List<Vendedor> vendedores;
    private List<Producto> productos;
    private Administrador administrador;

    private DataUtil() {
        this.vendedores = new ArrayList<>();
        this.productos = new ArrayList<>();
        inicializarDatos();
    }

    public static DataUtil getInstance() {
        if (instance == null) {
            instance = new DataUtil();
        }
        return instance;
    }

    private void inicializarDatos() {
        // creamos administrador por defecto
        administrador = new Administrador(
                "Administrador",
                "Sistema",
                "1234567890",
                "Dirección Admin",
                "admin",
                "admin123"
        );

        // creamos vendedores usando el Builder
        Vendedor vendedor1 = new VendedorBuilder()
                .conNombre("Juan")
                .conApellidos("Pérez")
                .conCedula("123456789")
                .conDireccion("Calle 1 #1-1")
                .conUsuario("juan.perez")
                .conContrasena("123456")
                .build();

        Vendedor vendedor2 = new VendedorBuilder()
                .conNombre("María")
                .conApellidos("González")
                .conCedula("987654321")
                .conDireccion("Calle 2 #2-2")
                .conUsuario("maria.gonzalez")
                .conContrasena("123456")
                .build();

        Vendedor vendedor3 = new VendedorBuilder()
                .conNombre("Carlos")
                .conApellidos("Rodríguez")
                .conCedula("456789123")
                .conDireccion("Calle 3 #3-3")
                .conUsuario("carlos.rodriguez")
                .conContrasena("123456")
                .build();

        vendedores.add(vendedor1);
        vendedores.add(vendedor2);
        vendedores.add(vendedor3);

        // Creamos productos usando el Builder
        Producto laptop = new ProductoBuilder()
                .conNombre("Laptop HP")
                .conCategoria("Electrónicos")
                .conPrecio(2500000)
                .conVendedor(vendedor1)
                .conImagen("/images/laptop.png")
                .conEstado(EstadoProducto.PUBLICADO)
                .build();

        Producto smartphone = new ProductoBuilder()
                .conNombre("iPhone 13")
                .conCategoria("Electrónicos")
                .conPrecio(3800000)
                .conVendedor(vendedor2)
                .conImagen("/images/iphone.png")
                .conEstado(EstadoProducto.PUBLICADO)
                .build();

        Producto tablet = new ProductoBuilder()
                .conNombre("iPad Pro")
                .conCategoria("Electrónicos")
                .conPrecio(4200000)
                .conVendedor(vendedor3)
                .conImagen("/images/ipad.png")
                .conEstado(EstadoProducto.PUBLICADO)
                .build();

        // Agregar productos a vendedores y lista general
        vendedor1.getProductos().add(laptop);
        vendedor2.getProductos().add(smartphone);
        vendedor3.getProductos().add(tablet);

        productos.add(laptop);
        productos.add(smartphone);
        productos.add(tablet);

        // Establecer algunas relaciones entre vendedores
        vendedor1.agregarContacto(vendedor2);
        vendedor2.agregarContacto(vendedor3);

        // Agregar algunos comentarios y me gusta
        Comentario comentario1 = new Comentario();
        comentario1.setContenido("¡Excelente producto!");
        comentario1.setAutor(vendedor2);
        comentario1.setProducto(laptop);
        laptop.getComentarios().add(comentario1);

        MeGusta meGusta1 = new MeGusta();
        meGusta1.setVendedor(vendedor3);
        meGusta1.setProducto(smartphone);
        smartphone.getMeGusta().add(meGusta1);

        // Agregar algunos mensajes al muro
        vendedor1.getMuroMensajes().add("¡Nuevo producto disponible: Laptop HP!");
        vendedor2.getMuroMensajes().add("Ofertas especiales en smartphones");
    }

    public List<Vendedor> getVendedores() {
        return new ArrayList<>(vendedores);
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public Vendedor buscarVendedor(String usuario, String contrasena) {
        return vendedores.stream()
                .filter(v -> v.getUsuario().equals(usuario) &&
                        v.getContrasena().equals(contrasena))
                .findFirst()
                .orElse(null);
    }

    public boolean esAdministradorValido(String usuario, String contrasena) {
        return administrador.getUsuario().equals(usuario) &&
                administrador.getContrasena().equals(contrasena);
    }

//    public void guardarDatos() {
//        try {
//            List<Vendedor> vendedores = marketPlace.getVendedores();
//            List<Producto> productos = marketPlace.getProductos();
//            Administrador admin = marketPlace.getAdministrador();
//
//
//            System.out.println("Guardando datos de la aplicación...");
//            System.out.println("Vendedores guardados: " + vendedores.size());
//            System.out.println("Productos guardados: " + productos.size());
//            System.out.println("Datos guardados exitosamente.");
//
//        } catch (Exception e) {
//            System.err.println("Error al guardar los datos: " + e.getMessage());
//        }
//    }
}
