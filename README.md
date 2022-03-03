- [Getting Started](#getting-started)
  - [Requirements](#requirements)
    - [<b>Java Runtime Environment</b>](#bjava-runtime-environmentb)
    - [<b>Java Development Kit</b>](#bjava-development-kitb)
    - [<b>Maven</b>](#bmavenb)
  - [Build](#build)
    - [`mvn clean package`](#mvn-clean-package)
  - [Run](#run)
    - [`java -jar demo-0.0.1-SNAPSHOT.jar`](#java--jar-demo-001-snapshotjar)

# Getting Started

## Requirements

### <b>Java Runtime Environment</b>

The Java Runtime Environment (jre) is required to packaged JAR files as java applications.

The pom.xml file for this project targets java 11 so it is recommended that you use the latest patch of jre 11 but any jre above 11 should run the built project okay.
<br/>

### <b>Java Development Kit</b>

The Java Development Kit (jdk) is required to build java projects so that the built JAR files can be run as out java applications.

The pom.xml file for this project targets java 11 so it is recommended that you use the latest patch of <i>jdk 11</i> but any jdk above 11 should run build the project without issue.

### <b>Maven</b>

Maven is the dependency manager for this project and is required to fetch the dependencies needed to build (this occurs automatically) and package the project according to rules set out in pom.xml.

It is strongly recommended to use <i>version 3.8.4+</i> as there are know compatability issues with earlier versions.

## Build

### `mvn clean package`

Runs the junit and cucumber tests and builds at `./target`, there are 2 primary produced items of interest:

- `jacoco.exec` - Coverage report for SonarCube
- `demo-0.0.1-SNAPSHOT.jar` - Packaged jar file

## Run

### `java -jar demo-0.0.1-SNAPSHOT.jar`

This will start the tomcat server listening for http requests on <i>port 8080</i> and allow the Springboot application to process these requests.
