@tag
Feature: Purchase a product from ecoomerce site
Background:
Given Land on the page
@Regression
  Scenario Outline: Positive test of submitting an order
    Given Login to the site with <username> and <password>
    When Add product <productname> to cart 
    And CheckOut <productname> and submit the order
    Then Verify the successful message "Thankyou for the order." on confirmation page

    Examples: 
      | username  						| password  | productname|
      | mahin@example.com 		|Mahin@1116 | ZARA COAT 3|
      
