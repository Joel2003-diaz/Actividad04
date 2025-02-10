package com.mycompany.cuentasbancarias;

/**
Se crea su primer hijo.
 */
public class CuentaCorriente extends Cuenta {

    private static final double TASA_MANTENIMIENTO = 0.015; // 1.5% mensual
    private static final double MINIMO_APERTURA = 200000;

    public CuentaCorriente(String nombreCliente, String apellidosCliente, int edadCliente, double saldo) {
        super(nombreCliente, apellidosCliente, edadCliente, saldo, "Corriente");
        if (saldo < MINIMO_APERTURA) {
            throw new IllegalArgumentException("El saldo inicial debe ser al menos 200 mil pesos.");
        }
    }

    @Override
    public void realizarDeposito(double monto) {
        double cargos = 0;
        if (monto < 500000) {
            cargos = 7000;
        } else if (monto >= 500000 && monto < 2000000) {
            cargos = 5000 + (monto * 0.02);
        } else if (monto >= 2000000 && monto <= 10000000) {
            cargos = 4000 + (monto * 0.02);
        } else {
            cargos = monto * 0.033;
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
        double mantenimiento = saldo * TASA_MANTENIMIENTO;
        saldo -= mantenimiento;
        System.out.println("Se aplicó una tasa de mantenimiento de " + mantenimiento + " pesos.");
    }

    @Override
    public void mostrarSaldo() {
        System.out.println("Saldo actual de la cuenta corriente: " + saldo + " pesos.");
    }
}
