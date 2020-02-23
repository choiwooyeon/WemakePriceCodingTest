package com.wemakeprice.model;

import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * 캐릭터 sorting 클래스
 * 
 * @author wooyeon.choi
 * @since 2020.02.23
 *
 */
public class CharacterSort {

	private String number;
	
	private String alphabet;
	
	
	public String getNumber() {
		return this.number;
	}
	
	
	public String getAlphabet() {
		return this.alphabet;
	}
	
	
	public CharacterSort(String character) {
	
		String removeSpaceCharacter = character.replaceAll("\\p{Z}", "");
		if(removeSpaceCharacter.isEmpty()) {
			throw new IllegalArgumentException("문자열을 입력해 주세요.");
		}
		
		this.number = sortNumber(removeSpaceCharacter);
		this.alphabet = sortAlphabet(removeSpaceCharacter);
	}
	
	
	private String sortNumber(String removeSpaceCharacter) {
		String number = removeSpaceCharacter.replaceAll("[^0-9]+", "");
		
		return  Pattern.compile("")
				.splitAsStream(number)
				.sorted()
				.collect(Collectors.joining(""));
	}
	
	
	private String sortAlphabet(String removeSpaceCharacter) {
		String alphabet = removeSpaceCharacter.replaceAll("[^a-zA-Z]+", "");
		
		return Pattern.compile("")
				.splitAsStream(alphabet)
				.sorted()
				.sorted(String.CASE_INSENSITIVE_ORDER)
				.collect(Collectors.joining(""));
	}
	
	
	public int commonLength() {
		return Math.min(numberLength(), alphabetLength());
	}
	
	
	private int numberLength() {
		return this.number.length();
	}
	
	
	private int alphabetLength() {
		return this.alphabet.length();
	}
	
	
	public String remainSubstring() {
		
		if(this.alphabetLength() > numberLength()) {
			return this.alphabet.substring(commonLength(), alphabetLength());
		}
		
		return this.number.substring(commonLength(), numberLength());
	}
}
