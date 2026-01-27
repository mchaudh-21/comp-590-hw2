package dining;
import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {
    private final int id;
    private final Fork left;
    private final Fork right;
    private final Semaphore eatingLimiter; //only 4 can eat at a time

    public Philosopher(int id, Fork left, Fork right, Semaphore eatingLimiter) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.eatingLimiter = eatingLimiter;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                // TODO: deadlock and starvation
                eat();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private void think() throws InterruptedException {
        System.out.println("Philosopher " +id+" THINKING");
        Thread.sleep(200);
    }
    private void eat() throws InterruptedException {
        eatingLimiter.acquire();
        
        try {
            pickUpLeftFork();
            pickUpRightFork();
            System.out.println("Philosopher " +id+" EATING");
            Thread.sleep(200);
            putDownLeftFork();
            putDownRightFork();
        } finally {
            // Always realease to prevent starvation of any philosopher
            eatingLimiter.release();
        }
    }

    private void pickUpLeftFork() throws InterruptedException{
        // thread will wait until the fork is free
        
        //System.out.println("Philosopher" + id + " picked up fork " + left.getId());
        left.acquire();   
    }

    private void pickUpRightFork() throws InterruptedException {
        //System.out.println("Philosopher" + id + " picked up fork " + right.getId());
        right.acquire();
    }

    private void putDownLeftFork() throws InterruptedException {
        //System.out.println("Philosopher" + id + " put down fork " + left.getId());
        left.release();
    }
    private void putDownRightFork() throws InterruptedException {
        //System.out.println("Philosopher" + id + " put down fork " + right.getId());
        right.release();
    }

}
