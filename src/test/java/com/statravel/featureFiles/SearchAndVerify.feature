Feature: Verify Search function

Scenario Outline: User searches for
Given User launches UK tours search page
And User search random tour name from API brand <Brand> and region <Region>
Then Tours should display correctly on results page
And User click on random tour to open details
Then Tour details should display correctly

Examples:
    | Brand | Region  |
    | contiki   |    uk  |

