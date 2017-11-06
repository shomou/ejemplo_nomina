/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entities.Usuario;
import co.edu.sena.adsi.jpa.sessions.UsuarioFacade;
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

@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuariosREST {
    @EJB
    private UsuarioFacade usuarioEJB;
    
    @GET
    public List<Usuario> findAll(){
        return usuarioEJB.findAll();
    }
    
    @GET
    @Path("{id}")
    public Usuario findById(@PathParam("id")Integer id){
        return usuarioEJB.find(id);
    
    }
    
    @POST
    public void create(Usuario usuario){
        usuarioEJB.create(usuario);
    }
    
    
}
