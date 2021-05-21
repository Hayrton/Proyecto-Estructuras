/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.estructuras;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Hayrton
 * @param <T>
 */
public class Arbol<T> {
    private int orden;
    private Pagina raiz;

    public Arbol() {
        this.orden = 5;
    }

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * @return the raiz
     */
    public Pagina getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(Pagina raiz) {
        this.raiz = raiz;
    }
    
     private boolean buscarNodo(Pagina actual, T valor, Logical2 l) {
        int index;
        boolean encontrado;
        Comparador dato = (Comparador) valor;
        if (dato.esMenor(actual.getClave(1))) {
            encontrado = false;
            index = 0;
        } else {
            index = actual.getCuenta();
            while (dato.esMenor(actual.getClave(index)) && index > 1) {
                index--;
            }
            encontrado = dato.esIgual(actual.getClave(index));
        }
        l.setLogicalIndex(index);
        return encontrado;
    }

    public T buscarElemento(T buscado) {
        Logical2 l = new Logical2();
        Pagina resultado = buscar(buscado, l);
        if (resultado != null) {
            for (int i = 1; i <= resultado.getCuenta(); i++) {
                Comparador dato = (Comparador) resultado.getClave(i);
                if (dato.esIgual(buscado)) {
                    return (T) resultado.getClave(i);
                }
            }
        }
        return null;
    }

    protected Pagina buscar(T valor, Logical2 l) {
        return buscar(raiz, valor, l);
    }

    private Pagina buscar(Pagina actual, T valor, Logical2 l) {
        if (actual == null) {
            return null;
        } else {
            boolean esta = buscarNodo(actual, valor, l);
            if (esta) {
                return actual;
            } else {
                return buscar(actual.getRama(l.getLogicalIndex()), valor, l);
            }
        }
    }

    public void insertar(T valor) {
        raiz = insertar(raiz, valor);
    }

    Pagina insertar(Pagina raiz, T valor) {
        boolean sube;
        Logical2 mediana = new Logical2();
        Logical2 nd = new Logical2();
        sube = empujar(raiz, valor, mediana, nd);
        if (sube) {
            Pagina p;
            p = new Pagina();
            p.setCuenta(1);
            p.setClave(1, mediana.getLogicalMediana());
            p.setRama(0, raiz);
            p.setRama(1, nd.getLogicalNd());
            raiz = p;
        }
        return raiz;
    }

    private boolean empujar(Pagina actual, T cl, Logical2 mediana, Logical2 nuevo) {
        Logical2 k = new Logical2(0);
        boolean sube = false;
        if (actual == null) {
            sube = true;
            mediana.setLogicalMediana(cl);
            nuevo.setLogialNd(null);
        } else {
            boolean esta;
            esta = buscarNodo(actual, cl, k);
            if (esta) { //No se aceptan claves duplicadas
                System.out.println("Clave duplicada");
                //Venta v = (Venta) cl;
                //new LogFile().escribirArchivo("ERROR", "ArbolB", v.getNumeroFactura() + " Repetido");
                return false;
            }
            sube = empujar(actual.getRama(k.getLogicalIndex()), cl, mediana, nuevo);
            if (sube) {

                if (actual.nodoLleno()) { //se debe dividir la pagina
                    dividirNodo(actual, mediana, nuevo, k);
                } else {
                    sube = false;
                    meterPagina(actual, mediana, nuevo, k);
                }

            }
        }
        return sube;
    }

    private void meterPagina(Pagina actual, Logical2 cl, Logical2 ramaDr, Logical2 k) {
        //Hace un corrimiento a la derecha para hacer un espacio
        for (int i = actual.getCuenta(); i >= k.getLogicalIndex(); i--) {
            actual.setClave(i + 1, actual.getClave(i));
            actual.setRama(i + 1, actual.getRama(i));
        }
        //pone la clave y la rama derecha en la posicion k+1
        actual.setClave(k.getLogicalIndex() + 1, cl.getLogicalMediana());

        actual.setRama(k.getLogicalIndex() + 1, ramaDr.getLogicalNd());
        //incrementa el contador de claves 
        actual.setCuenta((actual.getCuenta() + 1));
    }

    private void dividirNodo(Pagina actual, Logical2 mediana, Logical2 nuevo, Logical2 pos) {
        int i, posMdna, k;
        Pagina nuevaPag;
        k = pos.getLogicalIndex();
        posMdna = (k <= orden / 2) ? orden / 2 : orden / 2 + 1;
        nuevaPag = new Pagina();
        for (i = posMdna + 1; i < orden; i++) {
            //se desplaza la mitad derecha a la nueva pagina, la clave mediana se queda en pagina actual
            nuevaPag.setClave(i - posMdna, actual.getClave(i));
            nuevaPag.setRama(i - posMdna, actual.getRama(i));

            //		codigo de prueba 
            /*Es necesario eliminar los elementos que se trasladan a la nueva pagina*/
            actual.setClave(i, null);
            actual.setRama(i, null);

        }

        nuevaPag.setCuenta((orden - 1) - posMdna);//claves de nueva pagina
        actual.setCuenta(posMdna); // claves en pagina origen
        //inserta la clave y rama en la painga que le corresponde
        if (k <= orden / 2) {
            meterPagina(actual, mediana, nuevo, pos); //Pagina origen 
        } else {
            pos.setLogicalIndex((k - posMdna));
            meterPagina(nuevaPag, mediana, nuevo, pos); //pagina nueva	
        }
        //extrae clave mediana de la pagina origen 
        mediana.setLogicalMediana(actual.getClave(actual.getCuenta()));

        actual.setClave(actual.getCuenta(), null);

        //rama0 del nuevo nodo es la rama de la mediana
        nuevaPag.setRama(0, actual.getRama(actual.getCuenta()));
        actual.setRama(actual.getCuenta(), null);
        /*Se elimina la clave mediana de la pagina actual*/
        actual.setCuenta((actual.getCuenta() - 1));
        /*Se elimina la rama referencia de mediana de la pagina*/
        nuevo.setLogialNd(nuevaPag);
    }

    public void recorridoInorden() {
        System.out.println("RECORRIDO INORDEN");
        recorridoInorden(raiz);
    }

    private void recorridoInorden(Pagina raiz) {
        AdminObject dato;
        if (raiz != null) {
            recorridoInorden(raiz.getRama(0));
            for (int k = 1; k <= raiz.getCuenta(); k++) {
                System.out.printf("Rama numero %d ", k);
                dato = (AdminObject) raiz.getClave(k);
                System.out.println(dato.escribirObjetoConsole());
                recorridoInorden(raiz.getRama(k));
            }
        }
    }

    public boolean graficar() {
        try {
            File archivo = new File("C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\ArbolB.dot");
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            StringBuilder builder = new StringBuilder();
            builder.append(generarDotB());
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(builder.toString());
            bw.close();

            String[] cmd = new String[5];
            cmd[0] = "dot.exe";
            cmd[1] = "-Tpng";
            cmd[2] = "C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\ArbolB.dot";
            cmd[3] = "-o";
            cmd[4] = "C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\ArbolB.png";
            

            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            return true;
        } catch (IOException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    /*Usado para el reporte general*/
    public String toDot() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(escribirPagina(raiz));
        buffer.append(enlazarPaginas(raiz));
        buffer.append(ePaginas(raiz));
        return buffer.toString();
    }

    private String generarDotB() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("digraph arbolB{\n");
        buffer.append("node[shape=record];\n");
        buffer.append(escribirPagina(raiz));
        buffer.append(enlazarPaginas(raiz));
        buffer.append(ePaginas(raiz));
//        buffer.append(enlazarPaginas(raiz));
        buffer.append("}");
        return buffer.toString();
    }

    private String escribirPagina(Pagina raiz) {
        StringBuilder buffer = new StringBuilder();
        StringBuffer clusters = new StringBuffer();      
        AdminObject dato;
        //Factura dato;
        boolean flag = false;
        if (raiz == null) {
            return "";
        }
        buffer.append("pagina").append(raiz.hashCode()).append("[label=\"");
        for (int i = 1; i <= raiz.getCuenta(); i++) {
            if (raiz.getClave(i) == null) {
                continue; //Evita nullpointer Exception
            }
            dato = (AdminObject) raiz.getClave(i);
            if (flag) {
                buffer.append(" | ");
            }
            buffer.append("<p").append(dato.hashCode()).append(">");
            buffer.append(dato.escribirObjeto());
            flag = true;

//            Venta temporal = (Venta) raiz.getClave(i);
//            
//            if (temporal != null) {
//                if (temporal.getDetalle() != null) {
//                    String dotDetalle = temporal.getDetalle().toDot();
//                    if (!dotDetalle.isEmpty()) {
//                        clusters.append("subgraph cluster").append("p").append(dato.hashCode()).append("{\n");
//                        clusters.append(dotDetalle);
//                        clusters.append("label=\"Detalle\"\n");
//                        clusters.append("}\n");
//                        /*En este punto estoy fuera del cluster*/
//                        clusters.append("pagina").append(raiz.hashCode());
//                        clusters.append(":p").append(dato.hashCode());
//                        clusters.append("->nodo").append(temporal.getDetalle().getPrimero().hashCode());
//                        clusters.append("[label=\"Factura_").append(temporal.getNumeroFactura()).append("\"];\n");
//                    }
//                }
//
//            }            

        }
        buffer.append("\"];\n");

        /*Agregar aqui el cluster a la cadena principal*/
        int i = 0;
        while (i <= raiz.getCuenta()) {
            if (raiz.getRama(i) != null) {
                buffer.append(escribirPagina(raiz.getRama(i)));
            }
            i++;
        }
        buffer.append(clusters);
        return buffer.toString();
    }

    private String ePaginas(Pagina raiz) {
        StringBuilder buffer = new StringBuilder();

        if (raiz == null) {
            return "";
        }
        int cont = 0;

        while (cont < orden) {
            buffer.append(enlazarPaginas(raiz.getRama(cont)));
            buffer.append(ePaginas(raiz.getRama(cont)));
            cont++;
        }

        return buffer.toString();
    }

    private String enlazarPaginas(Pagina raiz) {
        if (raiz == null) {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        int contador = 0;
        while (contador < orden) {
            if (raiz.getRama(contador) != null) {
                buffer.append("pagina").append(raiz.hashCode());
                buffer.append(" -> ");
                buffer.append("pagina").append(raiz.getRama(contador).hashCode());
                buffer.append("[label=\"rama").append(contador).append("\"];").append("\n");
            }
            contador++;
        }
        return buffer.toString();
    }
    
    
}


class Logical2<T> {

    boolean v;
    int index;
    Pagina nd;
    T mediana;    

    public Logical2() {
        // TODO Auto-generated constructor stub		
    }

    public Logical2(boolean v) {
        this.v = v;
    }

    public Logical2(int i) {
        this.index = i;
    }

    public Logical2(Pagina nd) {
        this.nd = nd;
    }

    public Logical2(T mediana) {
        this.mediana = mediana;
    }

    public void setLogicalMediana(T m) {
        this.mediana = m;
    }

    public T getLogicalMediana() {
        return this.mediana;
    }

    public void setLogialNd(Pagina nd) {
        this.nd = nd;
    }

    public Pagina getLogicalNd() {
        return this.nd;
    }

    public void setLogicalBoolean(boolean v) {
        this.v = v;
    }

    public boolean getLogicalBoolean() {
        return v;
    }

    public void setLogicalIndex(int i) {
        this.index = i;
    }

    public int getLogicalIndex() {
        return this.index;
    }

}

