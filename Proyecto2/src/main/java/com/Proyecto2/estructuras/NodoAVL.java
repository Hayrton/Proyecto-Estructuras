/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.estructuras;

import com.Proyecto2.modelo.Usuarios;

/**
 *
 * @author Hayrton
 */
public class NodoAVL {
    //public int dato;
    public Usuarios user;
    public NodoAVL izq;
    public NodoAVL der;
    public int fe;
    public ListaS milista;

    public NodoAVL(Usuarios user) {
        //this.dato = dato;
        this.user = user;
        this.izq = null;
        this.der = null;
        this.fe = 0;
        this.milista = new ListaS();
    }
    
    public NodoAVL(NodoAVL ramaIzq, Usuarios user, NodoAVL ramaDer){
        //this.dato = valor;
        this.user = user;
        this.izq = ramaIzq;
        this.der = ramaDer;
    }
    
    public String valorNodo(){ return user.getId();}
    public NodoAVL subarbolIzq(){ return izq;}
    public NodoAVL subarbolDer(){ return der;}
    public void nuevoValor(Usuarios d){ user= d;}
    public void ramaIzq(NodoAVL n){ izq = n;}
    public void ramaDer(NodoAVL n){ der = n;}
}
