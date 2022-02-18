Feature: Query
  Scenario: Get film by id
    Given We have an id of 1 chosen
    When We try to get a film by id
    Then The db receives a call to get all films matching our id
  Scenario: Get actor by id
    Given We have an id of 1 chosen
    When We try to get a actor by id
    Then The db receives a call to get all actors matching our id