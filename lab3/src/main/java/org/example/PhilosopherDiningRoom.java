package org.example;

import java.util.concurrent.Semaphore;

public class PhilosopherDiningRoom extends Philosopher {
    private final Semaphore semaphore;
    private boolean inDiningRoom = false;

    public PhilosopherDiningRoom(Fork left, Fork right, Semaphore semaphore) {
        super(left, right);
        this.semaphore = semaphore;
    }

    @Override
    protected void pickForks() throws InterruptedException {
        long startTime = System.nanoTime();

        if (semaphore.tryAcquire()) {
            left.pick();
            right.pick();
            inDiningRoom = true;
        }
        else {
            right.pick();
            left.pick();
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        synchronized (this) {
            this.timeWaited += elapsedTime;
            this.eatenCounter += 1;
        }
    }

    @Override
    public void putForks() {
        left.drop();
        right.drop();
        if (inDiningRoom) {
            semaphore.release();
        }
    }
}
