
package org.example;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NodeList {
    private Node head;
    private int size;
    private int maxSize;

    private final ReentrantLock listLock = new ReentrantLock();
    private final Condition notFull = listLock.newCondition();
    private final Condition notEmpty = listLock.newCondition();

    public NodeList(int maxSize) {
        this.head = null;
        this.maxSize = maxSize;
        this.size = 0;
    }

    public boolean contains(Object o) {
        listLock.lock();
        try {
            Node curr = head;
            while (curr != null) {
                if (curr.getObject().equals(o)) return true;
                curr = curr.getNext();
            }
            return false;
        } finally {
            listLock.unlock();
        }

    }

    public void add(Object o) {
        if(o == null) {
            return;
        }

        listLock.lock();

        try {
            while (size == maxSize) {
                notFull.await();
            }
            if (head == null) {
                head = new Node(o, null);
            }

            else {
                Node curr = this.head;

                while (curr.getNext() != null) {
                    curr = curr.getNext();
                }
                if (this.size + 1 <= this.maxSize) {
                    Node toAdd = new Node(o, null);
                    curr.setNext(toAdd);
                }
            }
            notEmpty.signal();
            this.size++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            listLock.unlock();
        }
    }

    public boolean remove(Object o) {
        listLock.lock();

        try {
            while (size == 0) {
                notEmpty.await();
            }

            if (head.getObject().equals(o)) {
                head = head.getNext();
                size--;
                notFull.signal();
                return true;
            }

            Node curr = this.head;
            while (curr != null && curr.getNext() != null && !curr.getNext().getObject().equals(o)) {
                curr = curr.getNext();
            }

            if (curr == null) {
                return false;
            }

            curr.setNext(curr.getNext().getNext());
            notFull.signal();
            this.size--;
            return true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally{
            listLock.unlock();
        }
    }
}
