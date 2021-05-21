/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.servicios;

import com.Proyecto2.estructuras.Hash;
import com.Proyecto2.modelo.Reserva;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Hayrton
 */
@Path("Reserva")
public class ReservaServicio {
    static Hash hash = new Hash();
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response InsertUser(Reserva reserva){
        try {
            System.out.println("insertando reserva");
            hash.Insertar(reserva);
            return Response.status(Response.Status.CREATED).entity(reserva).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/tablahash")
    @Produces(MediaType.APPLICATION_JSON)
    public Response graficaArbolB() {
        System.out.println("generando imagen de arbol B");
        try {
            hash.Graficar();
            return Response.ok("true").build();
        } catch (Exception e) {
            return Response.ok("false").build();
        }
    }
}
