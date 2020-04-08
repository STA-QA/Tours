Feature: User Verifies the Sort Order Functionality 
Scenario: User navigates to UK and view tours 
	Given User launches UK tours search page 
	And User selects pricelh 
	Then Verify the tours are displayed by PriceLowest 
	And User selects pricehl 
	Then Verify the tours are displayed by PriceHighest 
	And User selects durationShortest 
	Then Verify the tours are displayed by DurationShortest 
	And User selects durationLongest 
	Then Verify the tours are displayed by DurationLongest 
	And User selects promotions 
	Then Verify the tours are displayed by HighestSavings 
 