package com.quincy.controller;

import com.quincy.MemcachedUtils;
import com.quincy.jedis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


/**
 * Created by quincy on 18/2/5.
 */
@RestController
public class DemoController {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private JedisClient jedisClient;

//    public static void main(String[] args) {
//
//        for(int i = 0; i < 1000000;i ++){
//            int random = (int)(Math.random() * 30000);
//            System.out.print(random +" ");
//            if (i % 200 == 0) System.out.println("＝＝＝＝");
//        }
//    }


    @RequestMapping(value = "/index.html",produces="text/html;charset=UTF-8")
    public void getData() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            //先从redis数据库中获取数据，获取不到从mysql数据库中获取数据
            connection = dataSource.getConnection();
            //产生一个随机的数字
            //int randomId = (int)(Math.random() * 30000);
            Integer randomId = (int) (Math.random() * 30000);

            String strValue = String.valueOf(randomId);

            //String strValue = randomId+"";
           // String strValue = randomId.toString();
                    //从redis数据库中获取数据
            String value = jedisClient.get(strValue);
           //Object value = MemcachedUtils.get(strValue);
            if(value == null){
                //String sql = "select id from demo where id = " + randomId;
                String sql = "select id from demo where id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,randomId);
                resultSet = preparedStatement.executeQuery();
                String id = null;
                if(resultSet.next()){
                    id = resultSet.getString("id");
                }
//                resultSet.close();
//                preparedStatement.close();
//                connection.close();
                //System.out.println(str);
                //存入到redis数据库 //60 1
                jedisClient.setex(strValue,60,id);
                //MemcachedUtils.set(strValue,strValue,new Date(1000*60));//10秒
                System.out.println("====  从数据库中获取到的数据");
                //return id + "====  从数据库中获取到的数据";
                return ;
            }

            System.out.println("====  从redis中获取数据");
           // return value;
        } catch (SQLException e) {
            e.printStackTrace();
//            try {
//                resultSet.close();
//                preparedStatement.close();
//                connection.close();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
       // return null;
    }
}
