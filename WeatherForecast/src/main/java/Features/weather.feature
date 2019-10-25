Feature: Verify Weather Details

Scenario: Verify Weather details for London City

Given I Enter the Weather Details for "London" City
And I Get the woeid value From Above Response
And I Get the Weather Details with woied value
Then I Compare the "weather_state_name" value with "weather_state_abbr" outcome
And I also get the Weather Forcast for Today