package com.wemakeprice.model;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CharacterSort {

	private String number;
	private String alphapet;
	
	public String getNumber() {
		return this.number;
	}
	
	public String getAlpahpet() {
		return this.alphapet;
	}
	
	public CharacterSort(String character) {
	
		String removeSpaceCharacter = character.replaceAll("\\p{Z}", "");
		if(removeSpaceCharacter.isEmpty()) {
			throw new IllegalArgumentException("문자열을 입력해 주세요.");
		}
		
		String number = removeSpaceCharacter.replaceAll("[^0-9]+", "");
		String alphabet = removeSpaceCharacter.replaceAll("[^a-zA-Z]+", "");
		
		this.number = Pattern.compile("")
				.splitAsStream(number)
				.sorted()
				.collect(Collectors.joining(""));
		
		this.alphapet = Pattern.compile("")
				.splitAsStream(alphabet)
				.sorted()
				.sorted(String.CASE_INSENSITIVE_ORDER)
				.collect(Collectors.joining(""));
	}
	
	public int commonLength() {
		return Math.min(numberLength(), alphapetLength());
	}
	
	private int numberLength() {
		return this.number.length();
	}
	
	private int alphapetLength() {
		return this.alphapet.length();
	}
	
	public String remainSubstring() {
		
		if(this.alphapetLength() > numberLength()) {
			return this.alphapet.substring(commonLength(), alphapetLength());
		}
		
		return this.number.substring(commonLength(), numberLength());
	}
}
