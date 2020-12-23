/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.sql.Date;


/**
 *
 * @author BETO
 */
public class Evento {
    
    private String descripcion;
    
    private Date fechaInicio;
    
    private Date fechaTermino;
    
    private String Lugar;
    
    private Integer Usuario_idUsuario;

    public Evento() {
    }

    public Evento(String descripcion, Date fechaInicio, Date fechaTermino, String Lugar, Integer Usuario_idUsuario) {
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.Lugar = Lugar;
        this.Usuario_idUsuario = Usuario_idUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String Lugar) {
        this.Lugar = Lugar;
    }

    public Integer getUsuario_idUsuario() {
        return Usuario_idUsuario;
    }

    public void setUsuario_idUsuario(Integer Usuario_idUsuario) {
        this.Usuario_idUsuario = Usuario_idUsuario;
    }

    
}
