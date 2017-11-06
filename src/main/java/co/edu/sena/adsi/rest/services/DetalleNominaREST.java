/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entities.DetalleNomina;
import co.edu.sena.adsi.jpa.sessions.DetalleNominaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jperez
 */
@Path("detalle_nominas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DetalleNominaREST {
   @EJB
   private DetalleNominaFacade detalleNominaEJB;
   
   @GET
   public List<DetalleNomina> findAll(){
       return detalleNominaEJB.findAll();
      
   }

   @GET
   @Path("{id}")
   public DetalleNomina findById(@PathParam("id")Integer id){
       return detalleNominaEJB.find(id);
      
   }
   
   @POST
   public void create(DetalleNomina detalleNomina){
       detalleNominaEJB.create(detalleNomina);
   }
}
