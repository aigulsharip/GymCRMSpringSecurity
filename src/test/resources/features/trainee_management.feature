Feature: Trainee Management

  Scenario: Create a new trainee
    Given the trainee details are provided
    When the trainee is created
    Then the trainee should be present in the database

  Scenario: Retrieve an existing trainee
    Given a trainee is created with the details
    When the trainee is retrieved by ID
    Then the correct trainee details should be returned

  Scenario: Update an existing trainee
    Given a trainee is created with the details
    When the trainee details are updated
    Then the updated trainee details should be present in the database

  Scenario: Delete an existing trainee
    Given a trainee is created with the details
    When the trainee is deleted by ID
    Then the trainee should not be present in the database
