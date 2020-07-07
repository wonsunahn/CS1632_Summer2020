# CS 1632 Midterm 2 Exam Study Guide - Summer 2020

Midterm 2 is between July 19 (Sunday) 10:00 AM ~ July 20 (Monday) 06:00 PM.

It will be posted on GradeScope just like before and you will have a 3 hour
span to complete the exam at a time of your own choosing.  Remember that while
the exam is open book, you are forbidden from getting help from anybody, as
usual.  Also, during the exam period, you will not be able to ask me any
questions about the exam except clarification questions.

The midterm will cover everything we have covered since Midterm 1, starting
from Automated System Testing.  I recommend you review the slides and the
textbook (See syllabus.md for which chapters are required reading.  The reading
is also on the last slide of each lecture).  You are also expected to have
completed Exercises 3, 4, 5, and 6 be able to answer related questions.

The exam will consist of these types of questions:
  * Multiple choice questions
  * Fill in the blank questions
  * Short answer questions (explain a concept, discuss pros/cons, etc.)
  * Code tracing questions

Here are the key topics to study in preparation for the test.

_Note: the items in italic are learning goals that require application of your
knowledge.  Either you have to apply your knowledge to a piece of code, or you
need to apply an algorithm to a specific problem._

## AUTOMATED (WEB) SYSTEMS TESTING
* Be able to explain why testing a GUI web application involves a lot of text
  testing, just like text UI applications.
* Be able to discuss reasons why testing an HTML verbatim against an expected
  HTML page is not desirable and how Selenium solves those problems.
* Be able to explain why in Selenium, there is an option to select
  different locator strings for the same target element.

## PERFORMANCE TESTING
* Be able to compare different definitions of speed: throughput,
  responsiveness, and utilization
* Be able to apply appropriate definition of speed according to context (type
  of application).
* Be able to define performance indicator, KPIs, performance targets,
  performance thresholds.
* Be able to explain different categories and subcategories of performance
  indicators: service-oriented indicators / efficiency-oriented indications.
Also, be able to explain the reasoning behind the categorization.
* Be able to discuss reasons why statistical reasoning is mandatory when
  measuring response time.  Be able to list examples of factors that can cause
variability when measuring response time.
* Be able to define real time (wall clock time), user time, system time, and
  total time accurately.  Be able to explain why certain types of time may be
important when measuring certain performance indicators.
* Be able to explain what the "nines" notation means for availability.
* Be able to explain why availability is hard to measure directly.
* Be able to explain how baseline, soak (stability), and stress load testing
  can help model availability.
* Be able to explain what value efficiency-oriented indicators provide beyond
  service-oriented indicators, which already measure user experience.
* Be able to differentiate between general purpose and program-specific
  utilization measurement tools.

## STOCHASTIC / PROPERTY-BASED TESTING
* Be able to define accurately what stochastic testing is.
* Be able to define accurately what property-based testing is.
* Be able to explain why property-based testing is necessary for stochastic
  testing.
* Be able to define what an invariant is.
* Be able to discuss the advantages / disadvantages of property-based testing.
* Be able to explain why "shrinking" in quickcheck is useful for debugging.
* Be able to define what fuzz testing is.
* Be able to explain why complete random input generation when fuzz testing is
  ineffective, using an example.
* _Be able to come up with invariants of your own given a piece of (pseudo)
  code._

## STATIC TESTING PART 1
* Be able to discuss the pros / cons of static testing compared to dynamic
  testing.
* Be able to discuss why choice of language is important for compiler static analysis.
* Be able to explain the differences between different types of code coverage.
* Be able to explain why 100% code coverage necessarily does not mean defect
  free.
* Be able explain similarities between linters and bug finders (pattern
  matching) and the differences (usage).

## STATIC TESTING PART 2
* Be able to define what formal verification is.
* Be able to explain how theorem proving seeks to achieve formal verification.
* Be able to explain how model checking seeks to achieve formal verification.
* Be able to discuss pros / cons of theorem proving compared to model checking.
* Be able to compared the similarities and differences of model checking
  compared to property-based testing.
* Be able to explain how backtracking and state matching both help in make
  state space exploration efficient for model checkers.
* Be able to explain what the state explosion problem is.
* _Given code, be able identify parts of it that leads to state space explosion._
* _Given a piece of code, be able to create a state transition diagram._
* _Given a state transition diagram, be able to draw backtracking arrows._

## STATIC TESTING PART 3
* Be able to explain how symbolic execution can drastically reduce the size of
  the state space.
* Be able to explain what role a constraint solver plays in symbolic execution.
* Be able to explain the deficiencies of symbolic execution and why it cannot be easily applied to all programs.
* _Given code, be able to trace through the code, creating a symbolic
execution tree in the process.  Each statement in the tree should have an
associated path condition.  Also, if the statement is an assignment, a symbolic
expression should be assigned to the variable instread of a concrete value._

## PAIRWISE / COMBINATORIAL TESTING
* Be able to interpret the results of the NIST study on the frequency of
  defects for various numbers of variable interactions.
* Be able to define what pairwise testing is and what combinatorial testing is.
* Be able to define what a covering array is.
* Given that the size of the covering array is O(v^t * logk), be able to
  explain its implications; i.e. why it is possible to do combinatorial testing
with good defect coverage even for large programs in terms of v, t, k.
* _Be able to create a covering array for the given list of parameters and the
  given number of interactions (t)._

## SMOKE / EXPLORATORY TESTING
* Be able to explain why smoke testing can help the QA team to work more efficiently.
* Be able to tell other names for smoke testing.
* Be able to explain how build verification testing is different the unit
  testing during TDD and what value it adds.
* Be able to discuss the choice you have on when BVT is triggered and the trade-offs.
* Be able to explain what happens on a BVT pass or fail.
* Be able to explain clearly why BVT has to be fast (around 10 minutes).
* Be able to explain the situation where exploratory testing may be needed.
* Be able to compare the pros/cons of exploratory testing.

## SECURITY TESTING
* Be able to list the Infosec (CIA) triad
* Be able to tell which of the triad a particular attacks
  tries to compromise (confidentiality, integrity, availability).
* Be familiar with the terminology of vulnerability, exploit, attack.
* Be able to tell the differences between the numerous types of malicious code
  and be familiar with the terminology.
* Be able to explain how each of the 8 common types of attacks are perpetrated
  and possible protections.
* Be able to explain what Single Origin Policy (SOP) is in web security.
* Be able to explain how cross-site scripting circumvents SOP.
