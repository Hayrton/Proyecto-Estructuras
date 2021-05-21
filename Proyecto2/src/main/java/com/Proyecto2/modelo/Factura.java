/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.modelo;

import com.Proyecto2.estructuras.AdminObject;
import com.Proyecto2.estructuras.Comparador;

/**
 *
 * @author Hayrton
 */
public class Factura implements AdminObject, Comparador {
    private int id_factura;
    private String fecha;
    private String hora;
    private int costo;
    private String cliente;
    private String descripcion;

    public Factura() {
    }
    

    public Factura(int id_factura, String fecha, String hora, int costo, String cliente, String descripcion) {
        this.id_factura = id_factura;
        this.fecha = fecha;
        this.hora = hora;
        this.costo = costo;
        this.cliente = cliente;
        this.descripcion = descripcion;
    }

    public String escribirObjetoConsole() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return "id_factura: "+getId_factura()+" Fecha: "+getFecha()+" Hora: "+getHora()+" Costo: "+getCosto()+" Cliente: "+getCliente()+" descripcin: "+ getDescripcion();
    }

    public void modificarObjeto(Object q) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String escribirObjeto() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        StringBuilder buffer = new StringBuilder();
        buffer.append("Id_factura: ").append(getId_factura()).append("\\n");
        buffer.append("Fecha: ").append(getFecha()).append("\\n");
        buffer.append("Hora: ").append(getHora()).append("\\n");
        buffer.append("Costo").append(getCosto()).append("\\n");
        buffer.append("Cliente: ").append(getCliente()).append("\\n");
        buffer.append("Descripcion: ").append(getDescripcion()).append("\\n");
        return buffer.toString();
    }

    public String devolverClave() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean esIgual(Object q) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Factura f = (Factura)q;
        return getId_factura() == f.getId_factura(); 
    }

    public boolean esMayor(Object q) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Factura f = (Factura)q;
        return getId_factura() > f.getId_factura(); 
    }

    public boolean esMenor(Object q) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Factura f = (Factura)q;
        return getId_factura() < f.getId_factura(); 
    }

    public boolean mayorIgual(Object q) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Factura f = (Factura)q;
        return getId_factura() >= f.getId_factura(); 
    }

    public boolean menorIgual(Object q) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Factura f = (Factura)q;
        return getId_factura() <= f.getId_factura(); 
    }

    /**
     * @return the id_factura
     */
    public int getId_factura() {
        return id_factura;
    }

    /**
     * @param id_factura the id_factura to set
     */
    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
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

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
