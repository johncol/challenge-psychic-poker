# Psychic Poker Solution

This is my approach to solve the btccom [poker psychic challenge](https://github.com/btccom/Hire/blob/master/exercise/psychic-poker-en.md).

I wrote it using java 9, so you'll need the java 9 JDK installed on your machine to build it and run it.

## Tests

On a terminal in the project root folder, run

    mvn test

## Build

To create an executable jar, run

    mvn clean package

This will create, inside a target folder, a jar file called psychic-poker-1.0-SNAPSHOT.jar.

## Run

To launch the application, build the jar, and inside the target folder, run

    java -jar psychic-poker-1.0-SNAPSHOT.jar <ABSOLUTE_OR_RELATIVE_PATH_TO_FILE_WITH_TEST_CASES>

As an example, inside the target/test-classes folder the sample-input provided in the challenge was written in a sample-input.txt file.

    java -jar psychic-poker-1.0-SNAPSHOT.jar ./test-classes/sample-input.txt
