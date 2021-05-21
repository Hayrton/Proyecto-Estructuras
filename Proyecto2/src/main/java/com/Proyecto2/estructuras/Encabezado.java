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
public class Encabezado {
    Object dato;
    Encabezado siguiente;
    Encabezado anterior;
    NodoM acceso;
    
    public Encabezado(Object dato){
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
        this.acceso = null;
    }
}
