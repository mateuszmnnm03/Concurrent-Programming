package org.example;

import java.util.concurrent.TimeUnit;

class UnikalneNumery{
    private static int numerId = 0;
    synchronized public static int nowyID(){
        return numerId++;
    }
}

class Producer extends Thread {
    private final int id;
    private final Buffer _buf;

    public Producer(Buffer buf, int id){
        this._buf = buf;
        this.id = id;
    }
    public void run(){
        for(int i = 0; i < 10; ++i){
            try {
                _buf.put(i);
                System.out.println("Producer " + id + " produkuje pizze numer " + i + "...");
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

class Consumer extends Thread {

    private int id;
    private Buffer _buf;

    public Consumer(Buffer buf, int id){
        this._buf = buf;
        this.id = id;
    }

    public void run(){
        for(int i = 0; i < 10; ++i){
            try {
                System.out.println("Konsumer "+ id + " zjada pizze numer " + _buf.get() + "...");
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Buffer{
    private final int size;
    private byte[] buf;
    private int in; // pozycja do wstawienia
    private int out;
    private int cnt;

    public Buffer(int size){
        this.size = size;
        this.buf = new byte[size];
        this.in = 0;
        this.out = 0;
        this.cnt = 0;
    }
    public synchronized void put(int i) throws InterruptedException {
        while(cnt == size){
            wait();
        }

        buf[in] = (byte) i;
        in = (in + 1) % size;
        cnt++;
        notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        while(cnt == 0){
            wait();
        }
        cnt--;
        int tmp = buf[out];
        out = (out + 1) % size;
        notifyAll();
        return tmp;
    }
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Buffer buf = new Buffer(3);
        Producer p =  new Producer(buf, 1);
        Producer p2 = new Producer(buf, 2);
        Consumer c =  new Consumer(buf, 1);
        Consumer c2 =  new Consumer(buf, 2);
        Consumer c3 = new Consumer(buf, 3);

        p2.start();
        p.start();
        c.start();
        c2.start();
        c3.start();

        // 1 producer, 1 consumer


        // n producers, m consumers
    }
}