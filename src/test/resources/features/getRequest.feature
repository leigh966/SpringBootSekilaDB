Feature: Get Request
  Scenario: Get Film Without Filters
    Given there are no more filters
    And We have some films in the database
    When we receive a get film request
    Then we get all the films in the database