package com.example.demomongo.serialization;

import com.sun.xml.internal.ws.developer.Serialization;

/**
 * @Author candy-wind
 * @Data: 2020-03-25 18:07
 * @Version 1.0
 */
import java.io.Serializable;
public class User implements Serializable {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String name;

    private transient int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    private String age;
    private String sex;

}
