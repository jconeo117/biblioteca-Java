package controlers;

import clases.Libro;
import clases.Prestamo;
import clases.Socio;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

public class ControlerPrestamo {

    public static final String NOMBRE_ARCHIVO = "prestamos.txt";
    public static final String SEPARADOR_CAMPO = ";";
    public static final String SEPARADOR_REGISTRO = "\n";


    public static void Registrar(Prestamo prestamo){
        ControlerLibro.MarcarComoPrestado(prestamo.getCodigoLibro());
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true));
            bufferedWriter.write(prestamo.getCodigoLibro() +
                    SEPARADOR_CAMPO + prestamo.getNumeroSocio() +
                    SEPARADOR_CAMPO + prestamo.getFechaFormateada() +
                    SEPARADOR_REGISTRO);
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }

    public static ArrayList<Prestamo> Obtener(){
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        try{
            fileReader = new FileReader(NOMBRE_ARCHIVO);
            bufferedReader = new BufferedReader(fileReader);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] prestamoComoArreglo = linea.split(SEPARADOR_CAMPO);
                prestamos.add(new Prestamo(prestamoComoArreglo[0], prestamoComoArreglo[1],
                        LocalDateTime.parse(prestamoComoArreglo[2],
                                new DateTimeFormatterBuilder().parseCaseInsensitive()
                                        .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toFormatter())));
            }
        }catch (IOException e){
            System.out.println("Ha ocurrido un error: "+e.getMessage());
        }finally {
            try{
                if (bufferedReader != null){
                    bufferedReader.close();
                }
                if (fileReader != null){
                    fileReader.close();
                }
            }catch (IOException e){
                System.out.println("Excepcion cerrando: "+e.getMessage());
            }
        }
        return prestamos;
    }

    public static int NumeroDeLibrosPrestados(String numeroSocio, ArrayList<Prestamo> prestamos){
        int cantidad = 0;
        for (int x =0; x<prestamos.size(); x++){
            Prestamo prestamo = prestamos.get(x);
            if (prestamo.getNumeroSocio().equals(numeroSocio)){
                cantidad++;
            }
        }
        return cantidad;
    }

    public static void imprimirPrestamos(ArrayList<Prestamo> prestamos) {
        System.out
                .println("+-----+------------------------------+------------------------------+--------------------+");
        System.out.printf("|%-5s|%-30s|%-30s|%-20s|\n", "No", "Codigo libro", "No. Socio", "Fecha");
        System.out
                .println("+-----+------------------------------+------------------------------+--------------------+");

        for (int x = 0; x < prestamos.size(); x++) {
            Prestamo prestamo = prestamos.get(x);
            System.out.printf("|%-5d|%-30s|%-30s|%-20s|\n", x, prestamo.getCodigoLibro(), prestamo.getNumeroSocio(),
                    prestamo.getFechaFormateada());
            System.out.println(
                    "+-----+------------------------------+------------------------------+--------------------+");
        }
    }

    public static void PedirDatosParaPrestamo(){
        Libro libro = ControlerLibro.imprimirLibrosYPedirSeleccion();
        Socio socio = ControlerSocio.imprimirSociosYPedirSeleccion();
        ControlerPrestamo.Registrar(new Prestamo(socio.getNumero(), libro.getCodigo(), LocalDateTime.now()));
        System.out.println("Prestamo registrado con exito");
    }
}
