package com.wemakeprice.model;


/**
 * 결과 빈
 * 
 * @author wooyeon.choi
 * @since 2020.02.23
 *
 */
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
		this.message = message;
		this.quotient = quotient;
		this.remainder = remainder;
	}
	
	@Override
	public boolean equals(Object o){
		CharacterSortResponseDTO dto = (CharacterSortResponseDTO) o;
	      if(this.message.equals(dto.message) && 
	    		  this.quotient.equals(dto.quotient) &&
	    		  this.remainder.equals(dto.remainder)){
	         return true;
	      }
	      return false;
	  }  
}
