package com.example.demomongo.utils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author candy-wind
 * @Data: 2020-04-16 11:15
 * @Version 1.0
 */
public class TestLinkedList {

    private Node first;
    private Node last;
    private int size;


    public void add(Object object){
        Node node =new Node(object);
        if(first == null){
            first = node;
            last =node;
        }else {
            node.previous = last;
            node.next = null;
            last.next =node;
            last = node;
        }
    }

    public static void main(String[] args) {
//        LinkedList
//        HashMap hashMap = new HashMap();
//        hashMap.put("","");
//        Hashtable hashtable = new Hashtable();
//        hashtable.put("","");
//        System.out.println(hashMap);
//        System.out.println(hashtable);

        TestLinkedList testLinkedList =new TestLinkedList();
        testLinkedList.add("a");
        testLinkedList.add("b");
        testLinkedList.add("c");
        System.out.println(testLinkedList);
    }

    @Override
    public String toString() {
       Node node = first;
       while (node !=null){
           System.out.println(node.element);
           node= node.next;
       }
       return "";
    }
}
