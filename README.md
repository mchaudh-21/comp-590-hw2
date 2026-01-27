# Design Choices

We decided to use Semaphores as our main approach to the Dining Philosophers problem. Each fork is represented as a semaphore with only one permit, which means it can only be held by one philosopher at a time. 

## Fairness
To ensure fairness, we used the optional `fair` parameter in the Semaphore constructor. This means that the queue to obtain a permit (in our case, to hold the fork) is treated as first-in, first-out. If a philosopher is the first one to try to pick up a busy fork, they will be the first one to recieve that fork once it is free, and no other philosopher can "skip the line."

## Deadlocks
Deadlocks occur when a philosopher is holding a fork but waits indefinitely for the other, which creates a circular wait. We used Semaphores to limit how many philosophers can attempt to pick up forks simultaneously. The semaphore is initialized with N-1 permits (N = number of philosophers). In order to obtain a fork a philosopher must get a permit from the semaphore (breaks circular wait condition by ensuring tht at least one philosopher is always able to obtain both forks). 

Philosophers must also release any resources they acquire if they cannot get both forks within a certain amount of time. 

## Starvation 
Starvation is mitigated through the use of fair semaphores for both the forks and the global permit semaphore. 

Since waiting philosophers are served in a FIFO order, philosphers will not be delayed infinitely by others that keep acquiring resources. Starvation is highly unlikely with this practice.

## Race Conditions
Race conditions are avoided by protecting all shared resources with semaphores. Each fork is guarded by a semaphore with a single permit which prevents more than one philosopher from accessing the same fork simultaneously. All acquire and release operations happen completely or don't happen at all, which maintains consistent behavior and prevents conflicting access to shared state.

## Files
### Main.java
- Entry point for program 
- Initializes:
    - Circular array
    - Global semaphore called `eatingLimiter`
    - Spawn threads, each runs a Philosopher

### Philosopher.java
- Represents a philosopher via `Runnable`
- Thinking and eating are simulated using `Thread.sleep(...)`
- Order of operations for philosopher:
    - acquire the global `eatingLimiter`
    - get the left fork semaphore
    - get the right fork semaphore
    - eat
    - release both forks
    - release `eatingLimiter` in a `finally` block

### Fork.java
- Represents a fork as a semaphore:
    - Permit (mutual exclusion: one philosopher can hold a fork at a time)
    - Enable fairness 