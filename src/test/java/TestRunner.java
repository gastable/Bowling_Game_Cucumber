
/*
   Filename: TestRunner.java
     Author: Minyen Huang
       Date: Apr 5, 2025 6:55:37 PM
Description: This program runs cucumber test cases
 */

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src\\test\\java\\features\\BowlingGameFeature.feature"},
		glue = {"steps"})
public class TestRunner
{

}
