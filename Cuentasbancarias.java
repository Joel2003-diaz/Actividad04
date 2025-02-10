package com.mycompany.cuentasbancarias;

/**
 * Clase principal que gestiona el sistema bancario simulando operaciones como apertura de cuentas,
 * transferencias, retiros en cajero automático y cierre de mes.
 */
import java.util.Scanner;

public class Cuentasbancarias {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Variables para almacenar los datos de la cuenta
        String nombre = "";
        String apellidos = "";
        int edad = 0;
        Cuenta cuenta = null;

        // Menú de operaciones
        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Apertura de Cuentas");
            System.out.println("2. Transferencias");
            System.out.println("3. Cajero Automático");
            System.out.println("4. Cierre de mes (Estado de Cuenta)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Lógica de apertura de cuentas
                    // Solicitar los datos del cliente solo si no están almacenados
                    if (cuenta == null) {
                        scanner.nextLine();  // Limpiar el buffer de scanner antes de usar nextLine

                        System.out.print("Ingrese el nombre del cliente: ");
                        nombre = scanner.nextLine();

                        System.out.print("Ingrese los apellidos del cliente: ");
                        apellidos = scanner.nextLine();

                        System.out.print("Ingrese la edad del cliente: ");
                        edad = scanner.nextInt();

                        // Si el cliente es menor de edad, también pedimos los datos del representante
                        String nombreRepresentante = "";
                        String apellidosRepresentante = "";
                        if (edad < 18) {
                            scanner.nextLine();  // Limpiar buffer de nuevo antes de usar nextLine
                            System.out.print("Ingrese el nombre del representante: ");
                            nombreRepresentante = scanner.nextLine();

                            System.out.print("Ingrese los apellidos del representante: ");
                            apellidosRepresentante = scanner.nextLine();
                            System.out.println("Cliente es menor de edad. Representante legal es : " 
                                    + nombreRepresentante + " " + apellidosRepresentante);  
                        }

                        // Selección del tipo de cuenta
                        System.out.print("Seleccione tipo de cuenta (1 para Ahorros, 2 para Corriente): ");
                        int tipoCuenta = scanner.nextInt();

                        // Crear la cuenta
                        if (tipoCuenta == 1) {
                            cuenta = new CuentaAhorros(nombre, apellidos, edad, 1000000);  // Ejemplo de saldo inicial
                        } else if (tipoCuenta == 2) {
                            cuenta = new CuentaCorriente(nombre, apellidos, edad, 200000);  // Ejemplo de saldo inicial
                        }

                        // Confirmación de cuenta creada
                        System.out.println("Cuenta " + (tipoCuenta == 1 ? "de Ahorros" : "Corriente") + " creada exitosamente.");
                    } else {
                        System.out.println("Ya tiene una cuenta creada. Si desea abrir una nueva, cierre la sesión y vuelva a intentarlo.");
                    }
                    break;

                case 2:
                    // Llamada al método de transferencia
                    if (cuenta != null) {
                        realizarTransferencia(cuenta);
                    } else {
                        System.out.println("Primero debe abrir una cuenta.");
                    }
                    break;

                case 3:
                    // Llamada al método de retiro en cajero
                    if (cuenta != null) {
                        realizarRetiroEnCajero(cuenta);
                    } else {
                        System.out.println("Primero debe abrir una cuenta.");
                    }
                    break;

                case 4:
                    // Llamada al cierre de mes (aplicar mantenimiento o rendimiento)
                    if (cuenta != null) {
                        cerrarMes(cuenta);
                    } else {
                        System.out.println("Primero debe abrir una cuenta.");
                    }
                    break;

                case 0:
                    // Salir del sistema
                    System.out.println("Gracias por usar el sistema.");
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Método para realizar un retiro en un cajero
    public static void realizarRetiroEnCajero(Cuenta cuenta) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el monto a retirar: ");
        double monto = scanner.nextDouble();

        // Verificar si el cliente tiene suficiente saldo
        if (cuenta.getSaldo() >= monto) {
            // Preguntar si el retiro es en un cajero del banco o externo
            System.out.print("¿Está utilizando un cajero del banco (S/N)? ");
            char esCajeroDelBanco = scanner.next().charAt(0);

            if (esCajeroDelBanco == 'S' || esCajeroDelBanco == 's') {
                // No se cobra comisión si es del banco
                cuenta.retirarDinero(monto);
                System.out.println("Retiro realizado exitosamente sin comisión.");
            } else {
                // Se cobra comisión si es un cajero externo
                final double COMISION_CAJERO_EXTERNO = 4500;
                double saldoConComision = monto + COMISION_CAJERO_EXTERNO;

                if (cuenta.getSaldo() >= saldoConComision) {
                    cuenta.retirarDinero(saldoConComision);
                    System.out.println("Retiro realizado exitosamente, comisión por cajero externo aplicada.");
                } else {
                    System.out.println("Saldo insuficiente para realizar el retiro con la comisión.");
                }
            }
        } else {
            System.out.println("Saldo insuficiente para realizar el retiro.");
        }
        cuenta.mostrarSaldo();
    }

    // Método para realizar una transferencia
    public static void realizarTransferencia(Cuenta cuentaOrigen) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del destinatario: ");
        String nombreDestinatario = scanner.nextLine();

        System.out.print("Ingrese el monto a transferir: ");
        double monto = scanner.nextDouble();

        // Pedir la cuenta destino (puedes hacer que esta también sea una cuenta creada antes)
        // Ejemplo: vamos a suponer que la cuenta de destino es una cuenta de ahorros con un saldo inicial
        Cuenta cuentaDestino = new CuentaAhorros("Cliente Destino", "Apellidos Destino", 25, 500000);

        if (cuentaOrigen.getSaldo() >= monto) {
            cuentaOrigen.retirarDinero(monto);  // Realiza el retiro en la cuenta origen
            cuentaDestino.realizarDeposito(monto);  // Realiza el depósito en la cuenta destino
            System.out.println("Transferencia realizada con éxito a: " + nombreDestinatario);
        } else {
            System.out.println("Saldo insuficiente para realizar la transferencia.");
        }
    }

    // Método para cerrar el mes (aplicar mantenimiento y rendimiento)
    public static void cerrarMes(Cuenta cuenta) {
        System.out.println("\nCerrando mes para la cuenta de: " + cuenta.nombreCliente + " " + cuenta.apellidosCliente);

        // Aplicar el mantenimiento mensual o rendimiento
        cuenta.aplicarMantenimiento();

        // Mostrar saldo actualizado después del cierre de mes
        cuenta.mostrarSaldo();
    }
}

