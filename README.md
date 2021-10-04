# CodeDA

Cucumber- Mobile - API-WebPortal
A Maven framework in which to build cucumber tests written in Java with Junit, extent reports of test results.
Getting Started :
Copy the repo into your local machine.
Tools used :
1. 1.	IDE Eclispe /IntelliJ should be installed.
2. 2.	Java should be installed.
3. 3.	Cucumber - BDD 
4. 4.	Maven should be installed.
5. 5.	Using Junit framework for Test Cases
*******Run tests locally
Right click the <testng>.xml file and select "Run as TestNG Suite" to start the test.
********Run tests through the commandline
As this project uses Maven, we can invoke the tests using Maven goals. To run the test, use your CI or point Maven to the project and use the goals:
run command
cd << Project Path>>

mvn clean test -PWebBackEndTest

mvn clean test -PMobileTestng.xml
