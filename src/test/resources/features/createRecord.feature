Feature: Create Record
  Scenario: Create Language
    Given We choose name "some-language-name" for language
    When We create a language
    Then The created language should contain the given values
  Scenario: Create Actor
    Given We choose first_name "Some" and last_name "Guy"
    When We create an actor
    Then The created actor should contain the given values
