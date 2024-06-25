Feature: Trainer Workload Feign Controller

  Scenario: Get all trainer workloads via Feign Client
    When I retrieve all trainer workloads via Feign Client
    Then I should receive a list of trainer workloads

  Scenario: Get monthly summaries via Feign Client
    When I retrieve monthly summaries via Feign Client
    Then I should receive the list of trainer summaries

  Scenario: Get trainer workload by ID via Feign Client
    Given I have a trainer workload with ID 1
    When I retrieve the trainer workload with ID 1 via Feign Client
    Then I should receive the trainer workload details

  Scenario: Get trainer workload by non-existent ID via Feign Client
    Given I have a trainer workload with a non-existent ID 100
    When I attempt to retrieve the trainer workload with ID 100 via Feign Client
    Then I should receive a not found error response
