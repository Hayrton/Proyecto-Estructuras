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
public interface Comparador {
    boolean esIgual(Object q);
    boolean esMayor(Object q);
    boolean esMenor(Object q);
    boolean mayorIgual(Object q);
    boolean menorIgual(Object q);
}
