/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.estructuras;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hayrton
 */

public class ListaS {
    NodoS primero;

    public ListaS() {
        this.primero = null;
    }
    
    public void insertar(String reservacion){
        NodoS nuevo = new NodoS(reservacion);
        if(primero == null){
            primero = nuevo;
        }else{
            NodoS actual = primero;
            while(actual.siguiente != null){
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }
    
    public List<String> miReservas(){
        List<String> tmp = new ArrayList();
        if(primero != null){
            NodoS actual = primero;
            while(actual != null){
                tmp.add(actual.reservacion);
                actual = actual.siguiente;
            }
        }
        else{
            return null;
        }
        return tmp;
    }
    
}

class NodoS{
    String reservacion;
    NodoS siguiente;

    public NodoS(String reservacion) {
        this.reservacion = reservacion;
        this.siguiente = null;
    }
    
}