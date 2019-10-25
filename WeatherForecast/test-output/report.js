$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("weather.feature");
formatter.feature({
  "line": 1,
  "name": "Verify Weather Details",
  "description": "",
  "id": "verify-weather-details",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Verify Weather details for London City",
  "description": "",
  "id": "verify-weather-details;verify-weather-details-for-london-city",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I Enter the Weather Details for \"London\" City",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I Get the woeid value From Above Response",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "I Get the Weather Details with woied value",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I Compare the \"weather_state_name\" value with \"weather_state_abbr\" outcome",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "I also get the Weather Forcast for Today",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});