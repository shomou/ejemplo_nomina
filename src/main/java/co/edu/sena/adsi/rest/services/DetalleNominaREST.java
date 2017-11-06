/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entities.DetalleNomina;
import co.edu.sena.adsi.jpa.entities.Nomina;
import co.edu.sena.adsi.jpa.entities.Usuario;
import co.edu.sena.adsi.jpa.sessions.DetalleNominaFacade;
import co.edu.sena.adsi.jpa.sessions.NominaFacade;
import co.edu.sena.adsi.jpa.sessions.UsuarioFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

    @EJB
    private UsuarioFacade usuarioEJB;

    @EJB
    private NominaFacade nominaEJB;

    @GET
    public List<DetalleNomina> findAll() {
        return detalleNominaEJB.findAll();

    }

    @GET
    @Path("{id}")
    public DetalleNomina findById(@PathParam("id") Integer id) {
        return detalleNominaEJB.find(id);

    }

    @POST
    public void create(@QueryParam("idUsuario") Integer idUsuario,
            @QueryParam("diasLaborados") int diasLaborados,
            @QueryParam("idNomina") Integer idNomina) {

        try {
            DetalleNomina newDetalleNomina = new DetalleNomina();

            //Consultar Usuario por Id
            Usuario empleado = usuarioEJB.find(idUsuario);

            //Consultar la Nómina --opcional
           // Nomina nomina = nominaEJB.find(idNomina);
            
            // Dias Laborados
            newDetalleNomina.setDiasLaborados(diasLaborados);

            //SueldoDevengado
            newDetalleNomina.setSueldoDevengado(calculaSueldoDevengado(empleado.getSueldoBasico(), diasLaborados));

            //Aux Transporte
            newDetalleNomina.setAuxilioTransporte(obtenerValorAuxTransporte(empleado.getSueldoBasico(), diasLaborados));

            //Valor HorasExtras
            newDetalleNomina.setValorHorasExtras(newDetalleNomina.getSueldoDevengado() * 0.1);

            //Total Devengado
            newDetalleNomina.setTotalDevengado(newDetalleNomina.getSueldoDevengado()
                    + newDetalleNomina.getAuxilioTransporte()
                    + newDetalleNomina.getValorHorasExtras());

            //Descuento Salud
            newDetalleNomina.setDescuentoSalud((newDetalleNomina.getTotalDescuento()
                    - newDetalleNomina.getAuxilioTransporte())
                    * (0.04));

            //Descuento a Pension
            newDetalleNomina.setDescuentoPension((newDetalleNomina.getTotalDescuento()
                    - newDetalleNomina.getAuxilioTransporte())
                    * (0.04));

            //Descuentos Adisionales
            newDetalleNomina.setOtrosDescuentos(300.00);

            //Descuento Fondo de Solidaridad
            newDetalleNomina.setDescuentoFondoSolidaridad(obtenerFondosSolidaridad(newDetalleNomina.getTotalDevengado(),
                    empleado.getSueldoBasico()));

            //Total Descuentso
            newDetalleNomina.setTotalDescuento(newDetalleNomina.getDescuentoSalud()
                    + newDetalleNomina.getDescuentoPension()
                    + newDetalleNomina.getDescuentoFondoSolidaridad()
                    + newDetalleNomina.getOtrosDescuentos());

            //Neto a pagar
            newDetalleNomina.setNetoPagar(newDetalleNomina.getTotalDevengado()
                    - newDetalleNomina.getTotalDescuento());

            newDetalleNomina.setEmpleado(empleado);

            newDetalleNomina.setNomina(new Nomina(idNomina));

            detalleNominaEJB.create(newDetalleNomina);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

//(sueldo_basico/30*dias laborados    
    private double calculaSueldoDevengado(double sueldoBasico, int diasLaborados) {
        return sueldoBasico / 30 * diasLaborados;
    }

    private double obtenerValorAuxTransporte(double sueldoBasico, int diasLaborados) {
        double auxTransporte = 0;

        if (sueldoBasico <= (sueldoBasico * 2)) {
            auxTransporte = 3500 / 30 * diasLaborados;
        } else {
            auxTransporte = 0;
        }
        return auxTransporte;
    }

    private double obtenerFondosSolidaridad(double totalDevengado, double sueldoBasico) {

        double fondoSolidaridad = 0;

        if (sueldoBasico >= (3500 * 4)) {
            fondoSolidaridad = totalDevengado + 0.01;

        } else {
            return fondoSolidaridad = 0;
        }
        return fondoSolidaridad;

    }
}
