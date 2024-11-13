package co.edu.uniquindio.proyectofinal.proyecto_finalp2.model;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.EstadoProducto;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import java.io.File;

public class ProductoDialog extends Dialog<Producto> {
    private TextField txtNombre;
    private TextField txtCategoria;
    private TextField txtPrecio;
    private ComboBox<EstadoProducto> comboEstado;
    private TextField txtImagen;
    private Button btnSeleccionarImagen;
    private final Vendedor vendedor;

    public ProductoDialog(Producto producto, Vendedor vendedor) {
        this.vendedor = vendedor;
        setTitle(producto == null ? "Nuevo Producto" : "Editar Producto");

        // Configurar botones
        ButtonType guardarButtonType = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(guardarButtonType, ButtonType.CANCEL);

        // Crear el grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // Crear campos
        txtNombre = new TextField();
        txtCategoria = new TextField();
        txtPrecio = new TextField();
        comboEstado = new ComboBox<>();
        txtImagen = new TextField();
        btnSeleccionarImagen = new Button("Seleccionar");

        // Configurar comboBox
        comboEstado.getItems().addAll(EstadoProducto.values());
        comboEstado.setValue(EstadoProducto.PUBLICADO);

        // Agregar campos al grid
        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(txtNombre, 1, 0);
        grid.add(new Label("Categoría:"), 0, 1);
        grid.add(txtCategoria, 1, 1);
        grid.add(new Label("Precio:"), 0, 2);
        grid.add(txtPrecio, 1, 2);
        grid.add(new Label("Estado:"), 0, 3);
        grid.add(comboEstado, 1, 3);
        grid.add(new Label("Imagen:"), 0, 4);
        grid.add(txtImagen, 1, 4);
        grid.add(btnSeleccionarImagen, 2, 4);

        // Si es edición, cargar datos
        if (producto != null) {
            txtNombre.setText(producto.getNombre());
            txtCategoria.setText(producto.getCategoria());
            txtPrecio.setText(String.valueOf(producto.getPrecio()));
            comboEstado.setValue(producto.getEstado());
            txtImagen.setText(producto.getImagen());
        }

        // Configurar selector de imagen
        btnSeleccionarImagen.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar Imagen");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
            );
            File file = fileChooser.showOpenDialog(getDialogPane().getScene().getWindow());
            if (file != null) {
                txtImagen.setText(file.getAbsolutePath());
            }
        });

        getDialogPane().setContent(grid);

        // Convertir el resultado
        setResultConverter((dialogButton) -> {
            if (dialogButton == guardarButtonType) {
                try {
                    if (validarCampos()) {
                        Producto nuevoProducto = producto == null ? new Producto() : producto;
                        nuevoProducto.setNombre(txtNombre.getText().trim());
                        nuevoProducto.setCategoria(txtCategoria.getText().trim());
                        nuevoProducto.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
                        nuevoProducto.setEstado(comboEstado.getValue());
                        nuevoProducto.setImagen(txtImagen.getText().trim());
                        nuevoProducto.setVendedor(vendedor);
                        return nuevoProducto;
                    }
                } catch (NumberFormatException ex) {
                    mostrarError("El precio debe ser un número válido");
                }
            }
            return null;
        });
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() ||
                txtCategoria.getText().trim().isEmpty() ||
                txtPrecio.getText().trim().isEmpty() ||
                comboEstado.getValue() == null) {

            mostrarError("Todos los campos son obligatorios excepto la imagen");
            return false;
        }
        return true;
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de validación");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }}