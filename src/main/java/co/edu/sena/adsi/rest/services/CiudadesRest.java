/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.rest.services;


import co.edu.sena.adsi.jpa.entities.Ciudad;
import co.edu.sena.adsi.jpa.sessions.CiudadFacade;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ejb.EJB;

/**
 *
 * @author jperez
 */

@Path("ciudades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CiudadesRest {
    
    @EJB
    private CiudadFacade ciudadEJB;
    
    @GET
    public List<Ciudad> findAll(){
        try{
             return ciudadEJB.findAll(); 
        }
        catch(Exception e){
            System.out.println("ERROR: "+ e.toString());
            return null;
        }
       
    }
    
}
