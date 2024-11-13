package co.edu.uniquindio.proyectofinal.proyecto_finalp2.controller;

import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.Producto;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.ProductoDialog;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.Vendedor;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.EstadoProducto;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.interfaces.IVendedorCrud;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class VendedorController {
    @FXML
    private ListView<String> listViewMuro;
    @FXML
    private TextField txtMensaje;
    @FXML
    private TableView<Producto> tableProductos;
    @FXML
    private TableColumn<Producto, String> colNombreProducto;
    @FXML
    private TableColumn<Producto, String> colCategoria;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, EstadoProducto> colEstado;
    @FXML
    private ListView<Vendedor> listViewContactos;

    private Vendedor vendedor;
    private IVendedorCrud vendedorService;

    public void inicializar(IVendedorCrud vendedorService) {
        this.vendedor = vendedor;
        this.vendedorService = vendedorService;
        configurarTablaProductos();
        cargarDatos();
    }

    private void configurarTablaProductos() {
        colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void cargarDatos() {
        // Cargar productos
        tableProductos.setItems(FXCollections.observableArrayList(vendedorService.listarMisProductos()));

        // Cargar contactos
        listViewContactos.setItems(FXCollections.observableArrayList(vendedorService.listarMisContactos()));

        // Cargar mensajes del muro
        listViewMuro.setItems(FXCollections.observableArrayList(vendedorService.obtenerMuroMensajes()));
    }

    @FXML
    private void handlePublicarMensaje(ActionEvent event) {
        String mensaje = txtMensaje.getText().trim();
        if (!mensaje.isEmpty()) {
            vendedorService.agregarMensaje(mensaje);
            txtMensaje.clear();
            cargarDatos();
        }
    }

    @FXML
    private void handleNuevoProducto(ActionEvent event) {
        Dialog<Producto> dialog = new ProductoDialog(null, vendedor);
        dialog.showAndWait().ifPresent(producto -> {
            vendedorService.publicarProducto(producto);
            cargarDatos();
        });
    }

    @FXML
    private void handleEditarProducto(ActionEvent event) {
        Producto producto = tableProductos.getSelectionModel().getSelectedItem();
        if (producto != null) {
            Dialog<Producto> dialog = new ProductoDialog(producto, vendedor);
            dialog.showAndWait().ifPresent(productoActualizado -> {
                vendedorService.actualizarEstadoProducto(productoActualizado, productoActualizado.getEstado());
                cargarDatos();
            });
        } else {
            mostrarAlerta("Por favor, seleccione un producto para editar.");
        }
    }

    @FXML
    private void handleEliminarProducto(ActionEvent event) {
        Producto producto = tableProductos.getSelectionModel().getSelectedItem();
        if (producto != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setContentText("¿Está seguro de eliminar el producto " + producto.getNombre() + "?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    vendedorService.eliminarProducto(producto.getNombre());
                    cargarDatos();
                }
            });
        } else {
            mostrarAlerta("Por favor, seleccione un producto para eliminar.");
        }
    }

    @FXML
    private void handleAgregarContacto(ActionEvent event) {
        // Implementar lógica para agregar contacto
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Getters y setters si son necesarios
    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
}