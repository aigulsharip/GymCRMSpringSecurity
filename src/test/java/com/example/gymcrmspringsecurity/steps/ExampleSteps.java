package com.example.gymcrmspringsecurity.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ExampleSteps {

    @Given("I have a configured Cucumber project")
    public void i_have_a_configured_Cucumber_project() {
        System.out.println("Given: I have a configured Cucumber project");
    }

    @When("I run the tests")
    public void i_run_the_tests() {
        System.out.println("When: I run the tests");
    }

    @Then("I should see the output")
    public void i_should_see_the_output() {
        System.out.println("Then: I should see the output");
    }
}
