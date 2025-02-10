package com.mycompany.cuentasbancarias;

/**
Si el cliente saca dinero por cajeros pertenecientes al banco, no se
cobra comisión; si el cliente saca dinero en cajeros diferentes a los
del banco, se cobra 4500 pesos en cada transacción.
 */
public class Cajero {

    public static final double COMISION_EXTERNO = 4500; // Comisión en cajeros fuera del banco

    public static void retirarDeCajero(Cuenta cuenta, double monto, boolean esCajeroBanco) {
        if (!esCajeroBanco) {
            monto += COMISION_EXTERNO;
            System.out.println("Se ha cobrado una comisión de " + COMISION_EXTERNO + " pesos por usar un cajero externo.");
        }
        cuenta.retirarDinero(monto);
    }
}

