## Intro 
Imagine you have a commandline app to show tomorrow's forecast using public API: https://www.metaweather.com/api/

Sample output:
```
$ forecast dubai

Tomorrow (2019/05/01) in Dubai:
Clear
Temp: 26.5 °C
Wind: 7.6 mph
Humidity: 61%
```

## Task
* How will you test the app? Write 1-2 automated tests to prove the correct work of application.
* Ideally, tests should not touch the real service and work without the Internet.
* Bonus task. Create CI pipeline with GitHub Actions or any alternative.


### **_Steps:_**
##### **_Build:_**
- Clone project : git clone https://github.com/DevendraJosan11/hotels-api-testing 
- Import as maven project into IntelliJ IDE.
- Navigate to project directory and Build project by running following command in terminal : **_mvn clean install_**.

##### **_Execution:_**
- To run test class : Right click on test class name or within test class and select **_Run ClassName_**.
- To run individual test : Right click on test case name and select **_Run TestName_**
- To run all tests : Right click on tests(**com.restassured.tajawal.tests**) and select **Run 'Test in 'com.restassured.tajawal.tests''**
    
##### **_Generate Reports:_**
- Run following command to view allure reports: **_allure serve_**.
