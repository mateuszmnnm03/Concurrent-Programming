package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;

public class Simulation {
    private final int N;
    private final int mode;

    public Simulation(int n, int mode) {
        N = n;
        this.mode = mode;
    }

    public int getN() {
        return N;
    }

    public void run(){
        Philosopher[] philosophers = new Philosopher[N];
        Fork[] forks = new Fork[N];
        Fork[] forkLocks = new Fork[N];

        for (int i = 0; i < N; i++) {
            forks[i] = new Fork(false);
            forkLocks[i] = new Fork(true);
        }

        Semaphore semaphore = null;
        if (mode == 5 || mode == 6) {
            semaphore = new Semaphore(N - 1);
        }
        for (int i = 0; i < N; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % N];

            Fork leftLockFork = forkLocks[i];
            Fork rightLockFork = forkLocks[(i + 1) % N];

            switch (mode) {
                case 1:
                    philosophers[i] = new PhilosopherNaive(leftFork, rightFork);
                    break;
                case 2:
                    philosophers[i] = new PhilosopherStarving(leftLockFork, rightLockFork);
                    break;
                case 3:
                    philosophers[i] = new PhilosopherAsymmetric(leftFork, rightFork);
                    break;
                case 4:
                    philosophers[i] = new PhilosopherStochastic(leftLockFork, rightLockFork);
                    break;
                case 5:
                    philosophers[i] = new PhilosopherArbiter(leftFork, rightFork, semaphore);
                    break;
                case 6:
                    philosophers[i] = new PhilosopherDiningRoom(leftFork, rightFork, semaphore);
                    break;
                default:
                    System.out.println("Invalid mode");
                    break;
            }

        }

        ExecutorService executor = Executors.newFixedThreadPool(N);
        for (Philosopher philosopher : philosophers) {
            executor.execute(philosopher);
        }

        try {
            Thread.sleep(45000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Philosopher philosopher : philosophers) {
            philosopher.stop();
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.err.println("err");
                }
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        long[] avgTimes = new long[N];
        int[] mealsCount = new int[N];
        for (int i = 0; i < N; i++) {
            if (philosophers[i].eatenCounter > 0) {
                avgTimes[i] = philosophers[i].getTimeWaited() / philosophers[i].getEatenCounter() / 1000000;
            } else {
                avgTimes[i] = 0;
            }
            mealsCount[i] = philosophers[i].getEatenCounter();
        }

        try (FileWriter writer = new FileWriter("sec_times_mode" + mode + "_n" + N  + ".csv")) {
            for (int i = 0; i < N; i++) {
                writer.append(Long.toString(avgTimes[i]));
                if (i != N - 1) {
                    writer.append(',');
                }
            }
            writer.append('\n');

            for (int i = 0; i < N; i++) {
                writer.append(Integer.toString(mealsCount[i]));
                if (i != N - 1) {
                    writer.append(',');
                }
            }
            writer.append('\n');
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}