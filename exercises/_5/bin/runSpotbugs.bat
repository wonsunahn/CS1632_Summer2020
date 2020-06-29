javac DrunkCarnivalShooter.java
javac DrunkCarnivalShooterImpl.java

java -jar spotbugs-4.0.0-beta4/lib/spotbugs.jar -textui -low -effort:max -longBugCodes DrunkCarnivalShooterImpl.class
