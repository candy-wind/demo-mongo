package com.example.demomongo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * 数组扩容
 * @param <E>
 */

public class TestArraylist<E> {


    private Object[] elementData;
    private int size;

    private static final int DEFAULT_SIZE = 10;

    public TestArraylist(){
        elementData = new Object[DEFAULT_SIZE];
    }


    public TestArraylist(int capacity){
        elementData = new Object[capacity];
    }
    public void add(E element){
        if(size==elementData.length){
            Object[] newArray =new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData,0,newArray,0,elementData.length);

        elementData =newArray;

        }

        elementData[size++] = element;

        //什么时候扩容 怎么扩容

    }

    public E get(int index){
        return (E)elementData[index];
    }

    public void set(E element,int index){

        if(index >size || index <0){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);

        }
        elementData[index]=element;
    }


    /**
     *
     * 数组扩容
     * @param args
     */

    public static void main(String[] args) {
        TestArraylist testArraylist= new TestArraylist();

        for (int i = 0; i < 40 ; i++) {
            testArraylist.add("23");
        }
//       testArraylist.set("23232",100);

        System.out.println(testArraylist);

    }

    @Override
    public String toString() {
        return "TestArraylist{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }
}
