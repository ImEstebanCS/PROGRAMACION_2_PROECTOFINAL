package co.edu.uniquindio.proyectofinal.proyecto_finalp2.controller;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.*;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.factory.ServiceFactory;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.interfaces.IAdministradorCrud;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import java.io.IOException;

import javafx.scene.control.*;
import java.io.IOException;

public class MarketPlaceAppController {
    @FXML
    private TabPane tabPane;

    private Object usuarioActual;
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    public void inicializarUsuario(Object usuario) {
        this.usuarioActual = usuario;
        cargarPestanas();
    }

    private void cargarPestanas() {
        if (usuarioActual instanceof Administrador) {
            cargarVistaAdministrador();
        } else if (usuarioActual instanceof Vendedor) {
            cargarVistaVendedor((Vendedor) usuarioActual);
        }
    }

    private void cargarVistaAdministrador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyectofinal/proyecto_finalp2/fxml/Administrador.fxml"));
            Tab tab = new Tab("Panel de Control");
            tab.setContent(loader.load());

            AdministradorController controller = loader.getController();
            IAdministradorCrud adminService = ServiceFactory.getInstance().createAdministradorCrud();

            tabPane.getTabs().add(tab);
        } catch (IOException e) {
            e.printStackTrace();
            mostrarError("Error al cargar vista de administrador");
        }
    }

    private void cargarVistaVendedor(Vendedor vendedor) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyectofinal/proyecto_finalp2/fxml/Vendedor.fxml"));
            Tab tab = new Tab(vendedor.getNombre());
            tab.setContent(loader.load());

            VendedorController controller = loader.getController();
            controller.inicializar(serviceFactory.createVendedorCrud(vendedor));

            tabPane.getTabs().add(tab);
        } catch (IOException e) {
            e.printStackTrace();
            mostrarError("Error al cargar vista de vendedor");
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void handleCerrarSesion(ActionEvent actionEvent) {
    }

    public void handleSalir(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void handleExportarEstadisticas(ActionEvent actionEvent) {
    }
}
