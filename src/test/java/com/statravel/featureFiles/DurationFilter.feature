Feature: Verify if Duration Filters are working properly

@UK
Scenario: verify Duration filters through Slider for <Supplier>
Given User launches UK tours search page
And User Slides the Duration for Minimum range
Then Lowest Duration should be adjusted
And User Slides the Duration for Maximum range
Then Highest Duration should be adjusted



