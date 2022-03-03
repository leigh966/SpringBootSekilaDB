Feature: Post Request
  Scenario: Successfully add film
    Given We have all the fields required for a film
    When We send a post request for a film
    Then The server should return "saved"
  Scenario: Have film rejected for being too long
    Given We have all the fields required for a film
    And The title is too long
    When We send a post request for a film
    Then The server should return "Not Saved: Too long"
