# Design Choices

We decided to use Semaphores as our main approach to the Dining Philosophers problem. Each fork is represented as a semaphore with only one permit, which means it can only be held by one philosopher at a time. 

## Fairness
To ensure fairness, we used the optional `fair` parameter in the Semaphore constructor. This means that the queue to obtain a permit (in our case, to hold the fork) is treated as first-in, first-out. If a philosopher is the first one to try to pick up a busy fork, they will be the first one to recieve that fork once it is free, and no other philosopher can "skip the line."

## Deadlocks
TODO