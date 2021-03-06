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
  Scenario: Get Actor Containing query
    Given there are no more filters
    And we have some actors in the database
    And query is "a"
    And An Actor in the database contains the query
    When we receive a get actor request
    Then we get the actor that matches the query
  Scenario: Get Actor by film id
    Given there are no more filters
    And we have some actors in the database
    And linked id is 1
    And There is an actor associated with the film_id
    When we receive a get actor request
    Then We return the actor associated with the film_id