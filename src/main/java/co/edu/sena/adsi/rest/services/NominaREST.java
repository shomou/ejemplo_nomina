/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entities.Nomina;
import co.edu.sena.adsi.jpa.sessions.NominaFacade;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;


/**
 *
 * @author jperez
 */
@Path("nominas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NominaREST {
    
    @EJB
    private NominaFacade nominaEJB;
    
    @GET
    public List<Nomina> findAll(){
        return nominaEJB.findAll();
    } 
    
    @GET
    @Path("{id}")
    public Nomina findById(@PathParam("id") Integer id ){
        return nominaEJB.find(id);
    }
    
    
    //Hacer un POST Sencillo
    @POST
    public void create (Nomina nomina){
        nominaEJB.create(nomina);
    }
    
}
