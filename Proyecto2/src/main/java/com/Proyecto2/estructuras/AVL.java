/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.estructuras;

import com.Proyecto2.modelo.Usuarios;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hayrton
 */
public class AVL {

    static NodoAVL Raiz;
    public static List<Usuarios> temp;

    public AVL() {
        Raiz = null;
        temp = new ArrayList();
    }

    public NodoAVL raizArbol() {
        return Raiz;
    }

    private NodoAVL rotacionII(NodoAVL n, NodoAVL n1) {
        n.ramaIzq(n.subarbolDer());
        n1.ramaDer(n);
        if (n1.fe == -1) {
            n.fe = 0;
            n1.fe = 0;
        } else {
            n.fe = -1;
            n1.fe = 1;
        }
        return n1;
    }

    private NodoAVL rotacionDD(NodoAVL n, NodoAVL n1) {
        n.ramaDer(n1.subarbolIzq());
        n1.ramaIzq(n);
        if (n1.fe == +1) {
            n.fe = 0;
            n1.fe = 0;
        } else {
            n.fe = +1;
            n1.fe = -1;
        }
        return n1;
    }

    private NodoAVL rotacionID(NodoAVL n, NodoAVL n1) {
        NodoAVL n2;
        n2 = (NodoAVL) n1.subarbolDer();
        n.ramaIzq(n2.subarbolDer());
        n2.ramaDer(n);
        n1.ramaDer(n2.subarbolIzq());
        n2.ramaIzq(n1);
        if (n2.fe == +1) {
            n1.fe = -1;
        } else {
            n1.fe = 0;
        }
        if (n2.fe == -1) {
            n.fe = 1;
        } else {
            n.fe = 0;
        }
        n2.fe = 0;
        return n2;
    }

    private NodoAVL rotacionDI(NodoAVL n, NodoAVL n1) {
        NodoAVL n2;
        n2 = (NodoAVL) n1.subarbolIzq();
        n.ramaDer(n2.subarbolIzq());
        n2.ramaIzq(n);
        n1.ramaIzq(n2.subarbolDer());
        n2.ramaDer(n1);
        if (n2.fe == +1) {
            n.fe = -1;
        } else {
            n.fe = 0;
        }
        if (n2.fe == -1) {
            n1.fe = 1;
        } else {
            n1.fe = 0;
        }
        n2.fe = 0;
        return n2;
    }

    public void insertar(Usuarios user) throws Exception {
        Logical h = new Logical(false);
        Raiz = insertarAVL(Raiz, user, h);
    }

    private NodoAVL insertarAVL(NodoAVL raiz, Usuarios user, Logical h) throws Exception {
        NodoAVL n1;
        if (raiz == null) {
            raiz = new NodoAVL(user);
            h.setLogical(true);
        } else if (user.getId().compareTo(raiz.valorNodo()) < 0) {
            NodoAVL iz;
            iz = insertarAVL((NodoAVL) raiz.subarbolIzq(), user, h);
            raiz.ramaIzq(iz);
            if (h.booleanValue()) {
                switch (raiz.fe) {
                    case 1:
                        raiz.fe = 0;
                        h.setLogical(false);
                        break;
                    case 0:
                        raiz.fe = -1;
                        break;
                    case -1:
                        n1 = (NodoAVL) raiz.subarbolIzq();
                        if (n1.fe == -1) {
                            raiz = rotacionII(raiz, n1);
                        } else {
                            raiz = rotacionID(raiz, n1);
                        }
                        h.setLogical(false);
                }
            }
        } else if (user.getId().compareTo(raiz.valorNodo()) > 0) {
            NodoAVL dr;
            dr = insertarAVL((NodoAVL) raiz.subarbolDer(), user, h);
            raiz.ramaDer(dr);
            if (h.booleanValue()) {
                switch (raiz.fe) {
                    case 1:
                        n1 = (NodoAVL) raiz.subarbolDer();
                        if (n1.fe == +1) {
                            raiz = rotacionDD(raiz, n1);
                        } else {
                            raiz = rotacionDI(raiz, n1);
                        }
                        h.setLogical(false);
                        break;
                    case 0:
                        raiz.fe = +1;
                        break;
                    case -1:
                        raiz.fe = 0;
                        h.setLogical(false);
                }
            }
        } else {
            throw new Exception("No puede haber calves repetidas");
        }
        return raiz;
    }

    public void InOrden() {
        temp.clear();
        InOrden(Raiz);
    }

    private void InOrden(NodoAVL raiz) {
        if (raiz != null) {
            InOrden(raiz.izq);
            //System.out.println(raiz.dato + " ");
            temp.add(raiz.user);
            InOrden(raiz.der);
        }
    }

    public List<Usuarios> getUsuarios() {
        return temp;
    }

    public static Usuarios Buscar(String id) {
        if (Raiz == null) {
            return null;
        } else {
            return Buscar(Raiz, id);
        }
    }

    public static Usuarios Buscar(NodoAVL raiz, String id) {
        Usuarios user = null;
        if (raiz == null) {
            return user;
        }
        if (id.compareTo(raiz.valorNodo()) < 0) {
            user = Buscar(raiz.izq, id);
        } else if (id.compareTo(raiz.valorNodo()) > 0) {
            user = Buscar(raiz.der, id);
        } else {
            user = raiz.user;
        }
        return user;
    }

    public void Eliminar(String id) throws Exception {
        Logical flag = new Logical(false);
        Raiz = BorrarAVL(Raiz, id, flag);
    }

    private NodoAVL BorrarAVL(NodoAVL r, String id, Logical cambiarAltura) throws Exception {
        if (r == null) {
            throw new Exception(" Nodo no encontrado ");
        } else if (id.compareTo(r.valorNodo()) < 0) {
            NodoAVL iz;
            iz = BorrarAVL((NodoAVL) r.subarbolIzq(), id, cambiarAltura);
            r.ramaIzq(iz);
            if (cambiarAltura.booleanValue()) {
                r = equilibrar1(r, cambiarAltura);
            }
        } else if (id.compareTo(r.valorNodo()) > 0) {
            NodoAVL dr;
            dr = BorrarAVL((NodoAVL) r.subarbolDer(), id, cambiarAltura);
            r.ramaDer(dr);
            if (cambiarAltura.booleanValue()) {
                r = equilibrar2(r, cambiarAltura);
            }
        } else // Nodo encontrado
        {
            NodoAVL q;
            q = r; // nodo a quitar del árbol
            if (q.subarbolIzq() == null) {
                r = (NodoAVL) q.subarbolDer();
                cambiarAltura.setLogical(true);
            } else if (q.subarbolDer() == null) {
                r = (NodoAVL) q.subarbolIzq();
                cambiarAltura.setLogical(true);
            } else {
                NodoAVL iz;
                iz = reemplazar(r, (NodoAVL) r.subarbolIzq(), cambiarAltura);
                r.ramaIzq(iz);
                if (cambiarAltura.booleanValue()) {
                    r = equilibrar1(r, cambiarAltura);
                }
            }
            q = null;
        }
        return r;
    }

    private NodoAVL equilibrar1(NodoAVL n, Logical cambiaAltura) {
        NodoAVL n1;
        switch (n.fe) {
            case -1:
                n.fe = 0;
                break;
            case 0:
                n.fe = 1;
                cambiaAltura.setLogical(false);
                break;
            case +1: //se aplicar un tipo de rotación derecha
                n1 = (NodoAVL) n.subarbolDer();
                if (n1.fe >= 0) {
                    if (n1.fe == 0) //la altura no vuelve a disminuir
                    {
                        cambiaAltura.setLogical(false);
                    }
                    n = rotacionDD(n, n1);
                } else {
                    n = rotacionDI(n, n1);
                }
                break;
        }
        return n;
    }

    private NodoAVL equilibrar2(NodoAVL n, Logical cambiaAltura) {
        NodoAVL n1;
        switch (n.fe) {
            case -1: // Se aplica un tipo de rotación izquierda
                n1 = (NodoAVL) n.subarbolIzq();
                if (n1.fe <= 0) {
                    if (n1.fe == 0) {
                        cambiaAltura.setLogical(false);
                    }
                    n = rotacionII(n, n1);
                } else {
                    n = rotacionID(n, n1);
                }
                break;
            case 0:
                n.fe = -1;
                cambiaAltura.setLogical(false);
                break;
            case +1:
                n.fe = 0;
                break;
        }
        return n;
    }

    private NodoAVL reemplazar(NodoAVL n, NodoAVL act, Logical cambiaAltura) {
        if (act.subarbolDer() != null) {
            NodoAVL d;
            d = reemplazar(n, (NodoAVL) act.subarbolDer(), cambiaAltura);
            act.ramaDer(d);
            if (cambiaAltura.booleanValue()) {
                act = equilibrar2(act, cambiaAltura);
            }
        } else {
            n.nuevoValor(act.user);
            n = act;
            act = (NodoAVL) act.subarbolIzq();
            n = null;
            cambiaAltura.setLogical(true);
        }
        return act;
    }

    public static boolean Validar(String id, String pass) {
        if (Raiz == null) {
            return false;
        } else {
            return Validar(Raiz, id, pass);
        }
    }

    public static boolean Validar(NodoAVL raiz, String id, String pass) {
        boolean r = false;
        if (raiz == null) {
            return r;
        }
        if (id.compareTo(raiz.valorNodo()) < 0) {
            r = Validar(raiz.izq, id, pass);
        } else if (id.compareTo(raiz.valorNodo()) > 0) {
            r = Validar(raiz.der, id, pass);
        } else {
            if (raiz.user.getPassword().equals(pass)) {
                r = true;
            }
            r = false;
        }
        return r;
    }

    public boolean insertarReserva(String id, String reservacion) {
        if (Raiz == null) {
            return false;
        } else {
            return insertarReserva(Raiz, id, reservacion);
        }
    }

    private boolean insertarReserva(NodoAVL raiz, String id, String reservacion) {
        boolean r = false;
        if (raiz == null) {
            return r;
        }
        if (id.compareTo(raiz.valorNodo()) < 0) {
            r = insertarReserva(raiz.izq, id, reservacion);
        } else if (id.compareTo(raiz.valorNodo()) > 0) {
            r = insertarReserva(raiz.der, id, reservacion);
        } else {
            raiz.milista.insertar(reservacion);
            r = true;
        }
        return r;
    }

    public List<String> getReservas(String id) {
        if (Raiz == null) {
            return null;
        } else {
            return getReservas(Raiz, id);
        }
    }

    private List<String> getReservas(NodoAVL raiz, String id) {
        List<String> r = null;
        if (raiz == null) {
            return r;
        }
        if (id.compareTo(raiz.valorNodo()) < 0) {
            r = getReservas(raiz.izq, id);
        } else if (id.compareTo(raiz.valorNodo()) > 0) {
            r = getReservas(raiz.der, id);
        } else {
            r = raiz.milista.miReservas();
        }
        return r;
    }

    public boolean GraficarAVL() {
        try {
            File archivo = new File("C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\grafoavl.dot");
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
            cmd[2] = "C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\grafoavl.dot";
            cmd[3] = "-o";
            cmd[4] = "C:\\Users\\Hayrton\\Proyecto2\\src\\main\\java\\com\\Proyecto2\\Grafos\\grafoavl.png";

            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private String generarDot() {
        StringBuilder builder = new StringBuilder();
        builder.append("digraph avl{\n");
        builder.append("graph[overlap=true];\n");
        builder.append("node[shape=box];\n");
        builder.append(nodosavl(Raiz));
        builder.append("}\n");
        return builder.toString();
    }

    private String nodosavl(NodoAVL raiz) {
        StringBuilder builder = new StringBuilder();
        if (raiz == null) {
            return null;
        }
        
        builder.append("nodo").append(raiz.hashCode()).append("[label=\"fe: ").append(raiz.fe).append("\\n");
        builder.append(raiz.user.imprimeUsuario()).append("\"]\n;");
        
        if(raiz.izq != null){
            builder.append("nodo").append(raiz.izq.hashCode()).append("[label=\"fe: ").append(raiz.izq.fe).append("\\n");
            builder.append(raiz.der.user.imprimeUsuario()).append("\"]\n;");
            
            builder.append("nodo").append(raiz.hashCode()).append(" -> nodo").append(raiz.izq.hashCode());
            builder.append("[label=\"izq\"];\n");
            builder.append(nodosavl(raiz.izq));
            
        }
        
        if(raiz.der != null){
            builder.append("nodo").append(raiz.der.hashCode()).append("[label=\"fe: ").append(raiz.der.fe).append("\\n");
            builder.append(raiz.der.user.imprimeUsuario()).append("\"]\n;");
            
            builder.append("nodo").append(raiz.hashCode()).append(" -> nodo").append(raiz.der.hashCode());
            builder.append("[label=\"der\"];\n");
            builder.append(nodosavl(raiz.der));
        }
        return builder.toString();
    }


}
