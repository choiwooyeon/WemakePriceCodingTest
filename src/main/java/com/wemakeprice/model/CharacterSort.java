package com.wemakeprice.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


import org.springframework.stereotype.Component;

import lombok.Data;


/**
 * 캐릭터 sorting 클래스
 * 
 * @author wooyeon.choi
 * @since 2020.02.23
 *
 */
@Data
@Component
public class CharacterSort {
	private final Pattern NumberPattern = Pattern.compile("[0-9]+");
	private final Pattern AlphabetPattern = Pattern.compile("[a-zA-Z]+");
	
	private String numberCharacter;
	private String alphabetCharacter;
	
	
	public void sortCharacter(String character) {
		numberCharacter = sortNumberCharacter(character);
		alphabetCharacter = sortgetAlphabetCharacter(character);
	}
	
	
	private String convertRegex(Pattern pattern, String character) {
		Matcher matcher = pattern.matcher(character);
		StringBuilder builder = new StringBuilder();
		while(matcher.find()) {
			builder.append(matcher.group(0));
		}
		return builder.toString();
	}
	
	
	private String sortNumberCharacter(String character) {
		return  Pattern.compile("")
				.splitAsStream(convertRegex(NumberPattern, character))
				.sorted()
				.collect(Collectors.joining(""));
	}
	
	
	private String sortgetAlphabetCharacter(String character) {		
		return Pattern.compile("")
				.splitAsStream(convertRegex(AlphabetPattern, character))
				.sorted()
				.sorted(String.CASE_INSENSITIVE_ORDER)
				.collect(Collectors.joining(""));
	}
	
	
	public int commonLength() {
		return Math.min(numberLength(), alphabetLength());
	}
	
	
	private int numberLength() {
		return this.numberCharacter.length();
	}
	
	
	private int alphabetLength() {
		return this.alphabetCharacter.length();
	}
	
	
	public String remainSubstring() {
		
		if(this.alphabetLength() > numberLength()) {
			return this.alphabetCharacter.substring(commonLength(), alphabetLength());
		}
		
		return this.numberCharacter.substring(commonLength(), numberLength());
	}
}
