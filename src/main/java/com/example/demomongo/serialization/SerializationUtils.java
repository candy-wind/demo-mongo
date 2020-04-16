package com.example.demomongo.serialization;

import java.io.*;

/**
 * @Author candy-wind
 * @Data: 2020-03-25 18:11
 * @Version 1.0
 */
public class SerializationUtils {


    public String encode(Object object){
        if (object == null) {
            return null;
        }

        try {
            System.out.println("序列化之前"+object);
            ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();//定义一个字节数组输出流
            ObjectOutputStream objectOutputStream =new ObjectOutputStream(byteArrayOutputStream);  //对象输出流
            objectOutputStream.writeObject(object);  //将指定的对象写入字节数组输出，进行序列化

            String serStr = byteArrayOutputStream.toString("ISO-8859-1");
            objectOutputStream.close();
            byteArrayOutputStream.close();
            decode(serStr,User.class);
            System.out.println("序列化文本"+serStr);
            return serStr;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;

    }


    public static <T> T decode(String str, Class<T> type) {
        if (str == null) {
            return null;
        }

        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            T t = (T) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            System.out.println("反序列化之后"+t);
            return t;
        } catch (Exception e) {
        }

        return null;
    }

    public static void main(String[] args) {
        User user =new User();
        user.setAge("23");
        user.setName("ada");
        user.setSex("dafa");
        user.setHeight(190);
        SerializationUtils serializationUtils =new SerializationUtils();
        serializationUtils.encode(user);
    }

}
