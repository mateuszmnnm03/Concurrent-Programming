package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ForkLock {
    private final Lock lock = new ReentrantLock();

    public void pick(){
        lock.lock();
    }

    public void drop(){
        lock.unlock();
    }
}
