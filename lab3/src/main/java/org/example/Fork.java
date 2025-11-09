package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private boolean taken = false;
    private final boolean withLock;
    private final Lock lock;

    public Fork(boolean withLock) {
        this.withLock = withLock;
        this.lock = withLock ? new ReentrantLock() : null;
    }

    public boolean isLocked(){
        if (withLock) {
            return ((ReentrantLock)lock).isLocked();
        }
        else{
            return taken;
        }
    }

    public boolean tryPick(){
        if (withLock) {
            return ((ReentrantLock) lock).tryLock();
        }
        else{
            synchronized (this) {
                if (taken) {
                    return false;
                }
                taken = true;
                return true;
            }
        }
    }

    public void pick() throws InterruptedException {
        if (withLock) {
            lock.lock();
        }
        else {
            synchronized (this) {
                while (taken) {
                    wait();
                }
                taken = true;
            }
        }
    }

    public void drop(){
        if (withLock) {
            lock.unlock();
        }
        else {
            synchronized (this) {
                taken = false;
                notifyAll();
            }
        }
    }
}
