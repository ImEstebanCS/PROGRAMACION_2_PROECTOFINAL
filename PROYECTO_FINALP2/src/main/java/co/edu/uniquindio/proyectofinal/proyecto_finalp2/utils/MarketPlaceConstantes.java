package co.edu.uniquindio.proyectofinal.proyecto_finalp2.utils;

public class MarketPlaceConstantes {
    public static final String TITULO_APP = "MarketPlace Social";
    public static final String VERSION = "1.0.0";
    public static final int MAX_CONTACTOS = 10;

    // Rutas de archivos FXML
    public static final String VISTA_LOGIN = "/fxml/Login.fxml";
    public static final String VISTA_PRINCIPAL = "/fxml/MarketPlaceApp.fxml";
    public static final String VISTA_ADMINISTRADOR = "/fxml/Administrador.fxml";
    public static final String VISTA_VENDEDOR = "/fxml/Vendedor.fxml";

    // Mensajes de error
    public static final String ERROR_LOGIN = "Usuario o contraseña incorrectos";
    public static final String ERROR_CAMPOS_VACIOS = "Todos los campos son obligatorios";
    public static final String ERROR_MAX_CONTACTOS = "Has alcanzado el límite de contactos permitidos";
    public static final String ERROR_PRODUCTO_INVALIDO = "Los datos del producto son inválidos";
    public static final String ERROR_PERMISO_DENEGADO = "No tienes permiso para realizar esta acción";

    // Mensajes de éxito
    public static final String EXITO_GUARDAR = "Datos guardados exitosamente";
    public static final String EXITO_ELIMINAR = "Elemento eliminado exitosamente";
    public static final String EXITO_ACTUALIZAR = "Datos actualizados exitosamente";
    public static final String EXITO_EXPORTAR = "Reporte exportado exitosamente";

    // Tipos de reportes
    public static final String REPORTE_VENDEDORES = "VENDEDORES";
    public static final String REPORTE_PRODUCTOS = "PRODUCTOS";
    public static final String REPORTE_INTERACCIONES = "INTERACCIONES";


    public static final String ESTILO_PRINCIPAL = "/css/styles.css";
    public static final String ESTILO_LOGIN = "/css/login.css";
}
