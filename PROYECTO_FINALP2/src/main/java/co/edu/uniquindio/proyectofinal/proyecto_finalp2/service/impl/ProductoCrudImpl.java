package co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.impl;

import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.*;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.interfaces.IProductoCrud;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoCrudImpl implements IProductoCrud {
    private final MarketPlace marketPlace;

    public ProductoCrudImpl() {
        this.marketPlace = MarketPlace.getInstance();
    }

    @Override
    public void agregarProducto(Producto producto) {
        marketPlace.getProductos().add(producto);
        producto.getVendedor().getProductos().add(producto);
    }

    @Override
    public void eliminarProducto(String nombre) {
        Producto producto = buscarProducto(nombre);
        if (producto != null) {
            marketPlace.getProductos().remove(producto);
            producto.getVendedor().getProductos().remove(producto);
        }
    }

    @Override
    public void actualizarProducto(Producto producto) {
        Producto productoExistente = buscarProducto(producto.getNombre());
        if (productoExistente != null) {
            int index = marketPlace.getProductos().indexOf(productoExistente);
            marketPlace.getProductos().set(index, producto);

            // Actualizar en la lista del vendedor
            List<Producto> productosVendedor = producto.getVendedor().getProductos();
            int indexVendedor = productosVendedor.indexOf(productoExistente);
            if (indexVendedor != -1) {
                productosVendedor.set(indexVendedor, producto);
            }
        }
    }

    @Override
    public Producto buscarProducto(String nombre) {
        return marketPlace.getProductos().stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Producto> listarProductos() {
        return marketPlace.getProductos();
    }

    @Override
    public List<Producto> buscarPorCategoria(String categoria) {
        return marketPlace.getProductos().stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> buscarPorVendedor(String cedulaVendedor) {
        return marketPlace.getProductos().stream()
                .filter(p -> p.getVendedor().getCedula().equals(cedulaVendedor))
                .collect(Collectors.toList());
    }
}
