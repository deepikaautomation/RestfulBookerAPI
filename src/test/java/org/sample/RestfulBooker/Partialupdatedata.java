package org.sample.RestfulBooker;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Partialupdatedata {
	
	private String firstname;
	private int totalprice;

}
