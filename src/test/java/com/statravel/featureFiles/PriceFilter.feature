Feature: Verify If Price Filters are working properly
@UK
Scenario: verify price filters through Slider for <Supplier>
Given User launches UK tours search page
And User Slides the Budget for Minimum range
Then Lowest price tour should be adjusted
And User Slides the Budget for Maximum range
Then Highest price tour should be adjusted