package org.example;

public class PhilosopherNaive extends Philosopher {

    public PhilosopherNaive(Fork left, Fork right) {
        super(left, right);
    }

    @Override
    public void pickForks() throws InterruptedException {
        long startTime = System.nanoTime();
        left.pick();
        System.out.println("Philosopher " + id + " picked up the left.");
        right.pick();
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        synchronized (this) {
            this.timeWaited += elapsedTime;
            this.eatenCounter += 1;
        }
    }


}



