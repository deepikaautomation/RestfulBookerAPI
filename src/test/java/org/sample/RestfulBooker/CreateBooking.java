package org.sample.RestfulBooker;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import POJO.BookingData;
import POJO.BookingData.Bookingdates;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class CreateBooking {

	
	
	int BookingID;
	@Test
	public int createBookingTest() {

		RestAssured.baseURI="https://restful-booker.herokuapp.com";

		Bookingdates dates=new Bookingdates("2024-01-01","2024-01-10");

		BookingData data=new BookingData("Deepika","FFF",1231,true,"Dinner",dates);

		Response response=given().log().all()
		.contentType("application/json")
		.body(data)
		.when()
		.post("/booking")
		.then()
		 .assertThat()
		   .statusCode(200)
		    .extract()
		       .response();
		
		response.prettyPrint();
		
		
		int  BookingID=response.jsonPath().get("bookingid");
		Assert.assertNotNull(BookingID);
	
		
		//JsonPath id=(int)response.jsonPath().getInt("id");
		System.out.println(BookingID);
		return BookingID;
		
	}
	
	@Test
	public void getBooking() {Assert.assertNotNull(BookingID);
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		int BookingID=createBookingTest();
		Assert.assertNotNull(BookingID);
		System.out.println(BookingID);
		given().log().all()
		.contentType("application/json")
		.pathParam("id",BookingID)
		.when().log().all()
		.get("/booking/{id}")
		.then().log().all()
		 .assertThat()
		   .statusCode(200)
		   ;
	}

}
