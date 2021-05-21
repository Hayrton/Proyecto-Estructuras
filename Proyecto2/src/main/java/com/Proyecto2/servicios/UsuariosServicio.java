/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.servicios;

import com.Proyecto2.estructuras.AVL;
import com.Proyecto2.modelo.Usuarios;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Hayrton
 */
@Path("Usuarios")
public class UsuariosServicio {
    static AVL users = new AVL();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarios(){
        System.out.println("Mostrando todos los usuarios");
        users.InOrden();
        List<Usuarios> lista = users.getUsuarios();
        return Response.ok(lista).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("id") String id){
        System.out.println("buscando usuario");
        Usuarios u = AVL.Buscar(id);
        if(u != null){
            return Response.ok(u).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response InsertUser(Usuarios user){
        try {
            System.out.println("insertando usuario");
            users.insertar(user);
            return Response.ok("true").build();
        } catch (Exception e) {
            return Response.ok("false").build();
        }
    }
    
    @DELETE
    @Path("/borraruser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response BorrarUser(@PathParam("id") String id){
        try {
            System.out.println("id a eliminar ------> " +  id);
            users.Eliminar(id);
            System.out.println("se debio haber borrado");
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/autenticacion/{id}/{pass}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Autenticacion(@PathParam("id") String id, @PathParam("pass") String pass){
        Usuarios r = AVL.Buscar(id);
        
        if(r != null){
            System.out.println("validando usuario");
            if(r.getPassword().equals(pass))
                return Response.ok("true").build();
        }
        return Response.ok("false").build();
    }
    
    @GET
    @Path("/arbolavl")
    @Produces(MediaType.APPLICATION_JSON)
    public Response graficaArbolB() {
        System.out.println("generando imagen de arbol B");
        try {
            users.GraficarAVL();
            return Response.ok("true").build();
        } catch (Exception e) {
            return Response.ok("false").build();
        }
    }
    
}