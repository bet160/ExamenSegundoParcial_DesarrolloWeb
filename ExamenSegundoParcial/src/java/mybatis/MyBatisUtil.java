/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybatis;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author BETO
 */
public class MyBatisUtil {
    public static String RESOURCE = "mybatis/mybatis-config.xml";
    public static String ENVIRONMENT = "development";
    public static SqlSession getSession(){
        SqlSession session = null;
        try{
            Reader reader = Resources.getResourceAsReader(RESOURCE);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader,ENVIRONMENT);
            session =sqlMapper.openSession();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return session;
    }
}
