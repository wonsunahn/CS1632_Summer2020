javac -d bin -cp src src/DrunkCarnivalShooter.java
javac -d bin -cp src src/DrunkCarnivalShooterImpl.java

java -jar spotbugs-4.0.0-beta4/lib/spotbugs.jar -textui -low -effort:max -longBugCodes bin/*.class
