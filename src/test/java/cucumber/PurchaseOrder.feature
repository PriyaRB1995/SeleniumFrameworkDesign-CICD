@PurchaseOrder
Feature: Purchase Order from Ecommerce Website

  Background: 
  Given User is landed on Ecommerce login page

  @QA
  Scenario Outline: Verify if user can successfully place order
    Given user is logged in with <username> and <password>
    When user adds the <product> to the cart
    And checkout the <product> and submit the order
    Then "Order Placed Successfully" error message is displayed in confirmation page

    Examples: 
      | username         | password   | product         |
      | rahini@gmail.com | Password2! | ZARA COAT 3     |
      | best@gmail.com 	 | Password1! | ADIDAS ORIGINAL |
