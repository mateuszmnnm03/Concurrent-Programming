package org.example;

import java.util.concurrent.CountDownLatch;

public abstract class Philosopher extends Thread {
    protected final Fork left;
    protected final Fork right;
    private static int counter = 0; // no of philosophers
    protected final int id;


    public int getEatenCounter() {
        return eatenCounter;
    }

    public long getTimeWaited() {
        return timeWaited;
    }

    protected long timeWaited = 0;
    protected int eatenCounter = 0;

    public Philosopher(Fork left, Fork right) {
        this.id = ++counter;
        this.left = left;
        this.right = right;
    }

    public void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking");
        Thread.sleep((int)(1000));
//        Thread.sleep(100);
    }

    public void eat() throws InterruptedException {
//        System.out.println("Philosopher " + id + " picked up the right and is eating.");
        Thread.sleep((int) (2000));
//        Thread.sleep(100);
    }

    protected abstract void pickForks() throws InterruptedException;

    public void putForks() throws InterruptedException {
        left.drop();
        right.drop();
        System.out.println("Philosopher " + id + " put down forks");
    }

    public void run(){
        try {
            while (true) {
                think();
                pickForks();
                eat();
                putForks();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
