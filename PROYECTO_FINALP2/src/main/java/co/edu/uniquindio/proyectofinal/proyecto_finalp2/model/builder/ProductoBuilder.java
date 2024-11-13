package co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.builder;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.Producto;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.Vendedor;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.EstadoProducto;

import java.time.LocalDateTime;
import java.util.ArrayList;
public class ProductoBuilder {
    private String nombre;
    private String imagen;
    private String categoria;
    private double precio;
    private EstadoProducto estado;
    private Vendedor vendedor;

    public ProductoBuilder() {
    }

    public ProductoBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProductoBuilder conImagen(String imagen) {
        this.imagen = imagen;
        return this;
    }

    public ProductoBuilder conCategoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public ProductoBuilder conPrecio(double precio) {
        this.precio = precio;
        return this;
    }

    public ProductoBuilder conEstado(EstadoProducto estado) {
        this.estado = estado;
        return this;
    }

    public ProductoBuilder conVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
        return this;
    }

    public Producto build() {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setImagen(imagen);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setEstado(estado != null ? estado : EstadoProducto.PUBLICADO);
        producto.setVendedor(vendedor);
        producto.setFechaPublicacion(LocalDateTime.now());
        producto.setComentarios(new ArrayList<>());
        producto.setMeGusta(new ArrayList<>());
        return producto;
    }
}
