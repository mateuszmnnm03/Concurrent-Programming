package org.example;

public class PhilosopherLock extends Thread {
    private final ForkLock left;
    private final ForkLock right;
    private int id;

    public PhilosopherLock(ForkLock left, ForkLock right, int id) {
        this.left = left;
        this.right = right;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            // ZADANIE 2
            while (true){
                System.out.println("Philosopher " + id + " is thinking.");
                Thread.sleep((int) (Math.random() * 1000));
                left.pick();
                right.pick();
                System.out.println("Philosopher " + id + " picked up forks.");
                Thread.sleep((int) (Math.random() * 2000));
                left.drop();
                right.drop();
                System.out.println("Philosopher " + id + " dropped.");
                Thread.sleep((int) (Math.random() * 2000));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
