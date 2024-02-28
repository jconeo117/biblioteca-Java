package controlers;

import clases.Libro;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlerLibro {

    public static final String NOMBRE_DE_ARCHIVO = "libros.txt";
    public static final String SEPARADOR_CAMPO = ";";
    public static final String SEPARADOR_REGISTRO = "\n";

    public static void solicitarDatosParaRegistro(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el codigo del libro: ");
        String codigo = sc.nextLine();
        System.out.println("Ingrese el nombre del libro: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el autor del libro: ");
        String author = sc.nextLine();
        System.out.println("Ingrese la locacion del libro: ");
        String locacion = sc.nextLine();
        System.out.println("Ingrese la signatura del libro: ");
        String signatura = sc.nextLine();
        System.out.println("Ingrese la disponibilidad del libro [true/false]: ");
        Boolean disponible = Boolean.valueOf(sc.nextLine());
        ControlerLibro.registrar(new Libro(codigo, nombre, author, locacion, signatura, disponible));
        System.out.println("Libro registrado con exito!");
    }
    public static void registrar(Libro libro){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NOMBRE_DE_ARCHIVO, true));
            bufferedWriter.write(libro.getCodigo() +
                    SEPARADOR_CAMPO + libro.getTitulo() +
                    SEPARADOR_CAMPO + libro.getAuthor() +
                    SEPARADOR_CAMPO + libro.getLocacion() +
                    SEPARADOR_CAMPO + libro.getSignatura() +
                    SEPARADOR_CAMPO + String.valueOf(libro.isDisponible()) +
                    SEPARADOR_REGISTRO);
            bufferedWriter.close();
        }catch (IOException e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }

    public static void guardarLibros(ArrayList<Libro> libros) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NOMBRE_DE_ARCHIVO, false));
            for (int x = 0; x < libros.size(); x++) {
                Libro libro = libros.get(x);
                bufferedWriter.write(libro.getCodigo()
                        + SEPARADOR_CAMPO + libro.getTitulo()
                        + SEPARADOR_CAMPO + libro.getAuthor()
                        + SEPARADOR_CAMPO + String.valueOf(libro.isDisponible())
                        + SEPARADOR_CAMPO + libro.getLocacion()
                        + SEPARADOR_CAMPO + libro.getSignatura() + SEPARADOR_REGISTRO);

            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error escribiendo en archivo: " + e.getMessage());
        }
    }

    public static ArrayList<Libro> Obtener(){
        ArrayList<Libro> libros = new ArrayList<>();
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        try{
            fileReader = new FileReader(NOMBRE_DE_ARCHIVO);
            bufferedReader = new BufferedReader(fileReader);
            String linea;
            while ((linea = bufferedReader.readLine()) != null){
                String[] librosComoArreglo = linea.split(SEPARADOR_CAMPO);
                libros.add(new Libro(
                        librosComoArreglo[0],
                        librosComoArreglo[1],
                        librosComoArreglo[2],
                        librosComoArreglo[3],
                        librosComoArreglo[4],
                        Boolean.valueOf(librosComoArreglo[5])));
            }
        }catch (IOException e){
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }finally {
            try{
                if (bufferedReader != null){
                    bufferedReader.close();
                }
                if (fileReader != null){
                    fileReader.close();
                }
            }catch (IOException e){
                System.out.println("Cerrando excepcion: " + e.getMessage());
            }
        }
        return libros;
    }

    public static int BuscarPorCodigo(String numero, ArrayList<Libro> libros){
        for (int x =0; x<libros.size(); x++){
            Libro libro = libros.get(x);
            if(libro.getCodigo().equals(numero)){
                return x;
            }
        }
        return -1;
    }

    public static void MarcarComoPrestado(String codigoLibro){
        ArrayList<Libro> libros = ControlerLibro.Obtener();
        int indice = ControlerLibro.BuscarPorCodigo(codigoLibro, libros);
        if (indice == -1){
            return;
        }
        libros.get(indice).setDisponible(false);
        ControlerLibro.guardarLibros(libros);
    }

    public static void CambiarSignatura(String codigoLibro, String nuevaSignatura, String nuevaLocacion){
        ArrayList<Libro> libros = ControlerLibro.Obtener();
        int indice = ControlerLibro.BuscarPorCodigo(codigoLibro, libros);
        if (indice == -1){
            return;
        }
        libros.get(indice).setSignatura(nuevaSignatura);
        libros.get(indice).setLocacion(nuevaLocacion);
        ControlerLibro.guardarLibros(libros);
    }

    public static void PedirDatosParaCambiarSignatura(){
        Scanner sc = new Scanner(System.in);
        Libro libro = ControlerLibro.imprimirLibrosYPedirSeleccion();
        if (!libro.isDisponible()){
            System.out.println("No puedes cambiar la signatura de un libro que no esta disponible");
            return;
        }
        System.out.println("Ingresa la nueva signatura para el libro");
        String nuevaSignatura = sc.nextLine();
        System.out.println("Ingresa la nueva locacion para el libro");
        String nuevaLocacion = sc.nextLine();
        ControlerLibro.CambiarSignatura(libro.getCodigo(), nuevaSignatura, nuevaLocacion);
        System.out.println("La signatura ha sido cambiada con exito");

    }
    public static void imprimirLibros(ArrayList<Libro> libros) {
        System.out.println(
                "+-----+----------+----------------------------------------+--------------------+----------+------------------------------+------------------------------+");
        System.out.printf("|%-5s|%-10s|%-40s|%-20s|%-10s|%-30s|%-30s|\n", "No", "Codigo", "Titulo", "Autor",
                "Disponible",
                "Localizacion", "Signatura");
        System.out.println(
                "+-----+----------+----------------------------------------+--------------------+----------+------------------------------+------------------------------+");

        for (int x = 0; x < libros.size(); x++) {
            Libro libro = libros.get(x);
            System.out.printf("|%-5d|%-10s|%-40s|%-20s|%-10s|%-30s|%-30s|\n", x + 1, libro.getCodigo(),
                    libro.getTitulo(),
                    libro.getAuthor(), libro.isDisponible() ? "Si" : "No", libro.getLocacion(),
                    libro.getSignatura());
            System.out.println(
                    "+-----+----------+----------------------------------------+--------------------+----------+------------------------------+------------------------------+");
        }
    }

    public static Libro imprimirLibrosYPedirSeleccion() {
        ArrayList<Libro> libros = ControlerLibro.Obtener();
        Scanner sc = new Scanner(System.in);
        while (true) {
            ControlerLibro.imprimirLibros(libros);
            System.out.println("Ingrese el codigo del libro: ");
            String codigo = sc.nextLine();
            int indice = ControlerLibro.BuscarPorCodigo(codigo, libros);
            if (indice == -1) {
                System.out.println("No existe libro con ese codigo");
            } else {
                Libro libro = libros.get(indice);
                if (libro.isDisponible()) {
                    return libro;
                } else {
                    System.out.println("El libro esta ocupado");
                }
            }
        }
    }

}
