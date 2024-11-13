package co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.impl;


import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.*;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.EstadoProducto;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.interfaces.IVendedorCrud;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.MarketPlaceApplication;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.Producto;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.Vendedor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VendedorCrudImpl implements IVendedorCrud {
    private final Vendedor vendedorActual;
    private final MarketPlaceApplication marketPlace;

    public VendedorCrudImpl(Vendedor vendedor) {
        this.vendedorActual = vendedor;
        this.marketPlace = MarketPlaceApplication.getInstance();
    }

    @Override
    public void publicarProducto(Producto producto) {
        if (producto == null || producto.getVendedor() == null ||
                !producto.getVendedor().equals(vendedorActual)) {
            throw new IllegalArgumentException("Producto inválido o vendedor no autorizado");
        }
        vendedorActual.getProductos().add(producto);
        marketPlace.getProductos().add(producto);
    }



    @Override
    public void actualizarEstadoProducto(Producto producto, EstadoProducto estado) {
        if (!vendedorActual.getProductos().contains(producto)) {
            throw new IllegalArgumentException("El producto no pertenece al vendedor actual");
        }
        producto.setEstado(estado);
    }

    @Override
    public List<Producto> listarMisProductos() {
        return new ArrayList<>(vendedorActual.getProductos());
    }

    @Override
    public List<Vendedor> listarMisContactos() {
        return new ArrayList<>(vendedorActual.getContactos());
    }

    @Override
    public void agregarContacto(Vendedor contacto) {
        if (vendedorActual.getContactos().size() >= 10) {
            throw new IllegalStateException("Has alcanzado el límite máximo de contactos");
        }
        if (!vendedorActual.getContactos().contains(contacto)) {
            vendedorActual.agregarContacto(contacto);
            contacto.agregarContacto(vendedorActual);
        }
    }

    @Override
    public List<String> obtenerMuroMensajes() {
        return new ArrayList<>(vendedorActual.getMuroMensajes());
    }

    @Override
    public void agregarMensaje(String mensaje) {
        String mensajeFormateado = LocalDateTime.now() + " - " + vendedorActual.getNombre() + ": " + mensaje;
        vendedorActual.getMuroMensajes().add(mensajeFormateado);
    }

    @Override
    public void agregarComentario(Producto producto, String contenido) {
        // Verificar si el producto es del vendedor o de sus contactos
        if (!esProductoAccesible(producto)) {
            throw new IllegalArgumentException("No tienes permiso para comentar este producto");
        }

        Comentario comentario = new Comentario();
        comentario.setContenido(contenido);
        comentario.setAutor(vendedorActual);
        comentario.setProducto(producto);
        comentario.setFecha(LocalDateTime.now());

        producto.getComentarios().add(comentario);
        vendedorActual.getComentarios().add(comentario);
    }

    @Override
    public void agregarMeGusta(Producto producto) {
        if (!esProductoAccesible(producto)) {
            throw new IllegalArgumentException("No tienes permiso para dar me gusta a este producto");
        }

        // Verificar si ya dio me gusta
        boolean yaExiste = producto.getMeGusta().stream()
                .anyMatch(mg -> mg.getVendedor().equals(vendedorActual));

        if (!yaExiste) {
            MeGusta meGusta = new MeGusta();
            meGusta.setVendedor(vendedorActual);
            meGusta.setProducto(producto);
            meGusta.setFecha(LocalDateTime.now());
            producto.getMeGusta().add(meGusta);
        }
    }

    @Override
    public void eliminarProducto(String nombre) {

    }

    private boolean esProductoAccesible(Producto producto) {
        if (producto.getVendedor().equals(vendedorActual)) {
            return true;
        }
        return vendedorActual.getContactos().contains(producto.getVendedor());
    }
}