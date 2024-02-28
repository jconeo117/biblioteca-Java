package clases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Prestamo {

    private String numeroSocio, codigoLibro;
    private LocalDateTime fecha;

    public Prestamo(String numeroSocio, String codigoLibro, LocalDateTime fecha){
        this.numeroSocio = numeroSocio;
        this.codigoLibro = codigoLibro;
        this.fecha = fecha;
    }

    public String getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(String numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getFechaFormateada() {
        String formato = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        return formateador.format(this.fecha);
    }

    @Override
    public String toString() {
        return "Prestamo{" + "codigoLibro=" + codigoLibro + ", numeroSocio=" + numeroSocio + ", fecha="
                + this.getFechaFormateada() + '}';
    }

}
