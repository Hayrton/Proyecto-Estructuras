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
public class NodoM {
    Object dato;
    String fila;
    String columna;
    NodoM derecha;
    NodoM izquierda;
    NodoM abajo;
    NodoM arriba;
    
    public NodoM(Object dato, String fila, String columna){
        this.dato = dato;
        this.fila = fila;
        this.columna = columna;
        this.derecha = null;
        this.izquierda = null;
        this.abajo = null;
        this.arriba = null;
    }
}
