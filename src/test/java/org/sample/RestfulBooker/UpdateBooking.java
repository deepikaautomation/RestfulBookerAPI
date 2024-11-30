package org.sample.RestfulBooker;

import org.testng.Assert;
import org.testng.annotations.Test;

import POJO.BookingData;
import POJO.BookingData.Bookingdates;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UpdateBooking {
	
	
	public TokenData Tokenbuilder() {
		
		return TokenData.builder()
		.username("admin")
		.password("password123")
		.build();
		
		//return tokencreds;
		
		
	}
	
	
	
	
	public String generateToken() {
		
		
		TokenData tokenData= Tokenbuilder();
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String tokenid=given().log().all()
		  .contentType("application/json")
		  .body(tokenData)
		  .when()
		  .post("/auth")
		  .then()
		  .assertThat()
		  .statusCode(200)
		  .extract()
		  .path("token");
		
		Assert.assertNotNull(tokenid);
		System.out.println(tokenid);
	 
		return tokenid;
	}
	
	@Test
	public void updatebooking() {
		
		CreateBooking cb= new CreateBooking();
		int bookingidforupdate=cb.createBookingTest();
		Assert.assertNotNull(bookingidforupdate);
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		Bookingdates dates=new Bookingdates("2024-10-01","2024-11-10");

		BookingData data=new BookingData("Ishan","DDD",888,true,"bREAKFAST",dates);

		Response response=given().log().all()
		.contentType("application/json")
		.header("Cookie","token=" +generateToken())
		.pathParam("bookid", bookingidforupdate)
		.body(data)
		.when()
		.put("/booking/{bookid}")
		.then()
		 .assertThat()
		   .statusCode(200)
		   .body("firstname",equalTo(data.getFirstname()))
		   .body("lastname",equalTo(data.getLastname()))
		   .body("additionalneeds",equalTo(data.getAdditionalneeds()))
		   .body("totalprice",equalTo(data.getTotalprice()))
		   .body("depositpaid",equalTo(data.isDepositpaid()))
		    .extract()
		       .response();
		
		
		response.prettyPrint();
	}
	
	
	public Partialupdatedata partialDatabuilder() {
		
		  return Partialupdatedata.builder()
			.firstname("Appunni")
			.totalprice(1000)
			.build();
		
	}
	
	
	
	@Test
	public void partialupdateBooking() {
		
		CreateBooking cb= new CreateBooking();
		int bookingidforupdate=cb.createBookingTest();
		Assert.assertNotNull(bookingidforupdate);
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		Partialupdatedata partial=partialDatabuilder();

		Response response=given().log().all()
		.contentType("application/json")
		.header("Cookie","token=" +generateToken())
		.pathParam("bookid", bookingidforupdate)
		.body(partial)
		.when()
		.patch("/booking/{bookid}")
		.then()
		 .assertThat()
		   .statusCode(200)
		   .body("firstname",equalTo(partial.getFirstname()))
		   
		   
		   .body("totalprice",equalTo(partial.getTotalprice()))
		   
		    .extract()
		       .response();
		
		
		response.prettyPrint();
		
	}
	
	
	
	

}
