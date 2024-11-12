module co.edu.uniquindio.proyectofinal.proyecto_finalp2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.proyectofinal.proyecto_finalp2 to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal.proyecto_finalp2;
}