package com.example.web.model;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product  {

	@NotNull
	private String productName;
	@Size(min = 2, max = 10)	
	private String regionCode;
	@Size(min = 2, max = 2)
	private String stateCode;
	@NotNull
	private String usoc;
	@NotNull
	private String available;
	private long id;
}
