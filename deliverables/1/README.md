# CS 1632 - Software Testing
Summer Semester 2020

* DUE: June 1, 2020 06:00 PM

## Deliverable 1

For this assignment, your group will determine a test plan for the simple game
Coffee Maker Quest, based on the requirements listed.  There are several known
defects in the software; you will need to find and report on at least three.
Additionally, a traceability matrix showing the mapping of test cases to
requirements is required.

There should be two and only two members in a group.  You may choose to work on
the assignment alone if that's what you prefer.

There should be at least one test case per requirement, although I would
normally expect several test cases per requirement.  

Each requirement should have AT LEAST one test case associated with it in order
to have good test coverage.  Each test case should have AT LEAST one
requirement associated with it (no orphaned test cases).  One test case may
happen to test more than one requirement using a single set of inputs, and that
is fine.  The above can easily be checked via a traceability matrix (which you
should also deliver). 

Test cases should mention all necessary preconditions, execution steps, and postconditions.

I expect you to test several edge and corner cases as part of the test plan.
If you do this, I'd estimate that the number of test cases is at least 2x the
number of requirements.  If the number of test cases is more than 3x the number
of requirements, you are probably overtesting.

You are expected to execute the test plan in order to find the defects.  There
are AT LEAST three.  Full credit will be given only to those who properly find
and describe at least three.  While you are not expected to find all of the
defects, a reasonable test plan should definitely find at least three.  This is
an intentionally target-rich environment.

## Format
The report should start with a cover page with:
* The name of the project under test
* The names of the people in the group
* The title "CS 1632 - DELIVERABLE 1: Test Plan and Traceability Matrix"

There should be a short introduction (a few paragraphs) in which you may note
any concerns or difficulties you may have had or anticipate with the testing
process.  You should also note why you considered certain test cases, how you
came up with edge cases, etc.

ON A NEW PAGE, a traceability matrix should be provided mapping the test cases
with their associated requirements.  Remember that all requirements should map
to AT LEAST ONE test case, and all test cases should map to AT LEAST ONE
requirement.  

ON A NEW PAGE, a list of the actual test cases should follow.  You may name
them any way you wish, but please be consistent.  Please write them out in this
format -

	IDENTIFIER:
	TEST CASE: 
	PRECONDITIONS:
	EXECUTION STEPS:
	POSTCONDITIONS:

ON A NEW PAGE, list at least three defects found.  The defects should follow
the defect reporting template:

	 IDENTIFIER:
	 SUMMARY:
	 DESCRIPTION:
	 REPRODUCTION STEPS:
	 EXPECTED BEHAVIOR:
	 OBSERVED BEHAVIOR:

Other attributes of a defect (e.g., SEVERITY or IMPACT) are not necessary.
Don't forget to include any preconditions at the beginning of the REPRODUCTION
STEPS.

## Coffee Maker Quest

Coffee Maker Quest is a simple game.  The goal is get coffee, sugar, and cream,
and then drink it so that you can stay up and study.  In order to do so, you
need to visit several rooms in a house and look around.  Once you have obtained
all the necessary elements, you win.  If you decide to drink before getting all
of the necessary elements, you lose.

You can run it downloading the coffeemaker.jar file and running:
```bash
java -jar coffeemaker.jar
```

The requirements are listed in the file requirements.txt.

## Tips

Please read [deliverable1-tips.md] if you are confused.

## Grading
* Introduction: 10% of grade
* Test Plan: 40% of grade
* Traceability Matrix: 20% of grade
* Defects Found and Described: 30% of grade

Please review the grading_rubric.txt for details.

## Submission

Please use the ReportTemplate.docx file provided in this directory to write
your report.  If you don't have a .docx compatible word processor, that's
perfectly fine as long as you follow the same organization.  A PDF version of
the file is at ReportTemplate.pdf.  Please make sure that the intro,
traceability matrix, test cases, and defects are on seperate pages.  You will
be submitting to GradeScope in PDF format.  When you submit, you will be asked
to assign pages in the PDF file to each rubric item: 1. Introduction, 2.
Traceability Matrix, 3. Test Cases, and 4. Defects.

Each pairwise group will submit the exercise *once* to GradeScope, by *one
member* of the group.  The submitting member will press the "View or edit
group" link at the top-right corner of the assignment page after submission to
add his/her partner.  Make sure that your partner is there, or he/she will not
get a grade.

Please feel free to email me at wahn@pitt.edu or come to office hours to
discuss any problems you have. 
 
