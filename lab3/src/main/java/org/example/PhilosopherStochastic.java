package org.example;

import java.util.Random;

public class PhilosopherStochastic extends Philosopher {

    public PhilosopherStochastic(Fork left, Fork right) {
        super(left, right);
    }

    @Override
    protected void pickForks() throws InterruptedException {
        long startTime = System.nanoTime();
        Random rand = new Random();
        int num = rand.nextInt(2);
        if (num == 0) {
            right.pick();
            left.pick();
        }
        else {
            left.pick();
            right.pick();
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        synchronized (this) {
            this.timeWaited += elapsedTime;
            this.eatenCounter += 1;
        }

    }
}
    // ZADANIE 4
//    while(true) {
//        System.out.println("Philosopher " + id + " is thinking.");
//        Thread.sleep((int) (Math.random() * 1000));
//        Random rand = new Random();
//        int res = rand.nextInt(2);
//        if(res == 0) {
//            left.pick();
//            System.out.println("Philosopher " + id + " picked up the left.");
//            Thread.sleep((int) (Math.random() * 1000));
//            right.pick();
//            System.out.println("Philosopher " + id + " picked up the right and is eating.");
//            Thread.sleep((int) (Math.random() * 3000));
//            left.drop();
//            right.drop();
//            System.out.println("Philosopher " + id + " put down both.");
//            Thread.sleep((int) (Math.random() * 1000));
//        }
//        else {
//            right.pick();
//            System.out.println("Philosopher " + id + " picked up the right.");
//            Thread.sleep((int) (Math.random() * 1000));
//            left.pick();
//            System.out.println("Philosopher " + id + " picked up the left and is eating.");
//            Thread.sleep((int) (Math.random() * 3000));
//            right.drop();
//            left.drop();
//            System.out.println("Philosopher " + id + " put down both.");
//            Thread.sleep((int) (Math.random() * 1000));
//        }
//    }


