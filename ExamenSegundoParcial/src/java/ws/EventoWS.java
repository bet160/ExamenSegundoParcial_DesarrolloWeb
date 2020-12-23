/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import DAO.EventoDAO;
import java.sql.Date;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import pojos.Evento;

/**
 * REST Web Service
 *
 * @author BETO
 */
@Path("eventos")
public class EventoWS {

    @Context
    private UriInfo context;
     private EventoDAO eventoDAO;

    /**
     * Creates a new instance of EventoWS
     */
    public EventoWS() {
    }
    
    @Path("obtenerEventosDeUsuario/{Usuario_idUsuario}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evento> buscarEventos(
            @PathParam("Usuario_idUsuario") Integer Usuario_idUsuario) {
        
        eventoDAO = new EventoDAO();
        
        return eventoDAO.obtenerEventosDeUsuario(Usuario_idUsuario);
    }
    
    @POST
    @Path("registroEvento")
    @Produces(MediaType.APPLICATION_JSON)
    public Evento registroEvento(
            @FormParam("descripcion") String descripcion,
            @FormParam("fechaInicio") Date fechaInicio,
            @FormParam("fechaTermino") Date fechaTermino,
            @FormParam("lugar") String lugar,
            @FormParam("Usuario_idUsuario") Integer Usuario_idUsuario){
        
        eventoDAO = new EventoDAO();
        
        Evento evento = new Evento(descripcion,fechaInicio,fechaTermino,lugar,Usuario_idUsuario);
        
        return eventoDAO.registrarEvento(evento);
    }
}
