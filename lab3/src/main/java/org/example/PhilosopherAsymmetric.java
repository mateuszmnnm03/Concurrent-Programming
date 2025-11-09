package org.example;

public class PhilosopherAsymmetric extends Philosopher {

    public PhilosopherAsymmetric(Fork left, Fork right) {
        super(left, right);
    }

    @Override
    protected void pickForks() throws InterruptedException {
        long startTime = System.nanoTime();
        if (this.id % 2 == 0) {
            right.pick();
            System.out.println("Philosopher " + id + " picked up the right.");
            left.pick();
            System.out.println("Philosopher " + id + " picked up the left and is eating.");
        }
        else {
            left.pick();
            System.out.println("Philosopher " + id + " picked up the left.");
            right.pick();
            System.out.println("Philosopher " + id + " picked up the right and is eating.");
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        synchronized (this) {
            this.timeWaited += elapsedTime;
            this.eatenCounter += 1;
        }
    }
}
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

