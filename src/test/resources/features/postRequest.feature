Feature: Post Request
  Scenario: Successfully add film
    Given We have all the fields required for a film
    When We send a post request for a film
    Then The server should return "saved"
  Scenario: Have film rejected for title being too long
    Given We have all the fields required for a film
    And The title is too long
    When We send a post request for a film
    Then The server should return "Not Saved: Too long"
  Scenario: Successfully add actor
    Given We have all the fields for an actor
    When We send a post request for an actor
    Then The server should return "saved"
    Scenario: Have actor be rejected for first name being too long
      Given We have all the fields for an actor
      And first_name too long
      When We send a post request for an actor
      Then The server should return "Not Saved: first_name too long"
  Scenario: Have actor be rejected for last name being too long
    Given We have all the fields for an actor
    And last_name too long
    When We send a post request for an actor
    Then The server should return "Not Saved: last_name too long"
