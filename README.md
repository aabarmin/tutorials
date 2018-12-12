[![Build Status](https://travis-ci.org/aabarmin/self-study.svg?branch=master)](https://travis-ci.org/aabarmin/self-study)
[![codebeat badge](https://codebeat.co/badges/f0784dfc-4490-4024-88c0-bda25ba0b2f5)](https://codebeat.co/projects/github-com-aabarmin-self-study-master)
[![Maintainability](https://api.codeclimate.com/v1/badges/41112210aa66e81790ee/maintainability)](https://codeclimate.com/github/aabarmin/self-study/maintainability)
[![codecov](https://codecov.io/gh/aabarmin/self-study/branch/master/graph/badge.svg)](https://codecov.io/gh/aabarmin/self-study)

# Self Study #
I'd like to collect a few examples of code that illustrate something interesting

## Sorting ##

* Selection sort: N ^ 2 / 2
* Insertion sort: N ^ 2 / 4, N ^ 2 / 2 in a worth case
* Shell Sort: O(N ^ 3 / 2)
* Merge Sort: O(N log N)

## Gradle Cargo Plugin for local deployment

First, you need to build your application using `war` plugin and `war` task. 
Next, it's necessary to start a container and deploy your application to it. 
This particular task could be done by calling the `cargoRunLocal` task. 
This task deploys all the artifact from `deployable` closure to the Tomcat and
then starts it. 

Provided `jvmArgs` allows to connect to your local Tomcat installation using
default debugger. 

If you want to rebuild and redeploy the application you need to execute the
following steps:

1. Rebuild your application, for example, using `war` task
2. Run the `cargoRedeployLocal` task to update deployed configuration
