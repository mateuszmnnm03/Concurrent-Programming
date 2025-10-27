package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int N = 6;
        Philosopher[] philosophers = new Philosopher[N];
        Fork[] forks = new Fork[N];

        PhilosopherLock[] philosopherLocks = new PhilosopherLock[N];
        ForkLock[] forkLocks = new ForkLock[N];

        for (int i = 0; i < N; i++) {
            forks[i] = new Fork();
            forkLocks[i] = new ForkLock();
        }
        for (int i = 0; i < N; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % N];
            philosophers[i] = new Philosopher(leftFork, rightFork, i);

            ForkLock leftLock = forkLocks[i];
            ForkLock rightLock = forkLocks[(i + 1) % N];
            philosopherLocks[i] = new PhilosopherLock(leftLock, rightLock, i);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(N);
        for (int i = 0; i < N; i++) {
            // zadanie 1
            philosophers[i].start();

            // zadanie 2
            //philosopherLocks[i].start();
        }
    }
}