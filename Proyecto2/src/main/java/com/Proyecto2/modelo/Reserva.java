/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.modelo;

/**
 *
 * @author Hayrton
 */
public class Reserva {
    
    private int no_reserva;
    private int costo;
    private int tiempo;
    private String cliente;

    public Reserva() {
    }

    public Reserva(int no_reserva, int costo, int tiempo, String cliente) {
        this.no_reserva = no_reserva;
        this.costo = costo;
        this.tiempo = tiempo;
        this.cliente = cliente;
    }
    
    public String imprimeReserva(){
        StringBuilder builder = new StringBuilder();
        builder.append("No. Reserva: ").append(getNo_reserva()).append("\\n");
        builder.append("Costo: ").append(getCosto()).append("\\n");
        builder.append("Tiempo: ").append(getTiempo()).append("\\n");
        builder.append("Cliente").append(getCliente()).append("\\n");
        return builder.toString();
    }

    /**
     * @return the no_reserva
     */
    public int getNo_reserva() {
        return no_reserva;
    }

    /**
     * @param no_reserva the no_reserva to set
     */
    public void setNo_reserva(int no_reserva) {
        this.no_reserva = no_reserva;
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

    /**
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
}
