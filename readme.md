# OtusLesson End-to-End Testing Project

This project is an end-to-end testing framework built using **Java 21** and **Playwright**, designed to verify functionalities on the website [Otus.ru](https://otus.ru). It uses the **Page Object Model (POM)** pattern for maintainability and integrates with **JUnit 5**, Google Guice for dependency injection, and other tools like **SpotBugs** for code quality assurance.

---

## **Project Structure**
### **Key Packages**
1. **`pages`**: Contains classes representing different pages of the website, such as:
    - `CompanyServices.java`: Represents the company services page.
    - `CustomCourses.java`: Represents the custom courses page.
    - `SubscriptionPage.java`: Represents the subscription-related functionality.
      Each page class contains locators and methods to interact with the corresponding page.

2. **`popup`**: Contains representations for popups found in the UI, such as `LoginPopup` and `TeacherAvatarPopup`.

3. **`extensions`**: `UIExtension.java` manages the Playwright browser setup, context, and teardown for JUnit tests. It ensures clean state before and after each test case.

4. **`modules`**: The `GuicePageModule.java` provides dependency injection configuration for all page objects and other required instances (e.g., `CustomCourses`, `CompanyServices`).

5. **`tests`**: The `HomeworkTest.java` file contains test cases to verify various workflows like:
    - Teachers carousel functionality.
    - Course filtering logic.
    - Opening company services and verifying navigation.
    - Subscription verification.

---

## **Dependencies**
The project uses the following main dependencies:
1. **Playwright**: For browser automation.
2. **JUnit 5**: For writing and running test cases.
3. **Google Guice**: For dependency injection.
4. **AssertJ**: For assertions in test cases.
5. **SpotBugs**: For static code analysis and ensuring code quality.

Refer to `pom.xml` for the exact versions of dependencies.

---

## **How to Run the Project**
Follow the steps below to execute the project:
  **Prerequisites**
    - Java 21 installed and configured.
    - Maven installed (`mvn -version` to verify).
    - Optional: IDE like IntelliJ IDEA for easier development.

   **Run Tests**
   Run the test suite using Maven's `surefire` plugin:
   ```bash
   mvn clean test -DthreadsCount=4
   ```