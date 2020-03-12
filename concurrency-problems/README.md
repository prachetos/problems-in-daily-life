Concurrency is a part and parcel of daily life. 
We should know the difference between parallelism 
and concurrency

In different languages , there are different ways of handling 
concurrency.

I will try to code solutions to 2 problems. 

1. Pub Sub
   * Imagine a chat app server. There will be multiple groups, 
   and groups will have people joining and leaving. 
   People produce messages in group, and read messages for other people

   Idea is how to model message queue as an array, how to use single
   shared array/map or fan out etc. 


2. Job scheduling problem 
   * Design a simple job scheduler
     * Takes a job, executeAt timestamp. 
     * Schedules it for execution then

     This is a very common interview question these days

