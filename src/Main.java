import controlers.ControlerLibro;
import controlers.ControlerPrestamo;
import controlers.ControlerSocio;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String eleccion = "";
        while (!eleccion.equals("9")){
            System.out.println(
                    "1. Registrar socio\n" +
                    "2. Registrar libro\n" +
                    "3. Registrar prestamo\n" +
                    "4. Ver socios\n" +
                    "5. Ver libros\n" +
                    "6. Ver prestamos\n" +
                    "7. Ver socios no fiables\n" +
                    "8. Cambiar localizacion de libro\n" +
                    "9. Salir\n" +
                    "Elige:");
            eleccion = sc.nextLine();
            if (eleccion.equals("1")){
                ControlerSocio.SolicitarDatosRegistroSocio();
            }
            if (eleccion.equals("2")){
                ControlerLibro.solicitarDatosParaRegistro();
            }
            if (eleccion.equals("3")){
                ControlerPrestamo.PedirDatosParaPrestamo();
            }
            if (eleccion.equals("4")){
                ControlerSocio.imprimirSocios(ControlerSocio.Obtener());
            }
            if (eleccion.equals("5")){
                ControlerLibro.imprimirLibros(ControlerLibro.Obtener());
            }
            if (eleccion.equals("6")){
                ControlerPrestamo.imprimirPrestamos(ControlerPrestamo.Obtener());
            }
            if (eleccion.equals("7")){
                ControlerSocio.imprimirSociosNoFiables(ControlerSocio.Obtener());
            }
            if (eleccion.equals("8")){
                ControlerLibro.PedirDatosParaCambiarSignatura();
            }
        }

    }
}