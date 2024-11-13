package co.edu.uniquindio.proyectofinal.proyecto_finalp2.mapping.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class MeGustaDto {
    private String vendedorCedula;
    private LocalDateTime fecha;
    private String productoNombre;

    public MeGustaDto() {
        this.fecha = LocalDateTime.now();
    }

    public MeGustaDto(String vendedorCedula, String productoNombre) {
        this();
        this.vendedorCedula = vendedorCedula;
        this.productoNombre = productoNombre;
    }

    public String getVendedorCedula() {
        return vendedorCedula;
    }

    public void setVendedorCedula(String vendedorCedula) {
        this.vendedorCedula = vendedorCedula;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public boolean esValido() {
        return vendedorCedula != null && !vendedorCedula.trim().isEmpty() &&
                productoNombre != null && !productoNombre.trim().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeGustaDto that = (MeGustaDto) o;
        return Objects.equals(vendedorCedula, that.vendedorCedula) &&
                Objects.equals(productoNombre, that.productoNombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendedorCedula, productoNombre);
    }

    @Override
    public String toString() {
        return "MeGustaDto{" +
                "vendedorCedula='" + vendedorCedula + '\'' +
                ", fecha=" + fecha +
                ", productoNombre='" + productoNombre + '\'' +
                '}';
    }
}
