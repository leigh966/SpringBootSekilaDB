Feature: Delete entry
  Scenario: Delete an actor
    Given We have the id of the actor we want to delete
    When We delete the actor
    Then The database should receive a call to delete the actor