Feature: Add Entry
  Scenario: Add Language
    Given We have a language to add
    When We add the language
    Then The language should be added and we should return that it was saved
  Scenario: Add Actor
    Given We have an actor to add
    When We add the actor
    Then The actor should be added and we should return that it was saved