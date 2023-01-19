# Spring Security for Spring Boot Rest API

This repository is to showcase Authentication and Role Base Access Control (RBAC) for Spring Boot Rest API using Spring Security.

## Github Actions Build status
[![Build Status](https://github.com/harishkannarao/java-reactive-rest-service/workflows/CI-main/badge.svg)](https://github.com/harishkannarao/java-reactive-rest-service/actions?query=workflow%3ACI-main)

## Required Softwares, Tools and Version
* Java JDK Version: 17
* Gradle Version: 7
* Git Client: Any latest version

## Running the build

    ./gradlew clean build
    
## Running the application using build tool

    ./gradlew bootRun

    http://localhost:8080/general-data

## Run the sample application using java
    
    ./gradlew clean assemble

    java -jar build/libs/spring-security-rest-api.jar

    http://localhost:8080/general-data

## Run unit tests

    ./gradlew clean test

## Run integration tests

    ./gradlew clean integrationTest

## Run feature toggle integration tests

    ./gradlew clean ftIntegrationTest
