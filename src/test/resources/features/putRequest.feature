Feature: Put Request
  Scenario: Successfully update a film
    Given We have all fields required for a film
    And film with id exists
    And language with id exists
    And All other film fields okay
    When A put film request is received
    Then The server returns "saved"
  Scenario: Successfully update an actor
    Given We have all the fields required for an actor
    And actor with id exists
    When A put actor request is received
    Then The server returns "saved"
  Scenario: Null update Actor
    Given all actor fields are null
    And actor with id exists
    When A put actor request is received
    Then The server returns "saved"
    Scenario: Null update Film
      Given all film fields are null
      And film with id exists
      And language with id exists
      And All other film fields okay
      When A put film request is received
      Then The server returns "saved"
