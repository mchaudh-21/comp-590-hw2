package dining;
public class Philosopher implements Runnable {
    private final int id;
    private final Fork left;
    private final Fork right;

    public Philosopher(int id, Fork left, Fork right) {
        this.id = id;
        this.left = left;
        this.right = right;
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
        // TODO: acquire forks
        System.out.println("Philosopher " +id+" EATING");
        Thread.sleep(200);
        // TODO: release forks
    }
}
