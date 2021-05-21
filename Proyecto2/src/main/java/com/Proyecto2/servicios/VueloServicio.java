/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.servicios;

import com.Proyecto2.estructuras.MatrizAD;
import com.Proyecto2.estructuras.Vuelo;
import com.Proyecto2.modelo.Destino;
import com.Proyecto2.modelo.Reserva;
import java.util.List;
import javax.ws.rs.Consumes;
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
@Path("Vuelo")
public class VueloServicio {

    static MatrizAD mat = new MatrizAD();

    @POST
    @Path("/newdestino")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response CargarDestino(Destino destino) {
        System.out.println("ingresando destino");
        try {
            mat.InsertarCabecera(destino);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/newRuta")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response CargarRuta(Vuelo vuelo) {
        System.out.println("cargando ruta");
        try {
            mat.Insertar(vuelo, vuelo.getPunto1(), vuelo.getPunto2());
            return Response.ok("true").build();
        } catch (Exception e) {
            return Response.ok("false").build();
        }
    }

    @GET
    @Path("/generarMAD")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GenerarMAD() {
        System.out.println("generando matriz de adyacencia");
        mat.graficaMatrizAD();
        return Response.ok("true").build();
    }

    @GET
    @Path("/grafoAD")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GrafoAD() {
        System.out.println("generando grafo de adyacencia");
        try {
            mat.grafoAD();
            return Response.ok("true").build();
        } catch (Exception e) {
            return Response.ok("false").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDestinoss() {
        System.out.println("Mostrando todos los Destinos");
        mat.temp.clear();
        mat.setDestinos();
        List<Destino> lista = mat.getDestionos();
        return Response.ok(lista).build();
    }
    
    @GET
    @Path("/Reservar/{origen}/{destino}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Log(@PathParam("origen") String origen, @PathParam("destino") String destino){
        System.out.println("realizando reservacion");
        mat.Ruta(origen, destino);
        String rutaobt = mat.miRutaObtenida();
        Reserva res = mat.procesoReserva(rutaobt);
        if(res != null){
            return Response.ok(res).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
