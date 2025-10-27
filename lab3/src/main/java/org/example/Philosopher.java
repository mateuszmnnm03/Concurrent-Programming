package org.example;

import java.util.Random;

public class Philosopher extends Thread {
    private final Fork left;
    private final Fork right;
    private int id;

    public Philosopher(Fork left, Fork right, int id) {
        this.left = left;
        this.right = right;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            // ZADANIE 1
//            while (true) {
//                System.out.println("Philosopher " + id + " is thinking.");
//                Thread.sleep((int) (1000));
//
//                left.pick();
//                System.out.println("Philosopher " + id + " picked up the left.");
//                Thread.sleep((int) (1000));
//
//                right.pick();
//                System.out.println("Philosopher " + id + " picked up the right and is eating.");
//                Thread.sleep((int) (2000));
//
//                left.drop();
//                right.drop();
//                System.out.println("Philosopher " + id + " put down both.");
//                Thread.sleep((int) (1000));
//            }


            // ZADANIE 3
//            while (true) {
//                System.out.println("Philosopher " + id + " is thinking");
//                Thread.sleep((int) (Math.random() * 1000));
//
//                if (id % 2 == 0){
//                    right.pick();
//                    System.out.println("Philosopher " + id + " picked up the right.");
//                    Thread.sleep((int) (Math.random() * 1000));
//                    left.pick();
//                    System.out.println("Philosopher " + id + " picked up the left and is eating.");
//                    Thread.sleep((int) (Math.random() * 2000));
//                    left.drop();
//                    right.drop();
//                    System.out.println("Philosopher " + id + " dropped both.");
//                    Thread.sleep((int) (Math.random() * 1000));
//                }
//
//                else{
//                    left.pick();
//                    System.out.println("Philosopher " + id + " picked up the left.");
//                    Thread.sleep((int) (Math.random() * 1000));
//                    right.pick();
//                    System.out.println("Philosopher " + id + " picked up the right.");
//                    Thread.sleep((int) (Math.random() * 1000));
//                    left.drop();
//                    right.drop();
//                    System.out.println("Philosopher " + id + " dropped both.");
//                    Thread.sleep((int) (Math.random() * 1000));
//                }
//            }
            // ZADANIE 4
            while(true) {
                System.out.println("Philosopher " + id + " is thinking.");
                Thread.sleep((int) (Math.random() * 1000));
                Random rand = new Random();
                int res = rand.nextInt(2);
                if(res == 0) {
                    left.pick();
                    System.out.println("Philosopher " + id + " picked up the left.");
                    Thread.sleep((int) (Math.random() * 1000));
                    right.pick();
                    System.out.println("Philosopher " + id + " picked up the right and is eating.");
                    Thread.sleep((int) (Math.random() * 3000));
                    left.drop();
                    right.drop();
                    System.out.println("Philosopher " + id + " put down both.");
                    Thread.sleep((int) (Math.random() * 1000));
                }
                else {
                    right.pick();
                    System.out.println("Philosopher " + id + " picked up the right.");
                    Thread.sleep((int) (Math.random() * 1000));
                    left.pick();
                    System.out.println("Philosopher " + id + " picked up the left and is eating.");
                    Thread.sleep((int) (Math.random() * 3000));
                    right.drop();
                    left.drop();
                    System.out.println("Philosopher " + id + " put down both.");
                    Thread.sleep((int) (Math.random() * 1000));
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
