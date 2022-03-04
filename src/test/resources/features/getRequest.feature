Feature: Get Request
  Scenario: Get Film Containing Query
    Given there are no more filters
    And We have some films in the database
    And query is "a"
    And a film title in the database contains the query
    When we receive a get film request
    Then we get the film that matches the query
  Scenario: Get Film Equal to Query
    Given there are no more filters
    And We have some films in the database
    And query is "a"
    And a film title in the database is equal to the query
    When we receive a get film request
    Then we get the film that matches the query
  Scenario: Get Film by actor id
    Given there are no more filters
    And We have some films in the database
    And linked id is 1
    And There is a film associated with the actor_id
    When we receive a get film request
    Then We return the film associated with the actor_id