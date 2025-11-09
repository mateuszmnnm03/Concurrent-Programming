package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NodeListBetter {
    private Node head;
    private int size;
    private final int maxSize;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();



    public NodeListBetter(int maxSize) {
        head = null;
        size = 0;
        this.maxSize = maxSize;
    }

    public boolean contains(Object o) {
        Node curr = head;
        Node prev = null;

        while (curr != null) {
            if (prev != null) {
                prev.getLock().unlock();
            }
            curr.getLock().lock();
            if (curr == o) {
                curr.getLock().unlock();
                return true;
            }
            prev = curr;
            curr = curr.getNext();
        }
        prev.getLock().unlock();
        return false;
    }

    public void add(Object o) throws InterruptedException {
        if (o == null) {
            return;
        }

        while(this.size == maxSize) {
            notFull.await();
        }

        Node curr = head;
        Node prev = null;
        head.getLock().lock();

        while (curr.getNext() != null) {
            curr.getLock().unlock();
            prev = curr;
            curr = curr.getNext();
            curr.getLock().lock();
        }
        prev.getLock().unlock();
        curr.getLock().lock();
        curr.setNext(new Node(0, null));
        size++;
        curr.getLock().unlock();
        notEmpty.signal();
    }

    public boolean remove(Object o) throws InterruptedException {
        if (o == null) {
            return false;
        }

        while(this.size == 0) {
            notEmpty.await();
        }

        Node curr = head;
        Node prev = null;

        head.getLock().lock();
        while (curr.getNext() != null) {
            if (curr.getNext() == o) {
                curr.getLock().unlock();
                curr.setNext(curr.getNext().getNext());
                return true;
            }
            curr.getLock().unlock();
            prev = curr;
            curr = curr.getNext();
            curr.getLock().lock();
        }
        prev.getLock().unlock();
        return false;
    }

}
