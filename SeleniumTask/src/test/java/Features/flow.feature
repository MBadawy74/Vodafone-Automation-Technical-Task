Feature: Test buying without purchase flow
  Scenario: Test buying without purchase flow
    Given User logged to the web site
    When user select products to the cart
    And user search for product to add
    Then open the cart to check the order


