package org.example;

public class Fork {
    boolean taken = false;

    public synchronized void pick() throws InterruptedException {
        while(taken){
            wait();
        }
        taken = true;
    }

    public synchronized void drop(){
        taken = false;
        notifyAll();
    }
}
