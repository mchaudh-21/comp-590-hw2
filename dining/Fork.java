package dining;

import java.util.concurrent.Semaphore;

public class Fork extends Semaphore{
    private final int id;
    public Fork(int id) {
        // initialize a Semaphore with 1 permit, so only 1 philosopher can hold it at a time
        super(1, true); 
        this.id = id;
    }
    public int getId() {
        return id;
    }
    // TODO add locking logic
}
