# CS 1632 - Software Quality Assurance

## Exercise 3

For this assignment, you and a partner will write a systems-level, automated black-box tests for the Reddit website using the Selenium IDE.  Specifically, we are going to test the r/cats subreddit at:

https://www.reddit.com/r/cats/

It was chosen because it is a nice safe subreddit which is policed pretty well.  Besides, it's cats.

Write a test for each requirement listed in requirements.md.  

Remember, each test must end with an assertion!  The list of available assertions and other commands are available at:

https://www.selenium.dev/selenium-ide/docs/en/api/commands

## Assertion for each Test

You will want to use the below commands and assertions to test each of the requirements:

FUN-TITLE: "assert title"

FUN-JOIN-BUTTON-EXISTS - "assert text"

FUN-SIGNUP-LINK - "store attribute" followed by "assert".  You will be storing the attribute value to a Selenium variable and asserting on the value of that variable.  Now there is a defect in Selenium IDE with the "store attribute" command where the target selector button is disabled when it should be enabled.  A work around is to enter a command such as "assert text" or "click" which allows you to use the target selector, fill in the locator string using it, and then revert to "store attribute".  As I said, while Selenium web driver is very mature, Selenium IDE is a work in progress. :)

FUN-SEARCH-SMELLY-CAT - "assert text"

FUN-RULE-6 - "assert text"

FUN-RULES-11-ITEMS - "assert element present" for the 11th item; "assert element not present" for the locator for the 12th item.

## Tips

Sometimes your test case will not work as expected.  Here are a few hints on how to debug a problem:

1. Check the Log window at the bottom of the Selenium IDE.  It will tell you which step failed for what reason (in red).
1. Select the test step that failed in the main test case window, and then select the Reference tab at the bottom pane of the IDE.  It will display usage instructions for that command.  If you need more information, refer to the link posted above.
1. Sometimes the target component of a test step is the problem.  The selector button tries to generate a locator string as best it can using xpath, css selector, or id tag.  But it is not fool proof.  The problem is, the web page may change ever so slightly on the next page load (e.g. due to a new post, or a new comment) and then the locator will stop working.  You will notice that there is a small down arrow at the end of the target text box.  If you click on that arrow, you will see alternative locator strings to the current string.  Select the one that looks specific enough to be able to point to the target but also general enough to not change between page loads.  You do need to try this out several times to get a feel of what a good locator string is.  Here is an in-depth discussion about locators:  
   https://www.seleniumhq.org/docs/09_selenium_ide.jsp#locating-elements
1. Sometimes you can use an XPATH position locator string to check that an element exists at an expected location ("assert element present") or does not exist ("assert element not present").  But to do this, you have to select the XPath position locator string in the list of target choices.
1. Try exporting your test case to Java code by pressing on the Export button at the top.  The format that is most familiar to you is probably Java (WebDriver + JUnit).  It uses Selenium WebDriver in conjunction with JUnit.  Reading the Java code gives you a good sense of what is happening behind the scenes.

## Try this out

Try exporting a test suite in Selenium IDE to a Java JUnit test class.  You can achieve this by right clicking on a test suite on Selenium IDE and then clicking on "Export" in the context menu.  I've already exported the test suite in "Reddit Cats.side" to the JUnit class "RedditCatsTest.java" for you.  You can try running the JUnit class using the provided TestRunner.java using the following script:

1. If you are running Windows:
   ```
   cd Windows
   run.bat
   ```
1. If you are running Mac:
   ```
   cd Mac
   run.sh
   ```
1. If you are running Linux:
   ```
   cd Linux
   run.sh
   ```

Note that the script only works if you have Chrome version 83 installed on your
computer (the most recent version as of today).  If you have a different
version of Chrome, you may have to update the chromedriver.exe in your
respective OS folder (Windows / Mac / Linux) by downloading a new Chrome Web
Driver from:

https://chromedriver.chromium.org/downloads

If things go properly, you will see the Chrome browser pop up repeatedly for
each test case, perform the actions, and close.  In the command line, you
should see "ALL TESTS PASSED" as usual.

Running your test case as Java code really gives you a good sense of what's
happening below the covers.  Also, if there is a test case that is particularly
hard to nail down just by using Selenium IDE, you can touch it up in the form
of exported Java code.  Selenium IDE is a tool to reduce coding effort.  But if
coding is more efficient, don't let the tool get in the way!

In addition, Selenium IDE gives the option to export a test suite to a Selenium
Grid which can run the test cases in parallel, although we will not explore
this option today.

Fortunately, the website you will test for Deliverable 3 is not very complex
and you will not need to export to Java and modify.  Selenium IDE will be good
enough.

## Submission

There is no submission for this exercise.  Please practice until you have gained confidence.
 
My test cases are stored at the "Reddit Cats.side" file.  Feel free to load
them into Selenium IDE my opening the project and try them out.  Compare with
your test cases and see if you implemented them correctly.  It is in JSON
format so you should be able to open it with a text editor and trace it with
your eyes, if that's what you prefer.

## Resources

These links are the same ones posted at the end of the slides:

* Selenium IDE Command Reference:  
https://www.selenium.dev/selenium-ide/docs/en/api/commands
