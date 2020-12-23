/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.Usuario;

/**
 *
 * @author BETO
 */
public class UsuarioDAO {
    
    public UsuarioDAO() {
    }
    
    
    public Usuario registrarUsuario(Usuario usuario){
   
        SqlSession conn = MyBatisUtil.getSession();

        try {
            conn.insert("Usuario.registrarUsuario", usuario);
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        
        return usuario;
    }
    
    
    
    public Usuario loginUsuario(Usuario usuarioABuscar){
        
        Usuario usuario = null;
        SqlSession conn = MyBatisUtil.getSession();

        if (conn != null) {
            try {
                usuario = conn.selectOne("Usuario.loginUsuario", usuarioABuscar);
                
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                conn.close();
            }
        }
        
        return usuario;
    }
    
}
