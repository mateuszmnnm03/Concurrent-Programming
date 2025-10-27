// ZADANIE 1
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

class Rewolwerowiec extends Thread{
    private final String name;
    private int cnt;
    private final AtomicBoolean flag;

    public Rewolwerowiec(String name, int cnt, AtomicBoolean flag){
        this.name = name;
        this.cnt = cnt;
        this.flag = flag;
    }

    public void run(){
        for(int i = 0; i < cnt; i++){
            if(flag.get()){
                // System.out.println("przerywam");
                return;
            }
            System.out.println(name + ": " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
            // if(flag.compareAndSet(false, true)){
            //     return;
            // }
        }
        if(flag.compareAndSet(false, true)){
            System.out.println("Pif! Paf!");
        }
    }

}

class Main{
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        AtomicBoolean czyKtosStrzelil = new AtomicBoolean(false);

        executor.execute(new Rewolwerowiec("Jan", 6,  czyKtosStrzelil));
        executor.execute(new Rewolwerowiec("Max", 4,  czyKtosStrzelil));
        executor.execute(new Rewolwerowiec("Tom", 8, czyKtosStrzelil));

        executor.shutdown();
    }
}
