package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class Node {
    private Object object;
    private Node next;
    private final ReentrantLock lock = new ReentrantLock();

    public Node(Object object, Node next) {
        this.object = object;
        this.next = next;
    }

    public Object getObject() {
        return object;
    }

    public Node getNext() {
        return next;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public ReentrantLock getLock() {
        return lock;
    }

}
