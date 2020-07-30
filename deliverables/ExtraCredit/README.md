# Extra Credit - Property-based Testing

Summer Semester 2020 - Extra Credit

* DUE: August 2, 2020 11:59 PM

This is a continuation of [Exercise 4 Performance Testing](../../exercises/4)
and [Exercise 6 Property-based Testing](../../exercises/6).  We are going to
further optimize MonkeySim using VisualVM profiling and then we will be at a
position where the program is fast enough for stochastic testing.

This extra credit is worth 1 point out of 100 points for the entire course, if
submitted on time.  You have to fulfill all requirements to get that 1 point.
There is no partial credit.

Note that this is an individual assignment.  No partners are allowed.

## Description

Remember the Collatz Conjecture
(https://en.wikipedia.org/wiki/Collatz_conjecture) underlying MonkeySim?  The
conjecture stated that the game will eventually end (that is the first monkey
will eventually get the banana).  The conjecture is probably true since nobody
was able to find a counter-example since 1937 when it was first proposed.  

Your job for the extra credit is to find that counter-example in MonkeySim.
The counter-example exists not because the conjecture is false, but because
MonkeySim is not a faithful implementation of the conjecture.  The game does
have a defect where for certain arguments (the starting number of the monkey),
the game falls into an infinite loop (that is, the first monkey NEVER gets the
banana).  Now, this defect is hard to find using just a handful of test cases
you can write using JUnit.  So we are going to use QuickCheck property-based
testing to feed MonkeySim with a whole bunch of randomized arguments to see
which triggers the infinite loop.  

So when would the game fall into an infinite loop?  It happens when a monkey
that already possessed the banana previously again receives the banana.  That
means a cycle exists in the sequence of monkeys with the banana and that cycle
will repeat forever.  But how would you detect this infinite loop using JUnit
assertions?  If a method call causes an infinite loop, the method will simply
not return and JUnit will just hang.  There is a way to configure a timeout for
JUnit tests but using timeouts for detection is not fool proof (e.g. how would
you set the length of the timeout?).

To detect infinite loops precisely, you will have to modify MonkeySim to
internally detect when the above situation occurs and throw an
InfiniteLoopException in that case.  Then JUnit can catch that exception when
it occurs and assert a test failure.

## What to do 

1. Optimize the MonkeySim implementation to the best of your ability leveraging
   VisualVM.  You can re-apply the optimizations that you did for Exercise 4.
But take care not to completely overwrite MonkeySim.java because some method
interfaces have changed.  You probably will need to be much more aggressive
when optimizing MonkeySim in order to make it fast enough for stochastic
testing.  All code is fair game, as long as you do not modify system behavior
(the output you get on a command line argument).

2. I have already written a pinning test for you in MonkeySimTest.java with the
   method name testMain5().  The pinning test tests the main method with the
command line argument 5, and compares the system output against expected
output.  The pinning test passes initially and it should stay that way while
you do all modifications to MonkeySim.  You can run MonkeySimTest using the script:

    ```
    runTest.bat
    ```
    for Windows machines, and the script:
    ```
    bash runTest.sh
    ```
    For Mac/Linux machines.

3. I have also written a performance test for you MonkeySimTest.java with the
   method name testMain1000000.  It tests the main method with the command line
argument 1000000.  It has a timeout of 100 milliseconds and tests whether the
program can handle such a large number of monkeys within just 100 milliseconds.
This performance test needs to pass in order for the program to be fast enough
for stochastic testing, where much larger numbers will be passed.

4. Modify the MonkeySim implementation such that the InfiniteLoopException is
   thrown in the main() method when a cycle among monkeys is detected.

5. Implement the testMainStochastic(int s) method in MonkeySimTest.java
   according to the comments.  Once you implement this, run JUnit again using
either runTest.bat or runTest.sh.  QuickCheck will do 100 random trials on
testMainStochastic to find the argument that triggers the InfiniteLoopException
to be thrown.  Since the trials are random, you may end up with a different
number each time, but you should be able to find *a* number within the 100
trials.  You may find that your MonkeySim implementation is too slow to
complete the trials within a reasonable amount of time.  Then you need to loop
back to Step 1 and further optimize MonkeySim.

6. Once you find *a* number that triggers the infinite loop, complete
   testMainInfiniteLoop() in MonkeySimTest.java by writing a regular JUnit test
that triggers that infinite loop by using that number.  The test should catch
the resulting InfiniteLoopException and assert fail, failing the test.

If all goes well, you should see something similar to the following output when
runTest.bat or runTest.sh is run:

```
testMainInfiniteLoop(MonkeySimTest): Error: Infinite loop exception thrown
testMainStochastic(MonkeySimTest): Property named 'testMainStochastic' failed (Error: Infinite loop exception thrown):
With arguments: [<SHRUNK_ANSWER_NUMBER>]
Original failure message: Error: Infinite loop exception thrown
First arguments found to also provoke a failure: [<ORIGINAL_ANSWER_NUMBER>]
Seeds for reproduction: [<SEED_NUMBER>]

!!! - At least one failure, see above.
```

QuickCheck, when it finds a failing argument (the <ORIGINAL_ANSWER_NUMBER>
above), it will attempt to shrink that number to a smaller number (the
<SHRUNK_ANSWER_NUMBER> above).  When you implement testMainInfiniteLoop(), it
is better to use the smaller <SHRUNK_ANSWER_NUMBER> since it will more likely
result in a shorter cycle and a shorter test.

## Submission

You will do an individual submission to GradeScope.

You will create a github repository just for ExtraCredit.  Make sure you keep
the repository *PRIVATE* so that nobody else can access your repository.  Once
you are done modifying code, don't forget to commit and push your changes to
the github repository.  When you are done, submit your github repository to
GradeScope at the "ExtraCredit GitHub" link.  

## GradeScope Feedback

The GradeScope autograder works in 5 phases:

1. MonkeySim method pinning tests

This is the testMain5() test in MonkeySimTest.java.

2. MonkeySim method performance tests

This is the testMain1000000() test in MonkeySimTest.java.

3. MonkeySimTest on MonkeySim

This is the result of running testMainStochastic and testMainInfiniteLoop on
your MonkeySim implementation.  Both tests are expected to fail with an
infinite loop exception.  Make sure you use the "Error: Infinite loop exception
thrown" fail string in your JUnit assertion for GradeScope to work properly.

The pinning test is a sanity test to make sure you did not illegally modify
existing behavior.  If that test fails, you automatically get a 0.  Otherwise,
the score you see on the autograder is the score you will get for the extra
credit.  That means, you can get partial credit for performance optimization
even if your property-based tests don't work and vice-versa.  I *will* do a
manual check on your code to make sure you properly followed instructions and
you didn't try to game GradeScope and will deduct points from the "Deductions"
rubric on GradeScope.
