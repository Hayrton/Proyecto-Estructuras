/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.estructuras;

import com.Proyecto2.modelo.Destino;

/**
 *
 * @author Hayrton
 */
public class ListaEncabezado {
    Encabezado primero;
    Encabezado ultimo;
    
    public ListaEncabezado(){
        this.primero = null;
        this.ultimo = null;
    }
    
    public void Insertar(Encabezado nuevo){
        if(primero == null){
            primero = ultimo = nuevo;
        }else if(((Destino)nuevo.dato).getCodigo().compareTo(((Destino)primero.dato).getCodigo()) < 0){
            nuevo.siguiente = primero;
            primero.anterior = nuevo;
            primero = nuevo;
        }else{
            Encabezado actual = primero;
            Encabezado ant = null;
            while(((Destino)nuevo.dato).getCodigo().compareTo(((Destino)actual.dato).getCodigo()) > 0 && actual.siguiente != null){
                ant = actual;
                actual = actual.siguiente;
            }
            
            if(((Destino)nuevo.dato).getCodigo().compareTo(((Destino)actual.dato).getCodigo()) == 0){
                System.out.println("id de destino respetido, no ingresado");
            }else{
                if(((Destino)nuevo.dato).getCodigo().compareTo(((Destino)actual.dato).getCodigo()) > 0){
                    ultimo.siguiente = nuevo;
                    nuevo.anterior = ultimo;
                    ultimo = nuevo;
                }else{
                    nuevo.siguiente = actual;
                    actual.anterior = nuevo;
                    nuevo.anterior = ant;
                    ant.siguiente = nuevo;  
                }
            }
        }
    }
    
    public Encabezado getEncabezado(String id){
        if(primero != null){
            Encabezado actual = primero;
            while(actual != null){
                if(((Destino)actual.dato).getCodigo().equals(id)){
                    return actual;
                }
                actual = actual.siguiente;
            }
        }
        return null;
    }
}
