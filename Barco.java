package com.mycompany.alquiler;

/**
Se crea la clase Barco.
 */
public class Barco {
    private String matricula;
    private double eslora; // en metros
    private int añoFabricacion;

    public Barco(String matricula, double eslora, int añoFabricacion) {
        this.matricula = matricula;
        this.eslora = eslora;
        this.añoFabricacion = añoFabricacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public double getEslora() {
        return eslora;
    }

    public int getAñoFabricacion() {
        return añoFabricacion;
    }
}

