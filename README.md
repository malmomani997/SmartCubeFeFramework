SmartCube Automation Framework

This repository contains a robust, scalable Selenium WebDriver automation framework designed for automating the testing of the PetStore web application. It employs the Page Object Model (POM), TestNG for test execution, ExtentReports for reporting, and supports cross-browser testing and Docker execution.

🚀 Key Features

Page Object Model (POM): Clean separation of page locators and test scripts.

Data-Driven Testing: JSON-based test data providers using Jackson.

Cross-Browser Testing: Supports Chrome, Firefox, and Edge.

Docker Compatible: Configured for headless execution in CI/CD pipelines.

Detailed Reporting: Integrated ExtentReports with automated screenshots on failures.

Retry Mechanism: Automatically retries failed tests.

📂 Project Structure

SmartCubeFeFramework/
├── src/
│   └── main/
│       └── java/
│           └── Base/
│               ├── DTOs/
│               ├── PagesHelper/
│               ├── Resources/
│               ├── SharedElements/
│               ├── TestHelpers/
│               └── dataProviders/
├── reports/
├── screenshots/
├── pom.xml
└── testng.xml

🛠️ Tech Stack

Selenium WebDriver

TestNG

WebDriverManager

ExtentReports

Jackson Databind

Apache Commons IO

Lombok

⚙️ Installation & Execution

Prerequisites

Java JDK 11+

Maven

Running Tests

Execute tests using Maven:

mvn clean test

Specify a browser (default: Chrome):

mvn clean test -Dbrowser=firefox

Docker Execution

The framework is pre-configured for Docker execution with headless browsers.

📊 Test Reports

Detailed reports are generated automatically after each test execution. Access the reports at:

/reports/index.html

📝 Test Scenarios Included

User Registration

User Login

Product Selection and Checkout

🔄 Retry Mechanism

Failed tests automatically retry once to manage flaky scenarios, configurable via Retry.java.

📸 Screenshots

Screenshots on test failures are automatically captured and stored in:

/reports/screenshots/

📈 Future Improvements

Enhanced logging

Extended browser support

Additional integration with CI/CD tools

Happy Testing! 🚀
