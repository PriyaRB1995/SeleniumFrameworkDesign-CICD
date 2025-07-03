@ErrorValidation
Feature: Purchase Order from Ecommerce Website
 
  @Regression
  Scenario Outline: Verify if error message is displayed for invalid username/password
    Given User is landed on Ecommerce login page
    When user is logged in with <username> and <password>
    Then "Incorrect email or password." error message is displayed in confirmation page
    
    Examples: 
      | username         | password   | product     |
      | raini@gmail.com  | Password2! | ZARA COAT 3 |
