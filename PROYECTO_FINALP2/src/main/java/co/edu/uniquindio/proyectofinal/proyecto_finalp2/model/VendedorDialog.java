package co.edu.uniquindio.proyectofinal.proyecto_finalp2.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class VendedorDialog extends Dialog<Vendedor> {
    private TextField txtNombre;
    private TextField txtApellidos;
    private TextField txtCedula;
    private TextField txtDireccion;
    private TextField txtUsuario;
    private PasswordField txtContrasena;

    public VendedorDialog(Vendedor vendedor) {
        setTitle(vendedor == null ? "Nuevo Vendedor" : "Editar Vendedor");

        // Botones
        ButtonType btnGuardar = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(btnGuardar, ButtonType.CANCEL);

        // Grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // Campos
        txtNombre = new TextField();
        txtApellidos = new TextField();
        txtCedula = new TextField();
        txtDireccion = new TextField();
        txtUsuario = new TextField();
        txtContrasena = new PasswordField();

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(txtNombre, 1, 0);
        grid.add(new Label("Apellidos:"), 0, 1);
        grid.add(txtApellidos, 1, 1);
        grid.add(new Label("Cédula:"), 0, 2);
        grid.add(txtCedula, 1, 2);
        grid.add(new Label("Dirección:"), 0, 3);
        grid.add(txtDireccion, 1, 3);
        grid.add(new Label("Usuario:"), 0, 4);
        grid.add(txtUsuario, 1, 4);
        grid.add(new Label("Contraseña:"), 0, 5);
        grid.add(txtContrasena, 1, 5);

        // Si es edición, cargar datos
        if (vendedor != null) {
            txtNombre.setText(vendedor.getNombre());
            txtApellidos.setText(vendedor.getApellidos());
            txtCedula.setText(vendedor.getCedula());
            txtDireccion.setText(vendedor.getDireccion());
            txtUsuario.setText(vendedor.getUsuario());
            txtContrasena.setText(vendedor.getContrasena());
            txtCedula.setDisable(true); // No permitir editar la cédula
        }

        getDialogPane().setContent(grid);

        setResultConverter(dialogButton -> {
            if (dialogButton == btnGuardar) {
                if (validarCampos()) {
                    Vendedor nuevoVendedor = vendedor == null ? new Vendedor() : vendedor;
                    nuevoVendedor.setNombre(txtNombre.getText().trim());
                    nuevoVendedor.setApellidos(txtApellidos.getText().trim());
                    nuevoVendedor.setCedula(txtCedula.getText().trim());
                    nuevoVendedor.setDireccion(txtDireccion.getText().trim());
                    nuevoVendedor.setUsuario(txtUsuario.getText().trim());
                    nuevoVendedor.setContrasena(txtContrasena.getText());
                    return nuevoVendedor;
                }
            }
            return null;
        });
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() ||
                txtApellidos.getText().trim().isEmpty() ||
                txtCedula.getText().trim().isEmpty() ||
                txtDireccion.getText().trim().isEmpty() ||
                txtUsuario.getText().trim().isEmpty() ||
                txtContrasena.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validación");
            alert.setContentText("Todos los campos son obligatorios");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
