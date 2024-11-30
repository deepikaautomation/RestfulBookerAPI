package POJO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookingData {
	
	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private String additionalneeds;
	private Bookingdates bookingdates;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Bookingdates{
		private String checkin;
		private String checkout;
	}
	

}
