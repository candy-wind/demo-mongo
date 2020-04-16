package com.example.demomongo.param;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author candy-wind
 * @Data: 2020-03-20 19:23
 * @Version 1.0
 */



public class Person {

    private String pname;

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pname='" + pname + '\'' +
                ", arrs=" + Arrays.toString(arrs) +
                ", list=" + list +
                ", map=" + map +
                ", properties=" + properties +
                '}';
    }

    private String[] arrs;
    private List<String> list;

    public void setArrs(String[] arrs) {
        this.arrs = arrs;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private Map<String,String> map;
    private Properties properties;
    public void test1(){
        System.out.println("person..."+pname+"ars:"+Arrays.toString(arrs)+"list:"+list+"map:"+map+"properties:"+properties);
    }
}
