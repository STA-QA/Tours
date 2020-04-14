Feature: Verify Search function

Scenario Outline: User searches for <Country> with <SearchString>
Given User launches UK tours search page
Given User provides <SearchString>
And Selects the <Country> from the list
Then Tours should display correctly

Examples:
|SearchString|Country|
|Ind|India|
