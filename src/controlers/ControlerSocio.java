package controlers;

import clases.Socio;
import clases.Prestamo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlerSocio {

    public static final String NOMBRE_DE_ARCHIVO = "socio.txt";
    public static final String SEPARADOR_CAMPO = ";";
    public static final String SEPARADOR_REGISTRO = "\n";

    public static void SolicitarDatosRegistroSocio(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese numero de Socio: ");
        String numero = sc.nextLine();
        System.out.println("Ingrese nombre de Socio: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese direccion de Socio: ");
        String direccion = sc.nextLine();
        ControlerSocio.Registrar(new Socio(numero, nombre,direccion));
        System.out.println("Socio registrado con exito");
    }

    public static void Registrar(Socio socio){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NOMBRE_DE_ARCHIVO, true));
            bufferedWriter.write(socio.getNumero() + SEPARADOR_CAMPO + socio.getNombre()
                    + SEPARADOR_CAMPO + socio.getDireccion() + SEPARADOR_REGISTRO);
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println("Ha ocurrido un error en el registro: " + e.getMessage());
        }
    }

    public static ArrayList<Socio> Obtener(){
        ArrayList<Socio> socios = new ArrayList<>();
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        try{
            fileReader = new FileReader(NOMBRE_DE_ARCHIVO);
            bufferedReader = new BufferedReader(fileReader);
            String Linea;

            while ((Linea = bufferedReader.readLine()) != null){
                String[] socioComoArreglo = Linea.split(SEPARADOR_CAMPO);
                socios.add(new Socio(socioComoArreglo[0], socioComoArreglo[1], socioComoArreglo[2]));
            }
        }catch (IOException e){
            System.out.println("Ha ocurrido un error leyendo el archivo: " + e.getMessage());
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
        return socios;
    }

    public static void imprimirSocios(ArrayList<Socio> socios) {
        ArrayList<Prestamo> prestamos = ControlerPrestamo.Obtener();
        System.out.println(
                "+-----+----------+----------------------------------------+----------------------------------------+--------------------+");
        System.out.printf("|%-5s|%-10s|%-40s|%-40s|%-20s|\n", "#", "No. socio", "Nombre", "Direccion",
                "Libros prestados");
        System.out.println(
                "+-----+----------+----------------------------------------+----------------------------------------+--------------------+");
        for (int x = 0; x < socios.size(); x++) {
            Socio socio = socios.get(x);
            System.out.printf("|%-5s|%-10s|%-40s|%-40s|%-20s|\n", x + 1, socio.getNumero(), socio.getNombre(),
                    socio.getDireccion(), ControlerPrestamo.NumeroDeLibrosPrestados(socio.getNumero(), prestamos));
            System.out.println(
                    "+-----+----------+----------------------------------------+----------------------------------------+--------------------+");
        }
    }

    public static void imprimirSociosNoFiables(ArrayList<Socio> socios) {
        ArrayList<Prestamo> prestamos = ControlerPrestamo.Obtener();
        System.out.println(
                "+-----+----------+----------------------------------------+----------------------------------------+--------------------+");
        System.out.printf("|%-5s|%-10s|%-40s|%-40s|%-20s|\n", "#", "No. socio", "Nombre", "Direccion",
                "Libros prestados");
        System.out.println(
                "+-----+----------+----------------------------------------+----------------------------------------+--------------------+");
        for (int x = 0; x < socios.size(); x++) {
            Socio socio = socios.get(x);
            int librosPrestados = ControlerPrestamo.NumeroDeLibrosPrestados(socio.getNumero(), prestamos);
            if (librosPrestados < 10) {
                continue;
            }
            System.out.printf("|%-5s|%-10s|%-40s|%-40s|%-20s|\n", x + 1, socio.getNumero(), socio.getNombre(),
                    socio.getDireccion(), librosPrestados);
            System.out.println(
                    "+-----+----------+----------------------------------------+----------------------------------------+--------------------+");
        }
    }

    public static int BuscarSocioPorNumero(String numero, ArrayList<Socio> socios){
        for (int x = 0; x<socios.size(); x++){
            Socio socio = socios.get(x);
            if (socio.getNumero().equals(numero)){
                return x;
            }
        }
        return -1;
    }

    public static Socio imprimirSociosYPedirSeleccion(){
        ArrayList<Socio> socios = ControlerSocio.Obtener();
        Scanner sc = new Scanner(System.in);
        while (true){
            ControlerSocio.imprimirSocios(socios);
            System.out.println("Ingrese el numero del socio a seleccionar: ");
            String numero = sc.nextLine();
            int indice = ControlerSocio.BuscarSocioPorNumero(numero, socios);
            if (indice == -1){
                System.out.println("El socio seleccionado no existe.");
            }else {
                return socios.get(indice);
            }
        }
    }

}
