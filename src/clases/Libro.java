package clases;

public class Libro {

    private String codigo, titulo, author, locacion, signatura;
    private boolean disponible;

    public Libro(String codigo, String titulo, String author, String locacion, String signatura, Boolean disponible){
        this.codigo = codigo;
        this.titulo = titulo;
        this.author = author;
        this.locacion = locacion;
        this.signatura = signatura;
        this.disponible = disponible;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

    public String getSignatura() {
        return signatura;
    }

    public void setSignatura(String signatura) {
        this.signatura = signatura;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "clases.Libro{" + "codigo=" + codigo + ", titulo=" + titulo + ", autor=" + author + ", localizacion="
                + locacion + ", signatura=" + signatura + ", disponible=" + disponible + '}';
    }
}
