package com.example.gymcrmspringsecurity;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.example.gymcrmspringsecurity.steps", "com.example.gymcrmspringsecurity"},
        plugin = {"pretty"}
)
public class CucumberTestRunner {
}
