package dining;
public class Main {
    public static final int NUM_PHILOSOPHERS = 5;
    public static void main(String[] args) {
        Fork[] forks = new Fork[NUM_PHILOSOPHERS];

        for (int i = 0; i < NUM_PHILOSOPHERS; i++){
            forks[i] = new Fork(i);
        }

        for (int i = 0; i< NUM_PHILOSOPHERS; i++) {
            Fork left = forks[i];
            Fork right = forks[(i+1) % NUM_PHILOSOPHERS];
            Thread t = new Thread(
                    new Philosopher(i, left, right),
                    "Philosopher " +i
            );
            t.start();
        }
    }
}