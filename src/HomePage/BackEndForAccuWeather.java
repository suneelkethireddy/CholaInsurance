package HomePage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import org.testng.annotations.Test;

public class BackEndForAccuWeather 
{
	@Test
	public void checkLocation(){
		
		//String apiKey="https://samples.openweathermap.org/data/2.5/weather?q={country}&appid=b6907d289e10d714a6e88b30761fae22";
		//RestAssured.given().parameter(arg0, arg1)
		Response response=RestAssured.given().pathParam("country", "London,uk").when().get("https://samples.openweathermap.org/data/2.5/weather?q={country}&appid=b6907d289e10d714a6e88b30761fae22");
		String longistude = response.jsonPath().getString("coord.lon");
		String countryAsserting=response.jsonPath().getString("name");
		assertEquals(countryAsserting, "London");
		//System.out.println(weth);
		assertNotNull(longistude);
		//System.out.println(response.prettyPrint());
		assertEquals(response.getStatusCode(), 200);
	}	
	
	@Test
	public void accuWetharCountry(){
		
		Response response=RestAssured.given().pathParam("country", "IND").when().get("http://dataservice.accuweather.com/locations/v1/adminareas/{country}?apikey=gh0or8GZLydSmWFppxRCsuLA8V6ywTrD");
		Object stateWether=response.jsonPath().get("[0].CountryID");
		int noOfStates=response.jsonPath().getList("$").size();
		Object compareString=response.jsonPath().get("CountryID");
		
		//
		//System.out.println(stateWether);
		System.out.println(noOfStates);
		//System.out.println(response.jsonPath().get("CountryID"));
		for(int i=1;i<noOfStates;i++)
		{
			assertEquals(response.jsonPath().get("CountryID"), "[IND]");
			System.out.println("*****");
		}
		
		assertEquals(response.getStatusCode(), 200);
	}
}
								