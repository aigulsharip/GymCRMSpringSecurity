package com.example.gymcrmspringsecurity.steps;

import com.example.gymcrmspringsecurity.entity.feign.TrainerSummary;
import com.example.gymcrmspringsecurity.entity.feign.TrainerWorkload;
import com.example.gymcrmspringsecurity.feign.TrainerWorkloadFeignClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TrainerWorkloadFeignStepDefinitions {

    @Autowired
    private TrainerWorkloadFeignClient trainerWorkloadFeignClient;

    private Long workloadId;
    private ResponseEntity<List<TrainerWorkload>> allWorkloadsResponse;
    private ResponseEntity<List<TrainerSummary>> monthlySummariesResponse;
    private ResponseEntity<TrainerWorkload> workloadResponse;
    private ResponseEntity<String> errorResponse;

    @When("I retrieve all trainer workloads via Feign Client")
    public void i_retrieve_all_trainer_workloads_via_Feign_Client() {
        allWorkloadsResponse = trainerWorkloadFeignClient.getAllTrainerWorkloads();
    }

    @Then("I should receive a list of trainer workloads")
    public void i_should_receive_a_list_of_trainer_workloads() {
        assertNotNull(allWorkloadsResponse);
        assertEquals(HttpStatus.OK.value(), allWorkloadsResponse.getStatusCodeValue());
        assertNotNull(allWorkloadsResponse.getBody());
    }

    @When("I retrieve monthly summaries via Feign Client")
    public void i_retrieve_monthly_summaries_via_Feign_Client() {
        monthlySummariesResponse = trainerWorkloadFeignClient.getMonthlySummaries();
    }

    @Then("I should receive the list of trainer summaries")
    public void i_should_receive_the_list_of_trainer_summaries() {
        assertNotNull(monthlySummariesResponse);
        assertEquals(HttpStatus.OK.value(), monthlySummariesResponse.getStatusCodeValue());
        assertNotNull(monthlySummariesResponse.getBody());
    }

    @Given("I have a trainer workload with ID {long}")
    public void i_have_a_trainer_workload_with_id(Long id) {
        this.workloadId = id;
    }

    @When("I retrieve the trainer workload with ID {long} via Feign Client")
    public void i_retrieve_the_trainer_workload_with_id_via_feign_client(Long id) {
        workloadResponse = trainerWorkloadFeignClient.getWorkloadById(id);
    }

    @Then("I should receive the trainer workload details")
    public void i_should_receive_the_trainer_workload_details() {
        assertNotNull(workloadResponse);
        assertEquals(HttpStatus.OK.value(), workloadResponse.getStatusCodeValue());
        assertNotNull(workloadResponse.getBody());
        assertEquals(workloadId, workloadResponse.getBody().getId());
    }

    @Given("I have a trainer workload with a non-existent ID {long}")
    public void i_have_a_trainer_workload_with_a_non_existent_id(Long id) {
        this.workloadId = id;
    }

    @When("I retrieve the trainer workload with ID {long} via Feign Client")
    public void i_retrieve_the_trainer_workload_with_id_via_feign_client_non_existent(Long id) {
        workloadResponse = trainerWorkloadFeignClient.getWorkloadById(id);
    }

    @Then("I should receive a not found error response")
    public void i_should_receive_a_not_found_error_response() {
        assertNotNull(workloadResponse);
        assertEquals(HttpStatus.NOT_FOUND.value(), workloadResponse.getStatusCodeValue());
    }


}
