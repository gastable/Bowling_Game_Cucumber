# 🎳 Bowling Game (Cucumber + Java)

An implementation of the classic **Bowling Game** kata using **Cucumber (Gherkin)** and **JUnit** in Java.  
This project demonstrates **Behavior-Driven Development (BDD)** for testing bowling score calculation.

---

## 📦 Tech Stack
- **Java** (JDK 8+)
- **Maven** (build & dependency management)
- **Cucumber (Gherkin)** + **JUnit** (BDD framework)
- *(Optional)* **Eclipse IDE** with Cucumber plugin

---
## 📂 Project Structure

```
src
├─ main
│ └─ java
│ └─ <scoring logic classes>
└─ test
├─ java
│ │ └─ <step definitions / runners>
└─ resources
└─ features
└─ *.feature # Gherkin scenarios
```
## How It Works
Feature files describe bowling scenarios in Gherkin syntax.

Step definitions map Gherkin steps to Java code.

Scoring logic implements ten-pin bowling rules.

JUnit runner executes the features and verifies expected results.

## 📝 Example Scenarios

| Scenario                     | Expected Score |
|------------------------------|----------------|
| Perfect game (12 strikes)    | 300            |
| All spares with 5 pins bonus | 150            |
| Open frames only (e.g., 9-)  | 90             |

