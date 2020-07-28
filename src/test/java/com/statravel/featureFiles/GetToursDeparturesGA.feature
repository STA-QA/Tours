@REGR
Feature: Get GA all departures for tours 

Scenario Outline: Get GA all departures
And Get GA all departures from API for tours <Tours>

Examples:
    | Tours | 
    |21593,24552,2343|
    | AAKS,AAEK,RT |
   
   
   

