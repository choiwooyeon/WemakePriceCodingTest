package com.wemakeprice.model;


public class CharacterSortResponseDTO {
	
	private String message;
	
	private String quotient;
	private String remainder;
	
	public String getMessage() {
		return this.message;
	}
	
	public String getQuotient() {
		return this.quotient;
	}
	
	public String getRemainder() {
		return this.remainder;
	}
	
	public CharacterSortResponseDTO(String message, String quotient, String remainder) {
		this.quotient = quotient;
		this.remainder = remainder;
	}
}
