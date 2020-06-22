mkdir bin

javac -d bin -cp "quickcheck-jars/*" src/*.java

java -cp bin MonkeySim $1
