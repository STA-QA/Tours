@REGR
Feature: Get all cheapest departures from Contiki

Scenario Outline: Get all cheapest departures
And Get all cheapest departures from Contiki API region <Region>

Examples:
     | Region  |
     |    uk  |

