# Exercise 5 - Static Analysis

In this exercise, you will use three static analysis tools to test a program: a
bug finder named SpotBugs, a linter named CheckStyle, and a model checker named
Java Pathfinder (JPF).  SpotBugs and CheckStyle work in similar ways in that
both look for patterns that are either symptomatic of a bug (former) or are bad
coding style (latter).  So we will look at them together first.  Later we will
look at JPF which is much more rigorous in proving a program correct.

* IMPORTANT: You need Java 8 (1.8.0.231, preferably) to run the Java Path
  Finder model checker.  Make sure you have the correct Java version by doing
"java -version" and "javac -version" before going into the JPF section.  If you
don't have the correct version, here is a link to a folder with installation
packages for each OS:

https://drive.google.com/drive/folders/1E76H7y2nMsrdiBwJi0nwlzczAgTKKhv7

## SpotBugs and CheckStyle

SpotBugs: https://spotbugs.github.io/  
CheckStyle: https://checkstyle.sourceforge.io/  

Try running both tools on a Sieve of Eratosthenes program, and then fix any
issues found.  This will allow you to see what kinds of bugs a static analysis
program can find (and which ones it cannot).

The Sieve of Eratosthenes is an ancient way of finding all prime numbers below
a specific value.  For details on the algorithm itself, please see
https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes.

This program accepts one integer value and will tell you all prime numbers up
to and including the passed-in value.  However, there are some problems hidden
in the code.  You are going to use SpotBugs and CheckStyle to find and fix
them.  Some problems are actual defects and some are just bad or confusing
code.

I have prepared scripts to run or test the program.  First cd into the Sieve
directory before executing the scripts.

To run the program (for Windows users):
```
$ run.bat [Integer]
```
To run SpotBugs:
```
$ runSpotbugs.bat
```
To run CheckStyle:
```
$ runCheckstyle.bat
```

For Mac or Linux users, please run the corresponding .sh scripts.

* There is a GUI for SpotBugs if that is what you prefer.  You can launch the GUI by using the following command:
```
$ java -jar spotbugs-4.0.0-beta4/lib/spotbugs.jar
```
The following link contains a short tutorial on how to use the GUI:
https://spotbugs.readthedocs.io/en/latest/gui.html

If all goes well you should see the following output:

```
$ java Sieve 100
Sieve of Eratosthenes
> 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97

$ java Sieve 14
Sieve of Eratosthenes
> 2 3 5 7 11 13
```

Note that there is a bug in the logic of the code that is not caught by either
SpotBugs or CheckStyle that will prevent you from getting the above output.  For example, the argument 100 will show the following:
```
$ java Sieve 100
Sieve of Eratosthenes
> 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 99
```
Locate the problem by reviewing the code and fix the problem.

### Lessons on Pattern Matching

Both linters (CheckStyle) and bug finders (SpotBugs) work by pattern matching.  Pattern matching can be good at finding simple bugs that are recurrent across projects and can even catch errors in your documentation.  What they are not good for is finding problems in your program logic (as seen above).  For that, you need dynamic testing that actually executes the program to check program behavior.  Or, you can use model checking that the proves that certain properties hold for all inputs (see below).

## Java Pathfinder (JPF)

Java Pathfinder is a tool developed by NASA to model check Java programs.  It
works in exactly the same way we learned in class: it does an exhaustive and
systematic exploration of program state space to check for correctness.

### JPF on Rand

Let's first try out JPF on the example Rand program we saw on the Formal Verification lecture
slides:  

<img src="jpf.png" width="50%" height="50%">

First cd into the Rand directory before executing the scripts.

To run the Rand program (for Windows users):
```
$ run.bat
```
To run JPF with Rand:
```
$ runJPF.bat
```

For Mac or Linux users, please run the corresponding .sh scripts.

When you run Rand with JPF, you can see from the screen output that it goes
through all possible states, thereby finding the two states with division-by-0
exception errors (I configured JPF to find all possible errors).  So, now we
know that there are two defective states, how do we debug?  You will see that
JPF has generated a trace file named [Rand.trace](Rand.trace) of all the
choices it had made to get to that state.  You will see two traces since there
are two defective states.  Pay attention to "cur" value of each Random.nextInt
invocation (that is the choice JPF has made for that invocation).  The first
trace shows values of 0, 2 for a, b and the second trace shows cur values of 1,
1 for a, b.  These are exactly the values that would cause a division-by-0
exception at c = a/(b+a -2).  In this way, the trace file lets you easily trace
through the code to get to the defective state.

### JPF on DrunkCarnivalShooter

First cd into the DrunkCarnivalShooter folder.

Now let's try using JPF to debug and verify a real program.  DrunkCarnivalShooter is a simple text-based game where the player goes to a carnival shooting range and tries to win the prize by shooting all 4 provied targets.  The player can designate what target to shoot for pressing 0-3.  But since the player is drunk, there is an equal chance of the player shooting left or right as shooting straight.  Refer to the file [sample_run.txt](sample_run.txt) for an example game play session.  You can also try playing it yourself using the reference implementation:
```
$ java -jar DrunkCarnivalShooter.jar
```

To run the DrunkCarnivalShooter using the current implementation (for Windows users):
```
$ run.bat
```
For Mac or Linux:
```
$ ./run.sh
```

Now the current implementation contains a couple of bugs.  You will notice
immediately after playing the game once or twice.  The bug does not manifest in
a deterministic way due to the randomness but you will notice soon enough.

So now let's use the JPF tool to try find some defects!
```
$ runJPF.bat DrunkCarnivalShooter.win.jpf
```
For Mac or Linux:
```
$ ./runJPF.sh DrunkCarnivalShooter.macos.jpf
```

The JPF tool also doesn't show any errors but that is because
DrunkCarnivalShooter takes user input and JPF does not know how to handle it.
Just like random numbers, we would like to have JPF to go over every
possibility.  We will do that by using the Verify API.  But in order to be able
to use that feature, we first have to import a library at the top of
DrunkCarnivalShooterImpl.java:

```
import gov.nasa.jpf.vm.Verify;
```

Now instead of scanning user input using the following statement:

```
int t = scanner.nextInt();
```

Exhaustively generate all possible inputs using the Verify API:

```
int t = Verify.getInt(0, 3);
```

* Invoke Verify instead of Scanner only when a commandline argument "test" is
  passed to program.  The "test" argument will put the program in test mode and
not in play mode.  You can see "test" is already configured as the commandline
argument in the target.args entry in
[DrunkCarnivalShooter.win.jpf](DrunkCarnivalShooter/DrunkCarnivalShooter.win.jpf).

The above will direct JPF to generate 4 states each where t is set to 0, 1, 2,
or 3 respectively.  Then it will systematically explore each state.  If you
wish, you can test a larger set of numbers beyond 0-3.  It is just going to
generate more states and take longer (the flipside being you will be able to
model check your program against a larger set of inputs).

Now let's try running runJPF.bat one more time like the above.  This will show
an error state with an exception:

```
====================================================== error 1
gov.nasa.jpf.vm.NoUncaughtExceptionsProperty
java.lang.ArrayIndexOutOfBoundsException: -1
        at java.util.ArrayList.elementData(java/util/ArrayList.java:422)
        at java.util.ArrayList.get(java/util/ArrayList.java:435)
        at DrunkCarnivalShooterImpl.isTargetStanding(DrunkCarnivalShooterImpl.java:78)
        at DrunkCarnivalShooterImpl.takeDownTarget(DrunkCarnivalShooterImpl.java:69)
        at DrunkCarnivalShooterImpl.shoot(DrunkCarnivalShooterImpl.java:58)
        at DrunkCarnivalShooterImpl.main(DrunkCarnivalShooterImpl.java:97)
...
```

Use the trace generated as part of the output to find the input value(s) and
the random value(s) that led to the exception.  Interpret it in the same way
you did Rand.trace.  The trace should look like:

```
====================================================== trace #1
------------------------------------------------------ transition #0 thread: 0
gov.nasa.jpf.vm.choice.ThreadChoiceFromSet {id:"ROOT" ,1/1,isCascaded:false}
      [50072 insn w/o sources]
------------------------------------------------------ transition #1 thread: 0
gov.nasa.jpf.vm.choice.BreakGenerator {id:"MAX_TRANSITION_LENGTH" ,1/1,isCascaded:false}
      [48603 insn w/o sources]
------------------------------------------------------ transition #2 thread: 0
gov.nasa.jpf.vm.choice.IntIntervalGenerator[id="verifyGetInt(II)",isCascaded:false,0..3,delta=+1,cur=0]
      [22 insn w/o sources]
------------------------------------------------------ transition #3 thread: 0
gov.nasa.jpf.vm.choice.IntIntervalGenerator[id="verifyGetInt(II)",isCascaded:false,0..2,delta=+1,cur=0]
      [64 insn w/o sources]
...
```

What would be the first choice interval 0..3?  It would be the Verify.getInt(0,
3), and in the trace it returned 0.  What would be the second choice interval
0..2?  It would be the rand.nextInt(3) used to add randomness to the shooting
target, and in the trace it also returned 0.  That should help you track down
the problem.

Once you fix these bugs, try running runJPF.bat one more time.  Now that you
have fixed the buggy state JPF runs for much longer.  In fact, JPF is going to
fall into an infinite loop and generate an infinite number of states (observed
by the ever increasing Round number).

```
...
Round #20:
  ||    ||    ||
Choose your target (0-3):
You aimed at target #0 but the Force pulls your bullet to the left.
You miss! "Do or do not. There is no try.", Yoda chides.

Round #21:
        ||    ||    ||
Choose your target (0-3):
You miss! "Do or do not. There is no try.", Yoda chides.

... (to infinity)
```

There is no theoretical limit to the number of rounds a player can play, hence
the state explosion.  How can I deal with this explosion and still verify my
program?

We have to somehow narrow down the amount of state we test, or we will be
forced to but JPF off after testing only a limited set of rounds.  Let's say
the state that we are really interested in relation to the specifications is
the state of the 4 targets.  Now if you think about it, the 4 targets can only
be in a handful of states: 2 * 2 * 2 * 2 = 16 states (standing or toppled for
each).  And this is true no matter how many rounds you go through.  The only
thing that constantly changes every round is the round number --- and that is
the culprit leading to the state explosion.  The round number is not something
we are interested in verifying right now.  So, let's filter that state out!

Import the appropriate JPF library at the top of DrunkCarnivalShooter.java again:
```
import gov.nasa.jpf.annotation.FilterField;
```
And now, let's annotate roundNum such that it is filtered out:
```
@FilterField private static int roundNum;
```

Now if we run runJPF.bat again, JPF will only go up to Round #2 and stop and declare "no errors detected".
```
...
Round #2:

Choose your target (0-3):

====================================================== results
no errors detected
```

But why Round #2?  We would expect that 4 rounds would be needed to cover all
the 16 possible states.  In fact, if you see the output, you can see it does
not cover all the possible 16 states.  And somehow the game is able to
terminate after 2 rounds.  So the game now does not throw any exceptions but
still malfunctions.  So what can be done?  Let's write a JUnit test to check
the behavior of the game against expected behavior!

Fill in the locations with TODO comments inside DrunkCarnivalShooterTest.java.
In the setUp method, use the Verify API such that you enumerate all the 16
possible states that the game can be in, as well as the target choice made by
the user (0-3).  In this way, each of your JUnit test cases will be tested on
all possible states the game can be in with all possible user inputs!

In the testShoot() method, implement the preconditions, execution steps, and
the invariant to test the shoot(targetChoice, builder) method as explained in
the method comment.  The invariant is a property that must hold no matter the
game state and the target choice.  For this test, the invariant chosen was the
remaining number of targets, because it appears that the game is ending
prematurely thinking that there aren't any more targets.

I recommend that you always insert the failString that I initialized for you in
the setUp method as the first argument of any JUnit assert call so that you get
that as part of your failure message.  For example,

```
assertEquals(failString, expected value, observed value);
```

The failString tells you the combination of game state and target choice that
led to the failure, which helps you debug the problem.  Feel free to append
additional information to the failString that may help you debug.

In order to run JUnit with JPF,
```
runJPF.bat JUnit.win.jpf
```
For Mac or Linux:
```
runJPF.sh JUnit.macos.jpf
```

If you implemented the test properly, you should see a long list of errors for different combinations.  Debug DrunkCarnivalShooterImpl to remove the errors.  Now if you play the game, you should not see any defects.

### Lessons on Model Checking

What have we learned?  We learned that a model checker such as JPF can
guarantee correctness for the given set of inputs.  But in order to do that,
you often need to limit the amount of state JPF monitors to prevent state
explosion.  Also, the guarantee of correctness depends heavily on how much of
the program specification you have encoded into your testing in the form of
assertions.  If there are no assertions, JPF can only check only basic things
such as no exceptions.

## Submission

There is no submission for this exercise.  For CheckStyle and SpotBugs, if you
don't get any more warnings you've done your job.  As to JPF, this is the
output you should expect to see.

* Result of running "runJPF.bat DrunkCarnivalShooter.win.jpf": [jpf_drunkcarnivalshooter_run.txt](jpf_drunkcarnivalshooter_run.txt)
* Result of running "runJPF.bat JUnit.win.jpf": [jpf_junit_run.txt](jpf_junit_run.txt)

Minor details like elapsed time can differ but pay special attention to the
number of new states.  In the case of the former, there are 233 new states.
For the latter, there are 141 new states.  Also, note that now the former goes
up to Round #4 to cover all possible 16 target configurations.

If you are still not sure, show me during lecture times or office hours. :)

## Resources

These links are the same ones posted at the end of the slides:

* JDK 8 installation packages:  
https://drive.google.com/drive/folders/1E76H7y2nMsrdiBwJi0nwlzczAgTKKhv7

* Java Path Finder manual:  
https://github.com/javapathfinder/jpf-core/wiki/How-to-use-JPF
http://javapathfinder.sourceforge.net/

* Java Path Finder Verify API:  
https://github.com/javapathfinder/jpf-core/wiki/Verify-API-of-JPF

* CheckStyle reference:  
https://checkstyle.sourceforge.io/checks.html  
If you don't understand a CheckStyle warning, read the corresponding entry inside google\_checks\_modified.xml under the checkstyle-jars folder and the above reference.

* SpotBugs reference:  
https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html

