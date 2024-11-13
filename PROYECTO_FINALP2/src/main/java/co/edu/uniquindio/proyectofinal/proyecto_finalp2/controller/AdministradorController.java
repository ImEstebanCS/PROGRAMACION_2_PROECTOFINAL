package co.edu.uniquindio.proyectofinal.proyecto_finalp2.controller;

import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.Vendedor;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.VendedorDialog;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.EstadoProducto;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.interfaces.IAdministradorCrud;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.event.ActionEvent;



public class AdministradorController {
    @FXML private TableView<Vendedor> tableVendedores;
    @FXML private TableColumn<Vendedor, String> colCedula;
    @FXML private TableColumn<Vendedor, String> colNombre;
    @FXML private TableColumn<Vendedor, String> colApellidos;
    @FXML private TableColumn<Vendedor, String> colUsuario;

    @FXML private Label lblTotalVendedores;
    @FXML private Label lblTotalProductos;
    @FXML private Label lblProductosVendidos;

    @FXML private ComboBox<String> comboTipoEstadistica;

    private IAdministradorCrud adminService;

    public void inicializar(IAdministradorCrud adminService) {
        this.adminService = adminService;
        configurarTabla();
        configurarComboBox();
        cargarDatos();
        actualizarEstadisticas();
    }

    private void configurarTabla() {
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
    }

    private void configurarComboBox() {
        comboTipoEstadistica.setItems(FXCollections.observableArrayList(
                "Vendedores Activos",
                "Productos",
                "Interacciones"
        ));
    }

    private void cargarDatos() {
        tableVendedores.setItems(FXCollections.observableArrayList(adminService.listarVendedores()));
    }

    private void actualizarEstadisticas() {
        lblTotalVendedores.setText(String.valueOf(adminService.listarVendedores().size()));
        int totalProductos = adminService.listarVendedores().stream()
                .mapToInt(v -> v.getProductos().size())
                .sum();
        lblTotalProductos.setText(String.valueOf(totalProductos));

        long productosVendidos = adminService.listarVendedores().stream()
                .flatMap(v -> v.getProductos().stream())
                .filter(p -> p.getEstado() == EstadoProducto.VENDIDO)
                .count();
        lblProductosVendidos.setText(String.valueOf(productosVendidos));
    }

    @FXML
    private void handleNuevoVendedor(ActionEvent event) {
        Dialog<Vendedor> dialog = new VendedorDialog(null);
        dialog.showAndWait().ifPresent(vendedor -> {
            adminService.agregarVendedor(vendedor);
            cargarDatos();
            actualizarEstadisticas();
        });
    }

    @FXML
    private void handleEditarVendedor(ActionEvent event) {
        Vendedor vendedor = tableVendedores.getSelectionModel().getSelectedItem();
        if (vendedor != null) {
            Dialog<Vendedor> dialog = new VendedorDialog(vendedor);
            dialog.showAndWait().ifPresent(vendedorActualizado -> {
                adminService.actualizarVendedor(vendedorActualizado);
                cargarDatos();
            });
        } else {
            mostrarAlerta("Por favor, seleccione un vendedor para editar.");
        }
    }

    @FXML
    private void handleEliminarVendedor(ActionEvent event) {
        Vendedor vendedor = tableVendedores.getSelectionModel().getSelectedItem();
        if (vendedor != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setContentText("¿Está seguro de eliminar al vendedor " +
                    vendedor.getNombre() + " " + vendedor.getApellidos() + "?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    adminService.eliminarVendedor(vendedor.getCedula());
                    cargarDatos();
                    actualizarEstadisticas();
                }
            });
        } else {
            mostrarAlerta("Por favor, seleccione un vendedor para eliminar.");
        }
    }

    @FXML
    private void handleGenerarReporte(ActionEvent event) {
        String tipoReporte = comboTipoEstadistica.getValue();
        if (tipoReporte == null) {
            mostrarAlerta("Por favor, seleccione un tipo de reporte.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt")
        );

        File file = fileChooser.showSaveDialog(tableVendedores.getScene().getWindow());
        if (file != null) {
            adminService.exportarEstadisticas(file.getAbsolutePath(),
                    tipoReporte.replace(" ", "_").toUpperCase());
            mostrarInformacion("Reporte generado exitosamente");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
