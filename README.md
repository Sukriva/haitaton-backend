# Haitaton 2.0
Haitaton 2.0 project API for "hanke" data.

## Requirements

TODO: IntelliJ Idea, with the initialized project dependencies, has things built-in or fetches almost
everything automatically, except separate OpenJDK (for manual running). But would be nice to test
and document what is needed for a fully manual command line build from these sources. (And needed for
CI, too).

TODO: document versions (once we settle with them).

Using Idea:
* IntelliJ Idea
   * it contains its own JDK, kotlinc, gradle, etc.
* OpenJDK (version 11+) - for running things after they have been built

Manual build
* OpenJDK (version 11+)
* Kotlin compiler
* Gradle


## How to compile, build and run

TODO... Seems IntelliJ Idea will basically automatically fetch dependencies, compile, and build
most of this stuff with default settings as soon as it sees the relevant gradle stuff and correct module setting.\
However, there are some steps left (runtime stuff?)..\
Also, do check what are the manual steps needed to do the same?

Starting the application/services can be done afterwards with command line at project's root directory:
> gradlew bootRun

After the application has started, the services should be available at URLs:
> http://localhost:8080/ \
> http://localhost:8080/api/hello/

At least Firefox seems to be able to show the REST JSON results in a nice way directly.

## History

Project was initialized with [spring initializr](https://start.spring.io/), and the result added
on top of the pre-created project stub at Github (which contained only the license and short readme.md).
