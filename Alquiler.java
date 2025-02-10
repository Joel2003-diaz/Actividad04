/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alquiler;

/**
Se crea la clase Alquiler e impportamos las librerias que utilicemos
 */
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Alquiler {
    private String nombreCliente;
    private int cantidadClientes;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String posicionAmarre;
    private Barco barco;

    // Constructor
    public Alquiler(String nombreCliente, int cantidadClientes, LocalDate fechaInicio, 
                    LocalDate fechaFin, String posicionAmarre, Barco barco) {
        this.nombreCliente = nombreCliente;
        this.cantidadClientes = cantidadClientes;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.posicionAmarre = posicionAmarre;
        this.barco = barco;
    }

    // Cálculo del total a pagar
    public double calcularAlquiler() {
        long diasOcupacion = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        double costoBase = diasOcupacion * 25000;  // 25 mil pesos por día
        double impuestos = costoBase * 0.16; // Supongamos que el impuesto es el 16%(ya que le ejercicio no nos da).

        return costoBase + impuestos;
    }

    // Imprimir recibo
    public void imprimirRecibo() {
        double totalPagar = calcularAlquiler();

        System.out.println("------ Recibo de Alquiler ------");
        System.out.println("Cliente: " + nombreCliente);
        System.out.println("Cantidad de clientes: " + cantidadClientes);
        System.out.println("Posición del amarre: " + posicionAmarre);
        System.out.println("Barco:");
        System.out.println("  Matrícula: " + barco.getMatricula());
        System.out.println("  Eslora: " + barco.getEslora() + " metros");
        System.out.println("  Año de fabricación: " + barco.getAñoFabricacion());
        System.out.println("Fecha de inicio: " + fechaInicio);
        System.out.println("Fecha de fin: " + fechaFin);
        System.out.println("Total a pagar (con impuestos): " + totalPagar + " pesos");
        System.out.println("--------------------------------");
    }
}

