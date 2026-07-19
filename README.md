# FinalSurge UI Automation Tests

Automated UI testing project for the **FinalSurge** web application.

The project is built using **Java + Selenide + TestNG** following the **Page Object Model (POM)** design pattern.

---

## Project Overview

The project covers automated end-to-end testing of the main FinalSurge functionality:

- Authorization
- Workout Management
- Shoes Management
- Workout Calculator
- Athlete Reports

The framework supports:

- Page Object Model
- Allure Reporting
- Retry mechanism
- TestNG Listeners
- Faker-generated test data
- Parallel execution
- GitHub Actions integration

---

## Technology Stack

| Technology | Version |
|------------|----------|
| Java | 17 |
| Maven | Latest |
| TestNG | 7.12.0 |
| Selenide | 7.16.2 |
| Selenium WebDriver | via Selenide |
| Allure Report | 2.24.0 |
| Allure Selenide | 2.24.0 |
| Lombok | 1.18.46 |
| JavaFaker | 1.0.2 |
| Log4j2 | 2.26.0 |

---

## Project Structure

```
src
 ├── main
 │
 └── test
      ├── models
      ├── pages
      ├── tests
      ├── listeners
      ├── utils
      ├── factory
      └── resources
```

---

## Test Coverage

### Authorization

- ✅ Verify successful authorization with valid credentials
- ✅ Verify authorization with invalid credentials
- ✅ Verify user logout

---

### Workouts

#### Workout Creation

- ✅ Quick adding workout via Calendar
- ✅ Full adding workout via Full Add
- ✅ Negative adding workout without required fields
- ✅ Create future workout
- ✅ Create past workout

#### Workout Management

- ✅ Update workout
- ✅ Delete workout

---

### Workout Calculator

- ✅ Calculate running pace using Workout Intensity Calculator

---

### Shoes

- ✅ Add new shoe with required fields
- ✅ Verify validation for empty required fields
- ✅ Edit shoe
- ✅ Delete shoe

---

### Athlete Workout Report

- ✅ View workout report

---

## Design Pattern

The framework uses the **Page Object Model (POM)**.

```
Test
   ↓
Page
   ↓
Selenide
```

Additional patterns:

- Builder
- Factory
- Fluent Interface

---

## Reporting

The project uses **Allure Report**.

Generate report

```bash
mvn allure:report
```

Open report

```bash
mvn allure:serve
```

---

## Running Tests

Run all tests

```bash
mvn clean test
```

Run regression suite

```bash
mvn clean test -DsuiteXmlFile=src/test/resources/regression.xml
```

Run smoke tests

```bash
mvn clean test -Dgroups=smoke
```

Run tests in headless mode

```bash
mvn clean test -Dheadless=true
```

---

## Test Data

Random test data is generated using **Java Faker**.

Example:

- Shoe Name
- Brand
- Model
- Price
- Notes

---

## Logging

Logging is implemented with **Log4j2**.

Logs contain:

- Test start
- Test finish
- Retry attempts
- Failed tests
- Screenshots
- Page Source

---

## CI/CD

The project supports execution through **GitHub Actions**.

Workflow includes:

- Checkout repository
- Setup JDK 17
- Maven build
- Test execution
- Allure Results generation

---

## Main Dependencies

```xml
Java 17
TestNG 7.12.0
Selenide 7.16.2
Allure 2.24.0
Lombok 1.18.46
Java Faker 1.0.2
Log4j2 2.26.0
```

---

## Author

Veranika Slesarchyk
