/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.estructuras;

/**
 *
 * @author Hayrton
 */
public class Vuelo {
    private String punto1;
    private String punto2;
    private int costo;
    private int tiempo;

    /**
     * @return the punto1
     */
    public String getPunto1() {
        return punto1;
    }

    /**
     * @param punto1 the punto1 to set
     */
    public void setPunto1(String punto1) {
        this.punto1 = punto1;
    }

    /**
     * @return the punto2
     */
    public String getPunto2() {
        return punto2;
    }

    /**
     * @param punto2 the punto2 to set
     */
    public void setPunto2(String punto2) {
        this.punto2 = punto2;
    }

    /**
     * @return the costo
     */
    public int getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(int costo) {
        this.costo = costo;
    }

    /**
     * @return the tiempo
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
}
