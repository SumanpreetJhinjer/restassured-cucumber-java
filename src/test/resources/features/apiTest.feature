@reqire
Feature: How to test Rest Apis
  I want to use this template for my feature file to test Rest Apis

  Scenario: reqire api test user1
    Given a user exist in the api with id 1
    Then the status code is 200
    And response includes the following
      | id         |      1 |
      | first_name | George |

  Scenario: reqire api test user2
    Given a user exist in the api with id 2
    Then the status code is 200
    And response includes the following
      | id         |     2 |
      | first_name | Janet |
