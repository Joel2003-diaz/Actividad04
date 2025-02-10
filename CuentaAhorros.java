/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cuentasbancarias;

/**
Creamos su segundo hijo como CuentaAhorros.
 */
public class CuentaAhorros extends Cuenta {

    private static final double  TASA_RENDIMIENTO= 0.022; // 2.2% anual

    public CuentaAhorros(String nombreCliente, String apellidosCliente, int edadCliente, double saldo) {
        super(nombreCliente, apellidosCliente, edadCliente, saldo, "Ahorros");
    }

    @Override
    public void realizarDeposito(double monto) {
        double cargos = 0;
        if (monto < 500000) {
            cargos = 3000 + (monto * 0.01);
        } else if (monto >= 500000 && monto < 2000000) {
            cargos = 2000 + (monto * 0.005);
        } else if (monto >= 2000000 && monto <= 10000000) {
            cargos = 1000 + (monto * 0.018);
        } else {
            cargos = monto * 0.02;
        }
        saldo += (monto - cargos);
        System.out.println("Depósito realizado. Cargos por depósito: " + cargos + " pesos.");
    }

    @Override
    public void retirarDinero(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            System.out.println("Retiro exitoso de " + monto + " pesos.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    @Override
    public void aplicarMantenimiento() {
        double rendimiento = saldo * TASA_RENDIMIENTO;
        saldo += rendimiento;
        System.out.println("Se aplicó un rendimiento de " + rendimiento + " pesos.");
    }

    @Override
    public void mostrarSaldo() {
        System.out.println("Saldo actual de la cuenta de ahorros: " + saldo + " pesos.");
    }
}

