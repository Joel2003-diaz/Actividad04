package com.mycompany.matricula;

/**
Se desea un programa que simule la matrícula de los estudiantes:
● Se debe mostrar un menú con un mínimo de 3 carreras.
● Se deben pedir todos los datos del estudiante (nombres, apellidos,
documento, dirección, teléfono).
● Si el estudiante realiza su curso en línea (sin materias aplazadas), solo se
cobra el monto por la matrícula de 1500 dólares, precio base; a partir de
quinto semestre hay un aumento de 5%.
● Si el estudiante no realiza su curso en línea (materias aplazadas), se debe
cobrar el total del semestre y adicional las materias perdidas.
● Las materias se cobran por créditos, cada crédito cuesta 20 dólares, si es
para primero, segundo y tercer semestre; si es para cuarto, quinto y sexto
cada crédito cuesta 25 dólares; y para el resto, cada crédito cuesta 30
dólares.
● Se debe imprimir el recibo de inscripción donde se detalle la transacción.
 */
import java.util.Scanner;

public class Matricula {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menú de carreras
        System.out.println("Bienvenido al sistema de matrícula de estudiantes");
        System.out.println("Seleccione una carrera:");
        System.out.println("1. Ingeniería en Sistemas");
        System.out.println("2. Medicina");
        System.out.println("3. Derecho");
        int carrera = scanner.nextInt();

        // Datos del estudiante
        scanner.nextLine();  // Limpiar el buffer
        System.out.print("Ingrese los nombres del estudiante: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese los apellidos del estudiante: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese el documento del estudiante: ");
        String documento = scanner.nextLine();
        System.out.print("Ingrese la dirección del estudiante: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese el teléfono del estudiante: ");
        String telefono = scanner.nextLine();
        System.out.print("¿El estudiante realiza el curso en línea? (si/no): ");
        String cursoEnLinea = scanner.nextLine().toLowerCase();

        // Determinación de semestre
        System.out.print("Ingrese el semestre actual (1, 2, 3, etc.): ");
        int semestre = scanner.nextInt();

        // Cálculo de matrícula
        double montoMatricula = calcularMatricula(cursoEnLinea, semestre);
        double montoMateria = 0;
        if (cursoEnLinea.equals("no")) {
            System.out.print("Ingrese el número de materias perdidas: ");
            int materiasPerdidas = scanner.nextInt();
            montoMateria = calcularMateriasPerdidas(materiasPerdidas, semestre);
        }

        // Recibo de inscripción
        imprimirRecibo(nombres, apellidos, documento, direccion, telefono, carrera, semestre, montoMatricula, montoMateria);
    }

    // Método para calcular el monto de la matrícula
    public static double calcularMatricula(String cursoEnLinea, int semestre) {
        double monto = 1500;
        if (cursoEnLinea.equals("si")) {
            if (semestre >= 5) {
                monto += monto * 0.05;  // Aumento del 5% a partir del quinto semestre
            }
        } else {
            monto += 0;  // Para estudiantes con materias perdidas, se calcula por créditos
        }
        return monto;
    }

    // Método para calcular el costo de las materias perdidas
    public static double calcularMateriasPerdidas(int materiasPerdidas, int semestre) {
        double costoPorCredito;
        if (semestre <= 3) {
            costoPorCredito = 20;  // Primeros tres semestres
        } else if (semestre <= 6) {
            costoPorCredito = 25;  // Cuarto, quinto y sexto semestre
        } else {
            costoPorCredito = 30;  // Séptimo semestre en adelante
        }
        return materiasPerdidas * costoPorCredito;
    }

    // Método para imprimir el recibo de inscripción
    public static void imprimirRecibo(String nombres, String apellidos, String documento, String direccion, String telefono, int carrera, int semestre, double montoMatricula, double montoMateria) {
        String carreraSeleccionada = " ";
           switch (carrera) {
            case 1: carreraSeleccionada = "Ingeniería en Sistemas"; break;
            case 2: carreraSeleccionada = "Medicina"; break;
            case 3: carreraSeleccionada = "Derecho"; break;
            default: carreraSeleccionada = "No válida"; break;
        }

        double total = montoMatricula + montoMateria;

        System.out.println("\n------ Recibo de Inscripción ------");
        System.out.println("Estudiante: " + nombres + " " + apellidos);//apalledos lo dejo en blanco para que me arroje en una sola linea.
        System.out.println("Documento: " + documento);
        System.out.println("Dirección: " + direccion);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Carrera: " + carreraSeleccionada);
        System.out.println("Semestre: " + semestre);
        System.out.println("Monto de matrícula: $" + montoMatricula);
        if (montoMateria > 0) {
            System.out.println("Costo por materias perdidas: $" + montoMateria);
        }
        System.out.println("Total a pagar: $" + total);
        System.out.println("----------------------------------");
    }
}

