# 🐾 PetStore Automation Testing Framework (SmartCubeFeFramework)

An automation testing framework built using **Java**, **Selenium WebDriver**, and **TestNG**, following the Page Object Model (POM) design pattern. This framework simplifies automated test creation, execution, and reporting for web applications—demonstrated with the PetStore example application.

---

## 🚀 Overview

This test automation framework includes:

- **Page Object Model (POM)** structure for easy maintainability and scalability.
- Support for multiple browsers (**Chrome, Firefox, Edge**) with headless options.
- Robust test data management using JSON files and DataProviders.
- Detailed logging and reporting using **ExtentReports**.
- Screenshot capturing upon test failures.
- Integration with TestNG and annotations for flexible test execution.

---

## 🛠️ Framework Components

### **1. DTOs (`SCDTO`)**
- Maps JSON test data to Java objects using Jackson annotations.

### **2. Shared Elements (`SharedElementsHelper`)**
- Encapsulates common WebDriver operations:
  - Wait utilities (visibility, clickable, etc.)
  - Element interactions (clicks, scrolling, actions)
  - Element validations

### **3. Page Helpers**
- **`AbstractPageHelper`**:
  - Base class for all Page Helpers, initializing web elements via Selenium’s `PageFactory`.

- **Page-specific helpers**:
  - Encapsulate specific page logic and elements (`PetStoreHomePageHelper`, `PetStoreRegistrationPageHelper`, `PetStoreCheckoutPageHelper`).
  - Clearly annotated Selenium web elements with `@FindBy`.
  - Simplified interactions through dedicated methods (e.g., `completeLogin()`, `fillRegistrationFormAndSubmit()`).

### **4. Test Helpers (`BaseTest`)**
- Browser setup and teardown:
  - Uses WebDriverManager.
  - Supports parallel execution via ThreadLocal.
  - Captures screenshots automatically upon test failure.

- Includes extensive configuration options for Chrome, Firefox, and Edge browsers.

### **5. Data Providers (`DataGenerator`)**
- Dynamically loads and provides test data from JSON files based on the executing test method.
- Supports easy extension for multiple DTO types.
- Provides clear logging and error handling for robust debugging.

### **6. Test Execution (`SCTestCases`)**
- Includes clear test methods demonstrating:
  - User registration flow.
  - User login validation.
  - Checkout process with item addition and price validation.

### **7. Retry Analyzer (`Retry`)**
- Implements automatic test retry logic for transient failures.

---

## 🚦 Execution Instructions

### **Prerequisites:**

- **Java 17** (or higher)
- **Maven**
- **Selenium WebDriver**
- **WebDriverManager**
- **TestNG**
- **ExtentReports**
- **Jackson** (for JSON parsing)
- **Lombok** (for cleaner code)

### **Installing Dependencies**

Execute the following Maven command in your project root:

```shell
mvn clean install
```

## 📊 Test Reports

### ExtentReports:
- Automatically generated after each test suite execution.
- Stored in the `reports/` directory.

### Screenshots:
- Automatically captured upon test failures.
- Stored in the `screenshots/` directory.

---

## ⚙️ Continuous Integration (CI)

This project fully supports integration with CI/CD tool :

- **GitHub Actions**

---

## 🗃️ Data-Driven Testing

Test data is stored in JSON format at:

```shell
/src/main/java/Base/dataProviders/ScTestData.json

