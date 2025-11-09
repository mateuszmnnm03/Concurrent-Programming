package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws InterruptedException {
        int cnt = 0;
        for (int n = 5; n <= 20; n += 5) {
            for (int i = 1; i < 7; i++) {
                Simulation sim = new Simulation(n, i);
                sim.run();
                Thread.sleep(5000);
                System.out.println("koniec symulacji numer " + ++cnt);
            }
        }


    }
}