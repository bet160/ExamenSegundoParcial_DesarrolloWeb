/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;
import DAO.UsuarioDAO;
import java.sql.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import pojos.Usuario;

/**
 * REST Web Service
 *
 * @author BETO
 */
@Path("usuarios")
public class UsuarioWS {

    @Context
    private UriInfo context;
    
    private UsuarioDAO usuarioDAO;

    /**
     * Creates a new instance of UsuarioWS
     */
    public UsuarioWS() {
    }
    
    @POST
    @Path("registroUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario registroUsuario(
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("numeroCelular") String numeroCelular,
            @FormParam("fechaNacimiento") Date fechaNacimiento,
            @FormParam("password") String password){
        
        usuarioDAO = new UsuarioDAO();
       
        Usuario usuario = new Usuario(nombre, apellidoPaterno, apellidoMaterno, numeroCelular, fechaNacimiento, password);
        
        return usuarioDAO.registrarUsuario(usuario);
    }
    
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario loginUsuario(
            @FormParam("numeroCelular") String numeroCelular,
            @FormParam("password") String password) {
        
        System.out.println("El cel es: " + numeroCelular);
        System.out.println("El pass es: " + password);
        
        usuarioDAO = new UsuarioDAO();
        
        Usuario usuario = new Usuario();
        usuario.setCelular(numeroCelular);
        usuario.setPassword(password);
        
        return usuarioDAO.loginUsuario(usuario);
    }
}
