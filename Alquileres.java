package com.mycompany.alquiler;
/**
En un puerto se alquilan amarres para barcos de distinto tipo. Para cada
alquiler se guarda el nombre y cierta cantidad de clientes, las fechas tanto
inicial como final de alquiler, la posición del amarre y el barco que lo ocupará.
Un barco se caracteriza por su matrícula, su eslora en metros, así como su año
de fabricación. Un alquiler se calcula multiplicando el número de días de
ocupación, cada día cuesta 25 mil pesos más los impuestos. Se desea un
sistema que tome los datos del cliente y del barco para calcular cuánto pagar.
* Se debe imprimir el recibo con todos los datos tanto del barco como del cliente.
Tener en cuenta que un cliente puede tener alquilados varios amarres.
 */
import java.time.LocalDate;
import java.util.Scanner;

public class Alquileres {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Datos del barco
        System.out.println("Ingrese los datos del barco:");
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Eslora (en metros): ");
        double eslora = scanner.nextDouble();
        System.out.print("Año de fabricación: ");
        int añoFabricacion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        Barco barco = new Barco(matricula, eslora, añoFabricacion);

        // Datos del cliente y alquiler
        System.out.println("Ingrese los datos del alquiler:");
        System.out.print("Nombre del cliente: ");
        String nombreCliente = scanner.nextLine();
        System.out.print("Cantidad de clientes: ");
        int cantidadClientes = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        System.out.print("Fecha de inicio del alquiler (YYYY-MM-DD): ");
        String fechaInicioString = scanner.nextLine();
        LocalDate fechaInicio = LocalDate.parse(fechaInicioString);

        System.out.print("Fecha de fin del alquiler (YYYY-MM-DD): ");
        String fechaFinString = scanner.nextLine();
        LocalDate fechaFin = LocalDate.parse(fechaFinString);

        System.out.print("Posicion del amarre: ");
        String posicionAmarre = scanner.nextLine();

        // Crear el alquiler
        Alquiler alquiler = new Alquiler(nombreCliente, cantidadClientes, fechaInicio, fechaFin, posicionAmarre, barco);

        // Imprimir el recibo
        alquiler.imprimirRecibo();
    }
}
