@reqire
Feature: How to test Rest Apis reqire
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

  Scenario: reqire api test to create new user
    Given i create following user
      | "name" | "sumanpreet"   |
      | "job"  | "Test Analyst" |
    Then the status code is 201
    And response includes the following
      | name | sumanpreet   |
      | job  | Test Analyst |

  Scenario: delete user
    Given i delete a user with id 265
    Then the status code is 204
    
