@REGR
Feature: Get GA all departures

Scenario Outline: Get GA all departures
And Get GA all departures from API from page <pageStart> to page <pageEnd>

Examples:
    | pageStart | pageEnd  |
    | 1   |   1  |
    | 2   |   2  |

