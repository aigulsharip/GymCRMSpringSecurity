package com.example.gymcrmspringsecurity.steps;

import com.example.gymcrmspringsecurity.entity.trainee.Trainee;
import com.example.gymcrmspringsecurity.repository.TraineeRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TraineeManagementSteps {

    @Autowired
    private TraineeRepository traineeRepository;

    private Trainee trainee;

    @Given("the trainee details are provided")
    public void theTraineeDetailsAreProvided() {
        trainee = new Trainee();
        trainee.setFirstName("Peter");
        trainee.setLastName("Doe");
        trainee.setUsername("peterdoe" + UUID.randomUUID());
        trainee.setPassword("password");
        trainee.setIsActive(true);
        trainee.setDateOfBirth(LocalDate.of(1990, 1, 1));
        trainee.setAddress("123 Main St");
    }

    @When("the trainee is created")
    public void theTraineeIsCreated() {
        trainee = traineeRepository.save(trainee);
    }

    @Then("the trainee should be present in the database")
    public void theTraineeShouldBePresentInTheDatabase() {
        assertTrue(traineeRepository.findById(trainee.getId()).isPresent());
    }

    @Given("a trainee is created with the details")
    public void aTraineeIsCreatedWithTheDetails() {
        trainee = new Trainee();
        trainee.setFirstName("Mary1");
        trainee.setLastName("Doe");
        trainee.setUsername("marydoe" + UUID.randomUUID());
        trainee.setPassword("password");
        trainee.setIsActive(true);
        trainee.setDateOfBirth(LocalDate.of(1992, 2, 2));
        trainee.setAddress("456 Main St");
        trainee = traineeRepository.save(trainee);
    }

    @When("the trainee is retrieved by ID")
    public void theTraineeIsRetrievedByID() {
        trainee = traineeRepository.findById(trainee.getId()).orElse(null);
    }

    @Then("the correct trainee details should be returned")
    public void theCorrectTraineeDetailsShouldBeReturned() {
        assertNotNull(trainee);
        assertEquals("Mary1", trainee.getFirstName());
        assertEquals("Doe", trainee.getLastName());
        //assertEquals("janedoe", trainee.getUsername());
        assertTrue(trainee.getUsername().startsWith("marydoe"));
    }

    @When("the trainee details are updated")
    public void theTraineeDetailsAreUpdated() {
        trainee.setFirstName("Jane Updated");
        trainee = traineeRepository.save(trainee);
    }

    @Then("the updated trainee details should be present in the database")
    public void theUpdatedTraineeDetailsShouldBePresentInTheDatabase() {
        Trainee updatedTrainee = traineeRepository.findById(trainee.getId()).orElse(null);
        assertNotNull(updatedTrainee);
        assertEquals("Jane Updated", updatedTrainee.getFirstName());
    }

    @When("the trainee is deleted by ID")
    public void theTraineeIsDeletedByID() {
        traineeRepository.deleteById(trainee.getId());
    }

    @Then("the trainee should not be present in the database")
    public void theTraineeShouldNotBePresentInTheDatabase() {
        assertFalse(traineeRepository.findById(trainee.getId()).isPresent());
    }
}

