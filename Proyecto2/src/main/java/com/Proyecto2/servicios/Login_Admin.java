/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.servicios;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Hayrton
 */
@Path("Login")
public class Login_Admin {
    @GET
    @Path("/{id}/{pass}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Log(@PathParam("id") String id, @PathParam("pass") String pass){
        System.out.println("verficando autenticiadad de usurio");
        if(id.equals("admin") && pass.equals("201313875")){
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
