/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.Evento;

/**
 *
 * @author BETO
 */
public class EventoDAO {
    
     public EventoDAO() {
        
    }
    
    public Evento registrarEvento(Evento evento){      
        
        SqlSession conn = MyBatisUtil.getSession();

        try {
            conn.insert("Evento.registrarEvento", evento);
            conn.commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        
        return evento;
    }
    
    public List<Evento> obtenerEventosDeUsuario(int idUsuario){
         
        List<Evento> list = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            list = conn.selectList("Evento.getEventosUsuario", idUsuario);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        
        return list;
    }
    
}
