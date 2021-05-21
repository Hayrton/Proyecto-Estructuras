/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.estructuras;

import com.Proyecto2.modelo.Reserva;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Hayrton
 */
public class Hash {
    protected int tamanio;
    protected int indiceTam;
    protected int tamanios[];
    protected int numElementos;
    protected double factorUtilidad;
    protected Object element[];

    public Hash() {
        this.tamanios = new int[]{43,47,53,59,67,73,79,83,89,97,103,107,113,127,137,149,157,167,179,197};
        this.indiceTam = 0;
        this.tamanio = tamanios[indiceTam];
        this.element = new Object[tamanio];
        this.numElementos = 0;
        this.factorUtilidad = 0.0;
    }
    
    public boolean Insertar(Reserva reserva){
        try {
            Insertando(reserva);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private void Insertando(Reserva reserva){
        int tmp = Division(reserva.getNo_reserva());
        int posicion = HashingLineal(tmp, reserva.getNo_reserva(), tamanio,false);
        element[posicion] = reserva;
        numElementos++;
        factorUtilidad = (double)numElementos/tamanio;
        if(factorUtilidad >= 0.5){
            System.out.println("se realizara rehashing");
            Rehashing();
        }
    }
    
    private int HashingLineal(int posicion,int clave, int m, boolean buscar){
        int i = 0;
        if(element[posicion] != null){
            System.out.println("Ha ocurrido una colision en  la posicion: " + posicion);
        }
        while(element[posicion] != null){
            if(((Reserva)element[posicion]).getNo_reserva() != clave){
                i++;
                posicion = posicion + i*i;
            }else{
                if(buscar)
                    return posicion;
                System.out.println("no se realizo la insercion clave "+clave+ " repetida");
                break;
            }
        }
        return posicion;
    }
    
    private int Division(int clave){
        return clave%tamanio;
    }
    
    private void Rehashing(){
        try {
            Object[] tmp = element;
            if(indiceTam < tamanios.length){
                indiceTam+=1;
                if(indiceTam == tamanios.length){
                    System.out.println("si alacanzo el tamanio maximos de arreglos");
                }
            }
            tamanio = tamanios[indiceTam];
            element = new Object[tamanio];
            numElementos = 0;
            for(Object temp1 : tmp){
                if(temp1 != null){
                    Insertando((Reserva)temp1);
                }
            }
            
        } catch (Exception e) {
            System.out.println("error al realizar el rehashing");
        }
    }

    public boolean Graficar(){
        try {
            File archivo = new File("C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\tablahash.dot");
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            StringBuilder builder = new StringBuilder();
            builder.append(generarDot());
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(builder.toString());
            bw.close();

            String[] cmd = new String[5];
            cmd[0] = "dot.exe";
            cmd[1] = "-Tpng";
            cmd[2] = "C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\tablahash.dot";
            cmd[3] = "-o";
            cmd[4] = "C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\tablahash.png";
            

            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    private String generarDot(){
        StringBuilder builder = new StringBuilder();
        builder.append("digraph tabla{\nnode[shape=record];\n");
        builder.append("graph[rankdir=LR, ordering=out];\n");
        builder.append(contenidotabla());
        builder.append("}");
        return builder.toString();
    }
    
    private String contenidotabla(){
        StringBuilder builder = new StringBuilder();
        boolean flag = false;
        builder.append("vector[height=5 label=\"");
        for(int i=0; i<tamanio; i++){
            if(flag){
                builder.append(" | ");
            }
            builder.append("<sector").append(i).append("> posicion: ").append(i);
            flag = true;
        }
        builder.append("\"];\n");
        for(int i=0; i<tamanio; i++){
            if(element[i] != null){
                Object tmp = element[i];
                if(tmp != null){
                    builder.append("vector:sector").append(i).append(" -> ");
                    builder.append("nodo").append(tmp.hashCode()).append("TH").append("\n");
                }
                builder.append("nodo").append(tmp.hashCode()).append("TH").append("[label=\"").append(((Reserva)tmp).imprimeReserva());
                builder.append("\"];\n");
            }
        }
        return builder.toString();
    }

    public void imprimir(){
        for (Object element1 : element) {
            if(element1 == null){
                System.out.println("null");
            }else{
                System.out.println("id: "+ ((Reserva)element1).getNo_reserva());
            }
        }
        System.out.println("tamanio actual del arreglo: " + tamanio);
    }
}
