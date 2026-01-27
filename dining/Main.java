package dining;
public class Main {
    public static final int NUM_PHILOSPHERS = 5;
    public static void main(String[] args) {
        Fork[] forks = new Fork[NUM_PHILOSPHERS];

        for (int i = 0; i < NUM_PHILOSPHERS; i++){
            forks[i] = new Fork(i);
        }

        for (int i = 0; i< NUM_PHILOSPHERS; i++) {
            Fork left = forks[i];
            Fork right = forks[(i+1) % NUM_PHILOSPHERS];
            Thread t = new Thread(
                    new Philospher(i, left, right),
                    "Philosopher " +i
            );
            t.start();
        }
    }
}