package co.edu.uniquindio.proyectofinal.proyecto_finalp2.controller;


import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class LoginController {
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Label lblError;

    private MarketPlaceApplication marketPlace = MarketPlaceApplication.getInstance();

    @FXML
    private void handleLogin(ActionEvent event) {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarError("Por favor complete todos los campos");
            return;
        }

        if (marketPlace.autenticarUsuario(usuario, contrasena)) {
            Object usuarioObj = marketPlace.obtenerUsuario(usuario, contrasena);
            abrirVentanaPrincipal(usuarioObj);
        } else {
            mostrarError("Usuario o contraseña incorrectos");
        }
    }

    private void mostrarError(String mensaje) {
        lblError.setText(mensaje);
        lblError.setVisible(true);
    }

    private void abrirVentanaPrincipal(Object usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MarketPlaceApp.fxml"));
            Scene scene = new Scene(loader.load());

            MarketPlaceAppController controller = loader.getController();
            controller.inicializarUsuario(usuario);

            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("MarketPlace - " + (usuario instanceof Administrador ? "Administrador" : "Vendedor"));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al cargar la aplicación");
        }
    }

}