package com.wemakeprice.model;

import lombok.Data;

/**
 * 결과 빈
 * 
 * @author wooyeon.choi
 * @since 2020.02.23
 *
 */
@Data
public class CharacterSortResponseDTO {
	
	private String message;
	private String quotient;
	private String remainder;
	
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
	
	
	@Override
	public int hashCode() {
		return this.message.hashCode()+this.quotient.hashCode()+this.remainder.hashCode();
	}


	public CharacterSortResponseDTO(String message, String quotient, String remainder) {
		this.message = message;
		this.quotient = quotient;
		this.remainder = remainder;
	}
}
