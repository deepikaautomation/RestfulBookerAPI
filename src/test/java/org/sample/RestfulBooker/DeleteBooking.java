package org.sample.RestfulBooker;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import POJO.BookingData;
import POJO.BookingData.Bookingdates;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteBooking {
	
	@Test
	public void Deletebook() {
	
		
		UpdateBooking ub=new UpdateBooking();
	    String token=ub.generateToken();
	    
	    
	    CreateBooking cb= new CreateBooking();
		int bookingidfordelete=cb.createBookingTest();
		Assert.assertNotNull(bookingidfordelete);
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		given().log().all()
		.contentType("application/json")
		.header("Cookie","token=" +token)
		.pathParam("bookid", bookingidfordelete)
		
		.when()
		.delete("/booking/{bookid}")
		.then()
		 .assertThat()
		   .statusCode(201);
		
		
		
		
		given().log().all()
		.contentType("application/json")
		.pathParam("bookid", bookingidfordelete)
		.when().log().all()
		.get("/booking/{bookid}")
		.then().log().all()
		 .assertThat()
		   .statusCode(404)
		   ;
	
	
	}
	
	

	
	

}
