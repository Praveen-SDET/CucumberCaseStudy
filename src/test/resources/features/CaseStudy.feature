Feature: Purchase Function

Scenario: Login into App
Given User is on Launch Page
When User navigates to Login Page
Then Should display Home Page

Scenario: Add items to cart
When User Add an item to cart
 |cat | ite|
 |Phones | Nexus 6|
 |Laptops | MacBook air| 
Then Items must be added to cart

Scenario: Delete an items in cart
Given User is on Cart Page
When List of items should be available in cart
Then Delete an item from cart

Scenario: Place Order
When User Place ordering 
Then Item must be Placed