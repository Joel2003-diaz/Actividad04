package com.mycompany.cuentasbancarias;
/**
La La clase Cuenta se crea como abstracta porque no tiene sentido crear directamente un objeto de tipo Cuenta,
* ya que representa una "plantilla" general que debe ser especializada por sus clases hijas, 
* como CuentaCorriente y CuentaAhorros. Al hacerla abstracta, 
* estamos declarando que la clase no puede ser instanciada directamente,
* pero sí puede ser heredada y utilizada por las clases hijas.
 */
public abstract class Cuenta {
    protected String nombreCliente;
    protected String apellidosCliente;
    protected int edadCliente;
    protected double saldo;
    protected String tipoCuenta;
    protected String nombreRepresentante;
    protected String apellidosRepresentante;
    
    public Cuenta(String nombreCliente, String apellidosCliente, int edadCliente, double saldo, String tipoCuenta) {
        this.nombreCliente = nombreCliente;
        this.apellidosCliente = apellidosCliente;
        this.edadCliente = edadCliente;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;

    }
    public abstract void realizarDeposito(double monto);
    public abstract void retirarDinero(double monto);
    public abstract void aplicarMantenimiento();
    public abstract void mostrarSaldo();

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }
}

