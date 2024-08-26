# Project Title: Automation of OrangeHRM Demo Using TestNG 

### Project Summary: Orange HRM is a Human resource management system that has been automated in this project. The tests for Login module have been automated for both admin and employees created by admin. Then, the employee creation process by admin has been automated and tested by searching newly created employees by their names and employee IDs. Lastly, the Employee module has been tested by automating the setting of gender and blood group of employees through their profiles. 

## Prerequisites  
* JDK 11
* IntelliJ IDEA
* Gradle
* Libraries:
  * Selenium
  * TestNG
  * Allure
  * JSON.simple
  * Java Faker

## How to setup the environment?  
* Copy library name from the Gradle section on [https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java) and paste it in build.gradle file in the project folder
* Copy library name from the Gradle section on [https://mvnrepository.com/artifact/org.testng/testng](https://mvnrepository.com/artifact/org.testng/testng) and paste it in build.gradle file in the project folder
* Copy library name from the Gradle section on [https://mvnrepository.com/artifact/io.qameta.allure/allure-testng](https://mvnrepository.com/artifact/io.qameta.allure/allure-testng) and paste it in build.gradle file in the project folder
* Copy library name from the Gradle section on [https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple](https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple) and paste it in build.gradle file in the project folder
* Copy library name from the Gradle section on [https://mvnrepository.com/artifact/com.github.javafaker/javafaker](https://mvnrepository.com/artifact/com.github.javafaker/javafaker) and paste it in build.gradle file in the project folder
* Click on the gradle icon on IntelliJ IDEA
* Click on the 'Reload All Gradle Projects' icon

## How to run?  
Run the following command in the terminal inside the project directory:  
`gradle clean test`

## How to generate Allure report?  
Run the following command in the terminal inside the project directory:  
* `allure generate allure-results`  
* `allure serve allure-results`

## Output  
### Report 
#### Overview
![allure_report](https://github.com/user-attachments/assets/31ba1a86-5c7d-4b74-8370-3ab2db85eccf)  

#### Behaviors
![allure_report_behavior_v3](https://github.com/user-attachments/assets/5c299ae8-5c98-45e5-8e40-7c4fedb4ebfb)

### Test Cases
Please click the link to view the test cases:  
https://docs.google.com/spreadsheets/d/11Wnky-CMHicVizqBDMaNdL6fwkhhp5focviEeWb6k28/edit?usp=sharing

### Demonstration video
Please click the link to watch the video:  
https://drive.google.com/file/d/1Edm2PExKyLRol4APveshlJntcYX1bWIo/view?usp=sharing
