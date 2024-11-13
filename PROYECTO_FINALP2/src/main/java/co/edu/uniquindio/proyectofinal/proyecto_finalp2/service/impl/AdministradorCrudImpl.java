package co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.impl;

import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.MarketPlaceApplication;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.model.Vendedor;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.EstadoProducto;
import co.edu.uniquindio.proyectofinal.proyecto_finalp2.service.interfaces.IAdministradorCrud;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdministradorCrudImpl implements IAdministradorCrud {
    private final MarketPlaceApplication marketPlace = MarketPlaceApplication.getInstance();

    @Override
    public void agregarVendedor(Vendedor vendedor) {
        marketPlace.getVendedores().add(vendedor);
    }

    @Override
    public void eliminarVendedor(String cedula) {
        marketPlace.getVendedores().removeIf(v -> v.getCedula().equals(cedula));
    }

    @Override
    public void actualizarVendedor(Vendedor vendedor) {
        Vendedor vendedorExistente = buscarVendedor(vendedor.getCedula());
        if (vendedorExistente != null) {
            int index = marketPlace.getVendedores().indexOf(vendedorExistente);
            marketPlace.getVendedores().set(index, vendedor);
        }
    }

    @Override
    public Vendedor buscarVendedor(String cedula) {
        return marketPlace.getVendedores().stream()
                .filter(v -> v.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Vendedor> listarVendedores() {
        return marketPlace.getVendedores();
    }

    @Override
    public void exportarEstadisticas(String ruta, String tipoReporte) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ruta))) {
            writer.println("Reporte de " + tipoReporte);
            writer.println("Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            writer.println("Usuario: Administrador");
            writer.println("\nInformación del reporte:");

            switch (tipoReporte) {
                case "VENDEDORES" -> writer.println(generarReporteVendedores());
                case "PRODUCTOS" -> writer.println(generarReporteProductos());
                case "INTERACCIONES" -> writer.println(generarReporteInteracciones());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String generarReporteVendedores() {
        StringBuilder report = new StringBuilder();
        report.append("\nListado de Vendedores Activos:\n");

        marketPlace.getVendedores().forEach(v -> {
            report.append(String.format("- %s %s (CC: %s)\n", v.getNombre(), v.getApellidos(), v.getCedula()));
            report.append(String.format("  Productos: %d\n", v.getProductos().size()));
            report.append(String.format("  Contactos: %d\n", v.getContactos().size()));
        });

        return report.toString();
    }

    @Override
    public String generarReporteProductos() {
        StringBuilder report = new StringBuilder();
        report.append("\nEstadísticas de Productos:\n");

        // Total por estado
        long productosPublicados = marketPlace.getProductos().stream()
                .filter(p -> p.getEstado() == EstadoProducto.PUBLICADO).count();
        long productosVendidos = marketPlace.getProductos().stream()
                .filter(p -> p.getEstado() == EstadoProducto.VENDIDO).count();

        report.append(String.format("Productos Publicados: %d\n", productosPublicados));
        report.append(String.format("Productos Vendidos: %d\n", productosVendidos));

        // Top productos por me gusta
        report.append("\nTop Productos por Me Gusta:\n");
        marketPlace.getProductos().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getMeGusta().size(), p1.getMeGusta().size()))
                .limit(5)
                .forEach(p -> report.append(String.format("- %s (%d me gusta)\n",
                        p.getNombre(), p.getMeGusta().size())));

        return report.toString();
    }

    @Override
    public String generarReporteInteracciones() {
        StringBuilder report = new StringBuilder();
        report.append("\nEstadísticas de Interacciones:\n");

        // Total comentarios y me gusta
        long totalComentarios = marketPlace.getProductos().stream()
                .mapToLong(p -> p.getComentarios().size())
                .sum();
        long totalMeGusta = marketPlace.getProductos().stream()
                .mapToLong(p -> p.getMeGusta().size())
                .sum();

        report.append(String.format("Total Comentarios: %d\n", totalComentarios));
        report.append(String.format("Total Me Gusta: %d\n", totalMeGusta));

        // Vendedores más activos
        report.append("\nVendedores Más Activos:\n");
        marketPlace.getVendedores().stream()
                .sorted((v1, v2) -> Integer.compare(
                        v2.getComentarios().size() + v2.getMuroMensajes().size(),
                        v1.getComentarios().size() + v1.getMuroMensajes().size()))
                .limit(5)
                .forEach(v -> report.append(String.format("- %s %s (%d interacciones)\n",
                        v.getNombre(), v.getApellidos(),
                        v.getComentarios().size() + v.getMuroMensajes().size())));

        return report.toString();
    }

}
