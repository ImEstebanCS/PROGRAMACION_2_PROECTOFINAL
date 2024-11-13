package co.edu.uniquindio.proyectofinal.proyecto_finalp2.model;

import co.edu.uniquindio.proyectofinal.proyecto_finalp2.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;

public class MarketPlace {
    private static MarketPlace instance;
    private List<Vendedor> vendedores;
    private List<Producto> productos;
    private Administrador administrador;
    private DataUtil dataUtil;

    private MarketPlace() {
        this.vendedores = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.dataUtil = DataUtil.getInstance();
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        this.administrador = dataUtil.getAdministrador();
        this.vendedores.addAll(dataUtil.getVendedores());
        this.productos.addAll(dataUtil.getProductos());
    }

    public static MarketPlace getInstance() {
        if (instance == null) {
            instance = new MarketPlace();
        }
        return instance;
    }

    public void agregarVendedor(Vendedor vendedor) {
        if (vendedor != null && !existeVendedor(vendedor.getCedula())) {
            vendedores.add(vendedor);
        }
    }

    private boolean existeVendedor(String cedula) {
        return vendedores.stream()
                .anyMatch(v -> v.getCedula().equals(cedula));
    }

    /**
     * Obtiene la lista de todos los productos en el marketplace
     */
    public List<Producto> getProductos() {
        // Retornamos una nueva lista para evitar modificaciones externas directas
        return new ArrayList<>(productos);
    }

    /**
     * Obtiene los productos por categoría
     * @param categoria la categoría a buscar
     * @return lista de productos de esa categoría
     */
    public List<Producto> getProductosPorCategoria(String categoria) {
        List<Producto> productosPorCategoria = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCategoria().equals(categoria)) {
                productosPorCategoria.add(producto);
            }
        }
        return productosPorCategoria;
    }

    /**
     * Obtiene los productos de un vendedor específico
     * @param cedulaVendedor cédula del vendedor
     * @return lista de productos del vendedor
     */
    public List<Producto> getProductosVendedor(String cedulaVendedor) {
        List<Producto> productosVendedor = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getVendedor().getCedula().equals(cedulaVendedor)) {
                productosVendedor.add(producto);
            }
        }
        return productosVendedor;
    }

    /**
     * Agrega un nuevo producto al marketplace
     * @param producto el producto a agregar
     * @return true si se agregó exitosamente
     */
    public boolean agregarProducto(Producto producto) {
        if (producto != null && !productos.contains(producto)) {
            return productos.add(producto);
        }
        return false;
    }

    public boolean eliminarProducto(Producto producto) {
        if (producto != null) {
            return productos.remove(producto);
        }
        return false;
    }

    public List<Vendedor> getVendedores() {
        return new ArrayList<>(vendedores);
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores != null ? new ArrayList<>(vendedores) : new ArrayList<>();
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public boolean autenticarUsuario(String usuario, String contrasena) {
        if (administrador.getUsuario().equals(usuario) &&
                administrador.getContrasena().equals(contrasena)) {
            return true;
        }
        return vendedores.stream()
                .anyMatch(v -> v.getUsuario().equals(usuario) &&
                        v.getContrasena().equals(contrasena));
    }

    public Object obtenerUsuario(String usuario, String contrasena) {
        if (administrador.getUsuario().equals(usuario) &&
                administrador.getContrasena().equals(contrasena)) {
            return administrador;
        }
        return vendedores.stream()
                .filter(v -> v.getUsuario().equals(usuario) &&
                        v.getContrasena().equals(contrasena))
                .findFirst()
                .orElse(null);
    }
}