Bowling Game (Cucumber, Java)
End-to-end tests for the classic Bowling Game kata using Cucumber (Gherkin) + JUnit on Java/Maven.

Tech stack
Java (JDK 8+)

Maven

Cucumber (Gherkin) + JUnit

(Optional) IDE: Eclipse with Cucumber for Java plugin

Project structure
nginx
Copy
Edit
src
├─ main
│  └─ java
│     └─ <your app / scoring classes>
└─ test
   ├─ java
│  │  └─ <step definitions / runners>
   └─ resources
      └─ features
         └─ *.feature   # Gherkin scenarios for bowling scoring
Getting started
1) Prereqs
Install JDK 8+ and Maven

(Optional) Eclipse + “Cucumber for Java” plugin

2) Clone
bash
Copy
Edit
git clone https://github.com/gastable/Bowling_Game_Cucumber.git
cd Bowling_Game_Cucumber
3) Run tests
bash
Copy
Edit
mvn test
This will compile the project and execute all Cucumber scenarios.

4) Run a single feature or tag
bash
Copy
Edit
mvn test -Dcucumber.filter.tags="@smoke"
# or
mvn test -Dcucumber.features=src/test/resources/features/PerfectGame.feature
How it works
Gherkin features describe bowling scenarios (e.g., all strikes = 300, all misses = 0, spares, mixed frames).

Step definitions map steps to Java code that drives the scoring logic.

Scoring classes implement the ten-pin rules; Cucumber verifies expected totals.

Typical scenarios
Perfect game: 12 strikes → score 300

All open frames (e.g., 9- each frame) → 90

All spares with 5 bonus → 150

Build & CI
Local: mvn -q -DskipTests package

Add CI later (e.g., GitHub Actions) to run mvn -B test on each push/PR.
