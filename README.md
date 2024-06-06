# Welcome to my Selenium Framework! 😊
This is my go-to solution for efficient web application testing. With this framework, I've focused on providing a comprehensive set of features to make testing a breeze.

## Key Features: 🚀
- Parallel Execution: ⚡ I've implemented parallel execution to speed up testing, making the most of my resources and reducing overall execution time.
- Allure Reports: 📊 Detailed Allure reports are generated automatically, giving me valuable insights into test results and helping me analyze and report effectively.
- UI and API Testing: 🔍 Whether it's UI or API testing, my framework has got it covered. I can ensure thorough testing across different layers of my application.
- TestNG and JUnit 5 Integration: 🔧 Seamless integration with TestNG and JUnit 5 provides me with flexibility and compatibility, allowing me to choose the testing framework that suits my needs.
- Modular Structure: 🧩 I've designed the framework with a modular structure, making maintenance, scalability, and reusability of test cases and components a breeze.
- Configuration Management: ⚙️ Robust configuration management ensures that I can easily handle various test environment configurations, enhancing the reliability and consistency of my tests.
- Logging and Reporting: 📜 Detailed logging and reporting mechanisms help me capture crucial test execution details, errors, and exceptions, enabling thorough analysis and debugging.
- Jenkins Integration: 🔄 The tests are seamlessly integrated with Jenkins, allowing for continuous integration and delivery. Jenkins pulls the tests directly from this repository, ensuring that the latest tests are always executed.

## Getting Started: 🛠️
To get started with my Selenium Framework, follow these simple steps:

- Clone the repository to your local machine.
- Set up your favorite IDE with the necessary dependencies.
- Configure the framework according to your project requirements, including test environment settings and browser configurations.
- Start writing your test scripts using the provided templates and utilities.
- Execute your tests locally or integrate them into your CI/CD pipeline for automated testing using Jenkins.

## Prerequisites: 📋
- Java 17: Make sure you have Java 17 installed. You can download it from the official site.
- Maven: Ensure Maven is installed. You can download it from the official site.
With these features, my Java Selenium Framework provides a robust and efficient solution for all your web application testing needs. Happy Testing! 🎉

## Running Tests: 🚀
You can run the tests in my framework using different methods:

*Using Maven:*
You can execute the tests using Maven with the following command:

`mvn clean test -Dbrowser=BROWSERNAME`
Replace **BROWSERNAME** with **CHROME**, **FIREFOX**, or **EDGE** (case sensitive) to specify the browser for the test execution. For example:

`mvn clean test -Dbrowser=CHROME`

*Using TestNG XML:*
You can also run the tests by specifying the TestNG XML file. This method allows you to define test configurations and suites in an XML file.

`Right-click on the testng.xml file.`

Select Run '...\testng.xml' from the context menu.
![Screenshot_1](https://github.com/YehorYehorychev/Selenium-Framework/assets/131714384/e17a3e23-318b-4006-a756-3bb6967a6160)

## Jenkins Integration: 🔄
To integrate with Jenkins, follow these steps:

- Create a Jenkins job and configure it to pull the latest code from your repository.
- Set up the build steps to execute the tests using Maven commands as described above.
- Configure post-build actions to generate reports and notifications based on the test results.
- With these features and instructions, my Java Selenium Framework provides a robust and efficient solution for all your web application testing needs. Happy Testing! 🎉
