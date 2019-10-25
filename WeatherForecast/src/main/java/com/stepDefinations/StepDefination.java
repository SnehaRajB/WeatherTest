package com.stepDefinations;

import com.jayway.restassured.response.Response;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;
import net.minidev.json.JSONObject;

import static com.jayway.restassured.RestAssured.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jayway.restassured.http.ContentType;

public class StepDefination {

	private static Response jsonValue = null;
	private static String woeidValue = null;
	private static Response woiedJsonValue = null;
	private static String woiedValue = null;


	@Given("^I Enter the Weather Details for \"([^\"]*)\" City$")
	public void i_Enter_the_Weather_Details_for_City(String cityName) throws Throwable {

		Response resp = given().
				param("query", cityName).
				when().
				get("https://www.metaweather.com/api/location/search/");

		jsonValue = resp;

		System.out.println(resp.asString());
		Assert.assertEquals(resp.getStatusCode(), 200);

	}


	@Given("^I Get the woeid value From Above Response$")
	public void i_Get_the_woeid_value_From_Above_Response() throws Throwable {
		System.out.println(jsonValue);	    
		String value = jsonValue.jsonPath().getString("woeid");	
		woeidValue = value;
		System.out.println(value);
	}



	@Given("^I Get the Weather Details with woied value$")
	public void i_Get_the_Weather_Details_with_woied_value() throws Throwable {
		String woeidValue1 =  woeidValue.replace("[", "");
		String woeid =  woeidValue1.split("]")[0];
		woiedValue = woeid;
		
		String url = String.format("https://www.metaweather.com/api/location/%s/", woeid);
			
		Response resp =	given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
        when().get(url).
        then().contentType(ContentType.JSON).extract().response();
		
		woiedJsonValue = resp;
		System.out.println(resp.asString());

	}

	@Then("^I Compare the \"([^\"]*)\" value with \"([^\"]*)\" outcome$")
	public void i_Compare_the_value_with_outcome(String parameter1, String parameter2) throws Throwable {
		System.out.println(woiedJsonValue);	 		
		List<String> state_name_Response = woiedJsonValue.jsonPath().getList("consolidated_weather.weather_state_name");	
		List<String> state_abbr_Response = woiedJsonValue.jsonPath().getList("consolidated_weather.weather_state_abbr");

		if(state_name_Response.size()==state_abbr_Response.size())
		{
			for(int i=0;i<state_name_Response.size();i++)
			{
				String state_name_ResponseValue = state_name_Response.get(i);
				String state_abbr_ResponseValue = state_abbr_Response.get(i);
				char value = state_abbr_ResponseValue.charAt(0);
				String value1=Character.toString(value);
				if(state_name_ResponseValue.startsWith(value1.toUpperCase()))
				{
					System.out.println("Weather forecast for the day :- "+state_name_ResponseValue +" matches the parameter " + parameter1 +" And " + parameter2);	
				}else
				{
					System.out.println("Weather forecast for the day :- "+state_name_ResponseValue +" not matches the parameter " + parameter1 +" And " + parameter2);	
				}
			}
		}
	}
	
	@Then("^I also get the Weather Forcast for Today$")
	public void i_also_get_the_Weather_Forcast_for_Today() throws Throwable {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String date = dtf.format(localDate);
		System.out.println(dtf.format(localDate)); 
		
		String url = String.format("https://www.metaweather.com/api/location/%s/%s", woiedValue, date);
		
		Response resp =	given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
        when().get(url).
        then().contentType(ContentType.JSON).extract().response();
		System.out.println(resp.asString());
		String value = resp.jsonPath().getString("consolidated_weather.weather_state_name");	
		
		System.out.println(value);
		
	}



}
