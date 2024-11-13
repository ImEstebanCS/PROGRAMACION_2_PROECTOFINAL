package co.edu.uniquindio.proyectofinal.proyecto_finalp2.model;

import java.time.LocalDateTime;

public class MeGusta {
    private Vendedor vendedor;
    private LocalDateTime fecha;
    private Producto producto;

    public MeGusta() {
        this.fecha = LocalDateTime.now();
    }

    public MeGusta(Vendedor vendedor, Producto producto) {
        this.vendedor = vendedor;
        this.producto = producto;
        this.fecha = LocalDateTime.now();
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Me gusta de " + vendedor.getNombre() + " en " + producto.getNombre();
    }
}
