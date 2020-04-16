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
        System.out.println(testArraylist.get(10));
//        String str="{\n" +
//                "    \"code\": \"30000\",\n" +
//                "    \"message\": \"系统判断为不同人\",\n" +
//                "    \"data\": {\n" +
//                "        \"channel\": \"test\",\n" +
//                "        \"requestId\": \"test1586746205024\",\n" +
//                "        \"businessCode\": \"C2020041311013265896426722\",\n" +
//                "        \"batchNo\": \"bc3d7b1b93c445dcb9cdd3c34e4f990e\",\n" +
//                "        \"name\": \"张某某\",\n" +
//                "        \"idNo\": \"xy0f1570021d6c2f3976c9da4101223114ff633977f50ee1d1daeafd7c11c6d98c20160926\",\n" +
//                "        \"mobile\": \"xyab39185f4b86a19fec45efbf869cc92720160926\",\n" +
//                "        \"thirdName\": \"JY_JUNYU\",\n" +
//                "        \"data\": \"{\\\"operation_time\\\":\\\"2020-04-13 11:01:44\\\",\\\"sys_req_sn\\\":\\\"198604CB0017AB0EBCA626BF098C7EEC\\\",\\\"response_l3\\\":{\\\"code\\\":2,\\\"info\\\":\\\"系统判断为不同人\\\"},\\\"response_l2\\\":{\\\"code\\\":0,\\\"info\\\":\\\"系统判断为同一人\\\"},\\\"response_l1\\\":{\\\"code\\\":-1006,\\\"info\\\":\\\"必录项缺失,加密信息不能为空\\\"}}\"\n" +
//                "    }\n" +
//                "}";
//        JSONObject jsonObject = JSON.parseObject(str);
//        System.out.println(jsonObject.getJSONObject("data").getString("data"));
//        String  data =jsonObject.getJSONObject("data").getString("data");
//        JSONObject jsonObject1 = JSON.parseObject(data);
//        System.out.println(jsonObject1.getString("operation_time"));


    }

    @Override
    public String toString() {
        return "TestArraylist{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }
}
