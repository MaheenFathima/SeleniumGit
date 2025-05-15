
@tag
Feature: Error Validation 
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Error in Login
    Given Land on the page
    When Login to the site with <username> and <password>
    Then "Incorrect email or password." appears

    Examples: 
      | username  						| password  | 
      | mahin@example.com 		|Mahin@111 | 
