#!/bin/bash
sudo apt update -y && sudo apt upgrade -y
sudo apt install openjdk-17-jre -y && sudo apt install openjdk-17-jdk -y
sudo wget -O /tmp/apache-maven-3.8.4-bin.tar.gz https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz
sudo tar xf /tmp/apache-maven-*.tar.gz -C "/opt"
sudo ln -s /opt/apache-maven-3.8.4 /opt/maven
export JAVA_HOME=/usr/lib/jvm/java-1.17.0-openjdk-amd64
export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=${M2_HOME}/bin:${PATH}touch
mvn clean compile assembly:single
