package com.quincy;

import com.quincy.jedis.JedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by quincy on 18/2/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-dao.xml")
public class DemoTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JedisClient jedisClient;

    @Test
    public void test(){

        try {
            for(int i = 0;i < 100000; i ++){
                Connection connection = dataSource.getConnection();
                connection.close();
                System.out.println(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void redis(){
//        int a = 0;
//        do{
//            a ++;
//            jedisClient.setex(String.valueOf(a),100,String.valueOf(a));
//
//        }while (a < 100000);

        String value = jedisClient.get("17400");
        System.out.println(value);
        System.out.println(value == null);
    }

    @Test
    public void random(){

          String str = "null";
        byte[] bytes = str.getBytes();
        for (byte b:bytes) {
            System.out.println((int)b);
        }
        System.out.println();
//        for(int i = 0;i < 1000000; i ++){
//            int randomId = (int) (Math.random() * 30000);
//            System.out.println(randomId);
//
//            String strValue = String.valueOf(randomId);
//            jedisClient.setex(strValue,1,"1");
//        }
    }
}
