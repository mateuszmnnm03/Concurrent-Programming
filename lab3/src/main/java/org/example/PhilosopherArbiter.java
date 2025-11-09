package org.example;

import java.util.concurrent.Semaphore;

public class PhilosopherArbiter extends Philosopher{
    private final Semaphore semaphore;

    public PhilosopherArbiter(Fork left, Fork right, Semaphore semaphore) {
        super(left, right);
        this.semaphore = semaphore;
    }


    @Override
    protected void pickForks() throws InterruptedException {
        long startTime = System.nanoTime();

        semaphore.acquire();
        left.pick();
        right.pick();

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        synchronized (this) {
            this.timeWaited += elapsedTime;
            this.eatenCounter += 1;
        }
    }

    @Override
    public void putForks(){
        left.drop();
        right.drop();
        semaphore.release();
    }
}
