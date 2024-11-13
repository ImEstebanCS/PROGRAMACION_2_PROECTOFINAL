package co.edu.uniquindio.proyectofinal.proyecto_finalp2;

import co.edu.uniquindio.proyectofinal.proyecto_finalp2.utils.DataUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MarketPlaceApplication extends Application {

    @Override
    public void init() throws Exception {
        super.init();
        // Inicializar los datos al inicio de la aplicación
//        DataUtil.getInstance();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/co/edu/uniquindio/proyectofinal/proyecto_finalp2/fxml/Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("MarketPlace");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error cargando FXML: " + e.getMessage());
        }
    }

//    @Override
//    public void stop() throws Exception {
//        super.stop();
//        DataUtil.getInstance().guardarDatos();
//    }

    public static void main(String[] args) {
        launch(args);
    }
}