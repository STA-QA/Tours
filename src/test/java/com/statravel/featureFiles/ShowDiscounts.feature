Feature: User Verifies Discounts filter functionality
Scenario: User navigates to UK and view tours
	Given User launches UK tours search page
	And User Selects show promotion checkbox
	Then Only Tours with promotions are displayed