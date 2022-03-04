Feature: Get Request
  Scenario: Get Film Without Filters
    Given there are no more filters
    And We have some films in the database
    And query is "a"
    And a film title in the database contains the query
    When we receive a get film request
    Then we get the film that matches the query