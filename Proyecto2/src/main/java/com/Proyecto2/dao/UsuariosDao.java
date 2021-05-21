/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.dao;

import com.Proyecto2.estructuras.AVL;
import com.Proyecto2.modelo.Usuarios;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hayrton
 */
public class UsuariosDao {
    public static AVL usuarioss = new AVL();
    
    public static List<Usuarios> getUsuarios(){
        /*
        try {
            Usuarios user1 = new Usuarios("100","Omar","123");
            usuarioss.insertar(user1);
            Usuarios user2 = new Usuarios("104","Omar","123");
            usuarioss.insertar(user2);
            Usuarios user3 = new Usuarios("102","Omar","123");
            usuarioss.insertar(user3);
            Usuarios user4 = new Usuarios("108","Omar","123");
            usuarioss.insertar(user4);
            Usuarios user5 = new Usuarios("105","Omar","123");
            usuarioss.insertar(user5);
            
        } catch (Exception e) {
            System.out.println("error al ingresar los usurios");
        }*/
        usuarioss.InOrden();
        return usuarioss.getUsuarios();
    }
    
    public static void InsertarUser(Usuarios user){
        System.out.println("id ------> " + user.getId());
        try {
            System.out.println("esta insertando el usuario");
            usuarioss.insertar(user);
        } catch (Exception e) {
            System.out.println("error al insertar usuario");
        }
    }
}
