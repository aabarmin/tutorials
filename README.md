# Self Study #
I'd like to collect a few examples of code that illustrate something interesting

## Sorting ##

* Selection sort: N ^ 2 / 2
* Insertion sort: N ^ 2 / 4, N ^ 2 / 2 in a worth case
* Shell Sort: O(N ^ 3 / 2)
* Merge Sort: N log N

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
