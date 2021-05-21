/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.estructuras;

import com.Proyecto2.modelo.Destino;
import com.Proyecto2.modelo.Reserva;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Hayrton
 */
public class MatrizAD {

    ListaEncabezado encabezadoFila;
    ListaEncabezado encabezadoColumna;
    String cadena;
    public static List<Destino> temp;
    Hash hs;
    String clientetmp;
    
    int contador;
    String[] misrutas;
    
    public MatrizAD() {
        this.encabezadoFila = new ListaEncabezado();
        this.encabezadoColumna = new ListaEncabezado();
        this.temp = new ArrayList();
        this.hs = new Hash();
        this.misrutas = new String[5];
    }

    public void Insertar(Object dato, String fila, String columna) {
        NodoM nuevo = new NodoM(dato, fila, columna);
        Encabezado Fila = encabezadoFila.getEncabezado(fila);
        if (Fila.acceso == null) {
            Fila.acceso = nuevo;
        } else if (nuevo.fila.compareTo(Fila.acceso.columna) < 0) {
            nuevo.derecha = Fila.acceso;
            Fila.acceso.izquierda = nuevo;
            Fila.acceso = nuevo;
        } else {
            NodoM actual = Fila.acceso;
            NodoM ant = null;
            while ((nuevo.fila.compareTo(actual.fila)) > 0 && actual.derecha != null) {
                ant = actual;
                actual = actual.derecha;
            }
            if (nuevo.fila.compareTo(actual.fila) == 0) {
                System.out.println("encabezado repetido no ingresado");
            } else {
                if (nuevo.fila.compareTo(actual.fila) > 0) {
                    actual.derecha = nuevo;
                    nuevo.izquierda = actual;
                } else {
                    nuevo.derecha = actual;
                    actual.izquierda = nuevo;
                    ant.derecha = nuevo;
                    nuevo.izquierda = ant;
                }
            }
        }

        Encabezado Columna = encabezadoColumna.getEncabezado(columna);
        if (Columna.acceso == null) {
            Columna.acceso = nuevo;
        } else if (nuevo.columna.compareTo(Columna.acceso.fila) < 0) {
            nuevo.abajo = Columna.acceso;
            Columna.acceso.arriba = nuevo;
            Columna.acceso = nuevo;
        } else {
            NodoM actual = Columna.acceso;
            NodoM ant = null;
            while ((nuevo.columna.compareTo(actual.columna)) > 0 && actual.abajo != null) {
                ant = actual;
                actual = actual.abajo;
            }
            if (nuevo.columna.compareTo(actual.columna) == 0) {
                System.out.println("encabezado repetido no ingresado");
            } else {
                if (nuevo.columna.compareTo(actual.columna) > 0) {
                    actual.abajo = nuevo;
                    nuevo.arriba = actual;
                } else {
                    nuevo.abajo = actual;
                    actual.arriba = nuevo;
                    ant.abajo = nuevo;
                    nuevo.arriba = ant;
                }
            }
        }
    }
    
    private String BuscarPais(String pais){
        String tmp = "";
        if(encabezadoFila.primero != null){
            Encabezado actual = encabezadoFila.primero;
            while(actual != null){
                if(((Destino)actual.dato).getNombre().equals(pais)){
                    
                    tmp = ((Destino)actual.dato).getCodigo();
                    //System.out.println("encontrado: " + tmp);
                    break;
                }
                actual = actual.siguiente;
            }
        }
        return tmp;
    }

    public void InsertarCabecera(Destino dato) {
        encabezadoFila.Insertar(new Encabezado(dato));
        encabezadoColumna.Insertar(new Encabezado(dato));
    }
    
    public void setDestinos(){
        if(encabezadoFila.primero != null){
            Encabezado actual = encabezadoFila.primero;
            while(actual != null){
                //System.out.println("Destinos: "+((Destino)actual.dato).getNombre());
                temp.add((Destino)actual.dato);
                actual = actual.siguiente;
            }
        }
    }
    
    public List<Destino> getDestionos(){
        return temp;
    }
    
    public Reserva BuscarRuta(String origen, String destino){
        Reserva reserva = null;
        if(encabezadoFila.primero != null){
            Encabezado actual = encabezadoFila.primero;
            while(actual != null){
                if(((Destino)actual.dato).getNombre().equals(origen)){
                    NodoM tmp = actual.acceso;
                    while(tmp != null){
                        if(((Vuelo)tmp.dato).getPunto2().equals(BuscarPais(destino))){
                            Random no = new Random();
                            int val = 1000 + no.nextInt((9999+1)-1000);
                            reserva = new Reserva(val,((Vuelo)tmp.dato).getCosto(),((Vuelo)tmp.dato).getTiempo(),"Omar");
                            //hs.Insertar(reserva);
                            break;
                        }
                        tmp = tmp.derecha;
                    }
                    break;
                }
                actual = actual.siguiente;
            }
        }
        return reserva;
    }

    public void graficaMatrizAD() {
        try {
            cadena = "";
            //System.out.println("si llego aqui :(");
            File archivo = new File("C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\MatrizAD.dot");
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            cadena = "digraph g{\n";
            cadena += "node[shape=box, style=filled, color=Gray95];\n";
            cadena += "edge[color=black];\n";
            cadena += "rankdir=UD;\n";
            cadena += "{rank=min;\"Matriz\";" + CabeceraX() + "};\n";
            cadena += encabezaFilas();
            cadena += Columnas();
            cadena += enlaceColumnas();
            cadena += Filas();
            cadena += enlaceFilas();
            cadena += cabeceraColumna();
            cadena += cabeceraFila();
            cadena += "}";
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(cadena);
            bw.close();
            
            
            String[] cmd = new String[5];
            cmd[0] = "dot.exe";
            cmd[1] = "-Tpng";
            cmd[2] = "C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\MatrizAD.dot";
            cmd[3] = "-o";
            cmd[4] = "C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\MatrizAD.png";
            
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            
        } catch (IOException e) {
            System.out.println("error: " + e);
        }
    }

    private String CabeceraX() {
        String cont = "";
        if (encabezadoColumna.primero != null) {
            Encabezado actual = encabezadoColumna.primero;
            while (actual != null) {
                cont += "\"Destino: \n"+((Destino)actual.dato).getNombre()+"\";";
                actual = actual.siguiente;
            }
        }
        return cont;
    }
    
    private String encabezaFilas(){
        String cont = "";
        Encabezado actual = encabezadoFila.primero;
        while (actual != null){
            cont += "{rank=same;";
            cont += "\"Origen: \n" + ((Destino)actual.dato).getNombre()+"\";";
            NodoM tempo = actual.acceso;
            while(tempo != null){
                cont += "\"" + ((Vuelo)tempo.dato).getPunto1() + "-" + ((Vuelo)tempo.dato).getPunto2() + "\n";
                cont += ((Vuelo)tempo.dato).getCosto() + "\n" + ((Vuelo)tempo.dato).getTiempo() + "\";";
                tempo = tempo.derecha;
            }
            cont += "};\n";
            actual = actual.siguiente;
        }
        return cont;
    }
    
    private String Columnas(){
        String cont = "";
        Encabezado actual = encabezadoColumna.primero;
        while(actual != null){
            if(actual.acceso != null){
                NodoM tempo = actual.acceso;
                while(tempo != null){
                    cont += "\"" + ((Vuelo)tempo.dato).getPunto1() + "-" + ((Vuelo)tempo.dato).getPunto2() + "\n";
                    cont += ((Vuelo)tempo.dato).getCosto() + "\n" + ((Vuelo)tempo.dato).getTiempo() + "\"";
                    if(tempo.abajo != null){
                        cont += " -> ";
                        cont += "\"" + ((Vuelo)tempo.abajo.dato).getPunto1() + "-" + ((Vuelo)tempo.abajo.dato).getPunto2() + "\n";
                        cont += ((Vuelo)tempo.abajo.dato).getCosto() + "\n" + ((Vuelo)tempo.abajo.dato).getTiempo() + "\"";
                    }
                    cont += ";\n";
                    tempo = tempo.abajo;
                }
            }
            actual = actual.siguiente;
        }
        return cont;
    }
    
    private String enlaceColumnas(){
        String cont = "";
        Encabezado actual = encabezadoColumna.primero;
        while(actual != null){
            cont += "\"Destino: \n" + ((Destino)actual.dato).getNombre() + "\"";
            if(actual.acceso != null){
                cont += " -> ";
                cont += "\"" + ((Vuelo)actual.acceso.dato).getPunto1() + "-" + ((Vuelo)actual.acceso.dato).getPunto2() + "\n";
                cont += ((Vuelo)actual.acceso.dato).getCosto() + "\n" + ((Vuelo)actual.acceso.dato).getTiempo() + "\"";
            }
            cont += ";\n";
            actual = actual.siguiente;
        }
        return cont;
    }
    
    private String Filas(){
        String cont = "";
        Encabezado actual = encabezadoFila.primero;
        while(actual != null){
            if(actual.acceso != null){
                NodoM tempo = actual.acceso;
                while(tempo != null){
                    cont += "\"" + ((Vuelo)tempo.dato).getPunto1() + "-" + ((Vuelo)tempo.dato).getPunto2() + "\n";
                    cont += ((Vuelo)tempo.dato).getCosto() + "\n" + ((Vuelo)tempo.dato).getTiempo() + "\"";
                    if(tempo.derecha != null){
                        cont += " -> ";
                        cont += "\"" + ((Vuelo)tempo.derecha.dato).getPunto1() + "-" + ((Vuelo)tempo.derecha.dato).getPunto2() + "\n";
                        cont += ((Vuelo)tempo.derecha.dato).getCosto() + "\n" + ((Vuelo)tempo.derecha.dato).getTiempo() + "\"";
                    }
                    cont += "[constraint=false];\n";
                    tempo = tempo.derecha;
                }
            }
            actual = actual.siguiente;
        }
        return cont;
    }
    
    private String enlaceFilas(){
        String cont = "";
        Encabezado actual = encabezadoFila.primero;
        while(actual != null){
            cont += "\"Origen: \n" + ((Destino)actual.dato).getNombre() + "\"";
            if(actual.acceso != null){
                cont += " -> ";
                cont += "\"" + ((Vuelo)actual.acceso.dato).getPunto1() + "-" + ((Vuelo)actual.acceso.dato).getPunto2() + "\n";
                cont += ((Vuelo)actual.acceso.dato).getCosto() + "\n" + ((Vuelo)actual.acceso.dato).getTiempo() + "\"";
            }
            cont += ";\n";
            actual = actual.siguiente;
        }
        return cont;
    }
    
    private String cabeceraColumna(){
        String cont = "";
        Encabezado actual = encabezadoColumna.primero;
        cont = "Matriz -> \"Destino: \n" + ((Destino)actual.dato).getNombre() + "\";\n";
        while(actual != null){
            cont += "\"Destino: \n" + ((Destino)actual.dato).getNombre() + "\"";
            if(actual.siguiente != null){
                cont += " -> ";
                cont += "\"Destino: \n" + ((Destino)actual.siguiente.dato).getNombre() + "\"";
            }
            cont += ";\n";
            actual = actual.siguiente;
        }
        return cont;
    }
    
    
    private String cabeceraFila(){
        String cont = "";
        Encabezado actual = encabezadoFila.primero;
        cont = "Matriz -> \"Origen: \n" + ((Destino)actual.dato).getNombre() + "\";\n";
        while(actual != null){
            cont += "\"Origen: \n" + ((Destino)actual.dato).getNombre() + "\"";
            if(actual.siguiente != null){
                cont += " -> ";
                cont += "\"Origen: \n" + ((Destino)actual.siguiente.dato).getNombre() + "\"";
            }
            cont += ";\n";
            actual = actual.siguiente;
        }
        return cont;
    }
    
    public void grafoAD(){
        try {
            cadena = "";
            File archivo = new File("C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\estructuras\\GrafoAD.dot");
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            cadena = "digraph g{\n";
            cadena += Rutas();
            cadena += "}";
            
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(cadena);
            bw.close();
            
            
            String[] cmd = new String[5];
            cmd[0] = "dot.exe";
            cmd[1] = "-Tpng";
            cmd[2] = "C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\GrafoAD.dot";
            cmd[3] = "-o";
            cmd[4] = "C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\GrafoAD.png";
            
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            
        } catch (IOException e) {
            System.out.println("error: " + e);
        }
    }
    
    private String Rutas(){
        String cont = "";
        Encabezado actual = encabezadoFila.primero;
        while(actual != null){
            if(actual.acceso != null){
                NodoM tempo = actual.acceso;
                while(tempo != null){
                    cont += "\"" + ((Vuelo)tempo.dato).getPunto1() + "\" -> \"" + ((Vuelo)tempo.dato).getPunto2() + "\";\n";
                    tempo = tempo.derecha;
                }
            }
            actual = actual.siguiente;
        }
        return cont;
    }
    
    public void Ruta(String origen, String destino){
        if(encabezadoFila.primero != null){
            Encabezado actualfila = encabezadoFila.primero;
            while(actualfila != null){
                if(((Destino)actualfila.dato).getNombre().equals(origen)){
                    if(encabezadoColumna.primero != null){
                        Encabezado actualcolumna = encabezadoColumna.primero;
                        for(int i = 0; i < misrutas.length; i++){
                            misrutas[i] = origen;
                        }
                        while(actualcolumna != null){
                            if(((Destino)actualcolumna.dato).getNombre().equals(destino)){
                                int j = 0;
                                NodoM nodo = actualcolumna.acceso;
                                while(nodo != null){
                                    misrutas[j] += ","+destino;
                                    j++;
                                    nodo = nodo.abajo;
                                }
                                break;
                            }else if(!((Destino)actualcolumna.dato).getNombre().equals(destino)){
                                Ruta(actualcolumna, destino);
                            }
                            actualcolumna = actualcolumna.siguiente;
                        }
                    }
                    break;
                }
                actualfila = actualfila.siguiente;
            }
        }
        
        procesoReserva(miRutaObtenida());
        
    }
    
    private void Ruta(Encabezado origen, String destino){
        int i = 0;
        contador++;
        if(!((Destino)origen.dato).getNombre().equals(destino) && contador < 6){
            NodoM actual = origen.acceso;
            while(actual != null){
                misrutas[i] += "," + ((Destino)origen.dato).getNombre();
                i++;
                actual = actual.abajo;
            }
        }
    }
    
    public void imprimerRuta(){
        int i = 0;
        for(String mis : misrutas){
            i++;
            System.out.println("ruta"+i+": "+mis);
        }
    }
    
    
    public String miRutaObtenida(){
        return misrutas[1];
    }
    
    public int precioxPais(String pais1, String pais2){
        if(encabezadoFila.primero != null){
            Encabezado actual = encabezadoFila.primero;
            while(actual != null){
                if(((Destino)actual.dato).getNombre().equals(pais1)){
                    if(actual.acceso != null){
                        NodoM tmp = actual.acceso;
                        while(tmp != null){
                            if(((Vuelo)tmp.dato).getPunto1().equals(pais2)){
                                return ((Vuelo)tmp.dato).getCosto();
                            }
                            tmp = tmp.derecha;
                        }
                    }
                    break;
                }
                actual = actual.siguiente;
            }
        }
        return 0;
    }
    
    public int tiempoxPais(String pais1, String pais2){
        if(encabezadoFila.primero != null){
            Encabezado actual = encabezadoFila.primero;
            while(actual != null){
                if(((Destino)actual.dato).getNombre().equals(pais1)){
                    if(actual.acceso != null){
                        NodoM tmp = actual.acceso;
                        while(tmp != null){
                            if(((Vuelo)tmp.dato).getPunto1().equals(pais2)){
                                return ((Vuelo)tmp.dato).getTiempo();
                            }
                            tmp = tmp.derecha;
                        }
                    }
                    break;
                }
                actual = actual.siguiente;
            }
        }
        return 0;
    }
    
    public Reserva procesoReserva(String rut){
        String[] paises = rut.split(",");
        Reserva mireserva =  null;
        int precio = 0;
        int tiemp = 0;
        for(int i=0; i < paises.length-1; i++){
            precio += precioxPais(paises[1],paises[i+1]);
        }
        
        for(int i=0; i < paises.length-1; i++){
            tiemp += tiempoxPais(paises[1],paises[i+1]);
        }
        
        Random no = new Random();
        int val = 1000 + no.nextInt((9999+1)-1000);
        
        mireserva = new Reserva(val, precio, tiemp, "cliente"+val);
        return mireserva;
    }
    
    public void Cliente(String cl){
        clientetmp = cl; 
    }
}
