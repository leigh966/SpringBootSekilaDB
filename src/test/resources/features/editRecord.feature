Feature: Edit Record
  Scenario: Fill Language
    Given We have an empty language and a set with a Film
    When We add the name "some-language" and the film set
    Then The language should contain the name and set that we gave it
  Scenario: Fill Actor
    Given We have an empty Actor and a set with a Film
    When We add the first_name "some" last_name "guy" and the film set
    Then The actor should contain the values we gave it