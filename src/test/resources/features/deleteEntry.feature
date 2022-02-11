Feature: Delete entry
  Scenario: Delete an actor
    When We provide an existing actor_id
    Then We delete the actor from the database