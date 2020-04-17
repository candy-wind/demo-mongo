package com.example.demomongo.utils;

import java.util.LinkedList;

/**
 * @Author candy-wind
 * @Data: 2020-04-16 11:26
 * @Version 1.0
 */
public class Node {

    Node previous;
    Node next;
    Object element;

    public Node(Object element) {
        super();
        this.element = element;
    }

    public Node(Node previous, Node next, Object element) {
        super();
        this.previous = previous;
        this.next = next;
        this.element = element;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }


    public static void main(String[] args) {
        LinkedList linkedList ;
    }
}
