Feature: AddToCart feature
  Scenario: add product to cart and verify the added product
    Given user is on the home page of amazon
    And user searches for a mobile
    And user selects the mobile and the mobile name is displayed on the console
    And the size and colour variations available for mobile are displayed on console
    And user navigates to question and answer page and top three answers are listed
    When user adds product to cart
    Then verify the product added to cart is correct
    And user increases product quantity to four
    Then verify product quantity
    And total amount of order is listed