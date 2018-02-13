package com.quincy;


import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.schooner.MemCached.MemcachedItem;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Memcached 工具类
 * Created by quincy on 2017/5/9.
 */
public final class MemcachedUtils {
    private static MemCachedClient memCachedClient;

    static {
        /************** 配置 Memcached  ****************/
        System.out.println("/************** 配置 Memcached  ****************/");


        // 获取连接池的实例对象
        SockIOPool sockIOPool = SockIOPool.getInstance();
        // 服务器列表和其权重
        String[] servers = {"10.211.55.4:11211"};
        Integer[] weights = {3};

        sockIOPool.setServers(servers);//设置memcached服务器地址
        sockIOPool.setWeights(weights); //设置每个MemCached服务器权重

        sockIOPool.setAliveCheck(true);  //设置是否检查memcached服务器是否失效
        sockIOPool.setFailover(true);//当一个memcached服务器失效的时候是否去连接另一个memcached服务器.

        // 设置初始连接数、最小和最大连接数以及最大处理时间
        sockIOPool.setInitConn(10); //初始化时对每个服务器建立的连接数目
        sockIOPool.setMinConn(10);  //每个服务器建立最小的连接数
        sockIOPool.setMaxConn(100);  //每个服务器建立最大的连接数
        sockIOPool.setMaxIdle(1000 * 30 * 30);  // 设置最大处理时间


        // 设置主线程的睡眠时间
        sockIOPool.setMaintSleep(30);  //自查线程周期进行工作，其每次休眠时间

        // 设置TCP的参数，连接超时等
        sockIOPool.setNagle(false);   //Socket的参数，如果是true在写数据时不缓冲，立即发送出去。Tcp的规则是在发送一个包之前，包的发送方会等待远程接收方确认已收到上一次发送过来的包；这个方法就可以关闭套接字的缓存——包准备立即发出。
        sockIOPool.setSocketTO(3000);  //Socket阻塞读取数据的超时时间
        sockIOPool.setSocketConnectTO(0);   //连接建立时对超时的控制
        // 初始化连接池
        System.out.println(" 初始化连接池...");
        sockIOPool.initialize();

        if (memCachedClient == null) {
            memCachedClient = new MemCachedClient();
//            memCachedClient.setPrimitiveAsString(true);        //是否将基本类型转换为String方法
        }
    }

    /**
     * 保护型构造方法，不允许实例化！
     */
    private MemcachedUtils() {
    }

    /**
     * 添加指定对象到缓存中。
     * 注意：若key已经存在，旧value会被新value替换。
     *
     * @param key   键
     * @param value 值
     * @return boolean
     */
    public static boolean set(String key, Object value) {
        try {
            return memCachedClient.set(key, value);
        } catch (Exception e) {
            MemcachedLogUtils.writeLog("Memcached set方法报错，key值：" + key + "\r\n" + exceptionWrite(e));
            return false;
        }
    }

    /**
     * 添加指定对象到缓存中，并设置过期时间（如 new Date(1000*10) 表示10秒后过期）。
     * 注意：若key已经存在，旧value会被新value替换。
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间
     * @return boolean
     */
    public static boolean set(String key, Object value, Date expire) {
        try {
            return memCachedClient.set(key, value, expire);
        } catch (Exception e) {
            MemcachedLogUtils.writeLog("Memcached set方法报错，key值：" + key + "\r\n" + exceptionWrite(e));
            return false;
        }
    }

    /**
     * 根据key获取value值
     *
     * @param key 键
     * @return Object || null
     */
    public static Object get(String key) {
        try {
            return memCachedClient.get(key);
        } catch (Exception e) {
            MemcachedLogUtils.writeLog("Memcached get方法报错，key值：" + key + "\r\n" + exceptionWrite(e));
            return null;
        }
    }


    /**
     * 向缓存中添加键值对。
     * 注意: 当key不存在时，才能添加成功
     *
     * @param key   键
     * @param value 值
     * @return boolean
     */
    public static boolean add(String key, Object value) {
        try {
            if (get(key) != null) {
                MemcachedLogUtils.writeLog("Memcached add方法报错，key值：" + key + "\r\n" + exceptionWrite(new Exception("Memcached内存缓存中已经存在该键值对")));
                return false;
            }
            return memCachedClient.add(key, value);
        } catch (Exception e) {
            MemcachedLogUtils.writeLog("Memcached add方法报错，key值：" + key + "\r\n" + exceptionWrite(e));
            return false;
        }
    }

    /**
     * 向缓存添加键值对并为该键值对设定过期时间（比如： new Date(1000*10)，则表示十秒之后从Memcached内存缓存中删除）。
     * 注意：仅当缓存中不存在键时，才会添加成功。
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间
     * @return boolean
     */
    public static boolean add(String key, Object value, Date expire) {
        try {
            if (get(key) != null) {
                MemcachedLogUtils.writeLog("Memcached add方法报错，key值：" + key + "\r\n" + exceptionWrite(new Exception("Memcached内存缓存中已经存在该键值对")));
                return false;
            } else {
                return memCachedClient.add(key, value, expire);
            }
        } catch (Exception e) {
            MemcachedLogUtils.writeLog("Memcached add方法报错，key值：" + key + "\r\n" + exceptionWrite(e));
            return false;
        }
    }

    /**
     * 用新值替换已存在key的旧值
     * 注意：只有该键存在时，才会替换键相应的值。
     *
     * @param key      键
     * @param newValue 新的值
     * @return boolean
     */
    public static boolean replace(String key, Object newValue) {
        try {
            return memCachedClient.replace(key, newValue);
        } catch (Exception e) {
            MemcachedLogUtils.writeLog("Memcached replace方法报错，key值：" + key + "\r\n" + exceptionWrite(e));
            return false;
        }
    }

    /**
     * 用新值替换已存在key的旧值,并设置过期时间（比如： new Date(1000*10)，则表示十秒之后从Memcached内存缓存中删除）。
     * 注意：只有该键存在时，才会替换键相应的值。
     *
     * @param key      键
     * @param newValue 新的值
     * @param expire   过期时间
     * @return boolean
     */
    public static boolean replace(String key, Object newValue, Date expire) {
        try {
            return memCachedClient.replace(key, newValue, expire);
        } catch (Exception e) {
            MemcachedLogUtils.writeLog("Memcached replace方法报错，key值：" + key + "\r\n" + exceptionWrite(e));
            return false;
        }
    }

    /**
     * 根据键删除memcached中的键/值对
     *
     * @param key 键
     * @return boolean
     */
    public static boolean delete(String key) {
        try {
            return memCachedClient.delete(key);
        } catch (Exception e) {
            MemcachedLogUtils.writeLog("Memcached delete方法报错，key值：" + key + "\r\n" + exceptionWrite(e));
            return false;
        }
    }

    /**
     * 向已经存在的key的value后面追加数据
     *
     * @param key         已经存在的键
     * @param appendValue 追加的数据
     * @return boolean
     */
    public static boolean append(String key, Object appendValue) {
        try {
            if (get(key) == null) {
                MemcachedLogUtils.writeLog("Memcached append方法报错，key值：" + key + "\r\n" + exceptionWrite(new Exception("Memcached内存缓存中不存在该键值对")));
                return false;
            }
            return memCachedClient.append(key, appendValue);
        } catch (Exception e) {
            MemcachedLogUtils.writeLog("Memcached append方法报错，key值：" + key + "\r\n" + exceptionWrite(e));
            return false;
        }
    }

    /**
     * 向已经存在的key的value 前面 追加数据
     *
     * @param key          键
     * @param prependValue 追加的值
     * @return boolean
     */
    public static boolean prepend(String key, Object prependValue) {
        try {
            if (get(key) == null) {
                MemcachedLogUtils.writeLog("Memcached prepend方法报错，key值：" + key + "\r\n" + exceptionWrite(new Exception("Memcached内存缓存中不存在该键值对")));
                return false;
            }
            return memCachedClient.prepend(key, prependValue);
        } catch (Exception e) {
            MemcachedLogUtils.writeLog("Memcached prepend方法报错，key值：" + key + "\r\n" + exceptionWrite(e));
            return false;
        }
    }


    /**
     * 清理缓存中的所有键/值对
     */
    public static boolean flashAll() {
        try {
            return memCachedClient.flushAll();
        } catch (Exception e) {
            MemcachedLogUtils.writeLog("Memcached flashAll方法报错\r\n" + exceptionWrite(e));
            return false;
        }
    }

    /**
     * 返回String类型的异常栈信息
     */
    private static String exceptionWrite(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }


    /**
     * Memcached日志记录工具
     * Created by zhengyou on 2017/5/9.
     */
    private static class MemcachedLogUtils {

        private static FileWriter fileWriter;
        private static BufferedWriter logWrite;
        private final static String PID = ManagementFactory.getRuntimeMXBean().getName();// 通过找到对应的JVM进程获取PID

        //初始化Memcached日志写入流
        static {
            try {
                String osName = System.getProperty("os.name");
                if (osName.contains("Windows")) {
                    fileWriter = new FileWriter("D:\\memcached.log", true);
                } else {
                    fileWriter = new FileWriter("/usr/local/logs/memcached.log", true);
                }
                logWrite = new BufferedWriter(fileWriter);
            } catch (IOException iOException) {
                iOException.printStackTrace();
                try {
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    if (logWrite != null) {
                        logWrite.close();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }

        /**
         * 写入日志信息
         *
         * @author GaoHuanjie
         */
        private static void writeLog(String logContent) {
            try {
                logWrite.write("[" + PID + "] " + "- [" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]\r\n" + logContent);
                logWrite.newLine();
                logWrite.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}