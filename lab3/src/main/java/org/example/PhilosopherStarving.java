package org.example;

public class PhilosopherStarving extends Philosopher {

    public PhilosopherStarving(Fork left, Fork right) {
        super(left, right);
    }

    @Override
    protected void pickForks() throws InterruptedException {
        long startTime = System.nanoTime();
        while (true) {
            boolean leftTaken = left.tryPick();
            if (!leftTaken) {
                Thread.sleep(1);
                continue;
            }
            boolean rightTaken = right.tryPick();
            if (!rightTaken) {
                left.drop();
                Thread.sleep(1);
                continue;
            }
            System.out.println("Philosopher " + id + " picked both and is eating. ");
            break;
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        synchronized (this) {
            this.timeWaited += elapsedTime;
            this.eatenCounter += 1;
        }
    }
}
