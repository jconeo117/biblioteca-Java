package clases;

public class Socio {

    private String numero, nombre, direccion;

    public Socio(String numero, String nombre, String direccion){
        this.numero = numero;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "clases.Socio{" + "numero=" + numero + ", nombre=" + nombre + ", direccion=" + direccion + '}';
    }
}
