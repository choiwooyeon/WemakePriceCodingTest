package com.wemakeprice.model;

import java.io.IOException;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
/**
 * HTTP 응답결과 긁어오는 클래스
 * 
 * @author wooyeon.choi
 * @since 2020.02.23
 *
 */
@Component
public class Scraper {	
	
	public String scrap(final String url, final CharacterType characterType) throws IOException {
		Document doc = Jsoup.connect(url)
				.timeout(5000)
				.get();
		String scrapedCharacter =  doc.outerHtml();
		
		if(CharacterType.NOTHTML == characterType) {
			scrapedCharacter = scrapedCharacter.replaceAll("<[^>]*>", "").replaceAll("\\s", "");
		}
		
		scrapedCharacter = removeSpaceCharacter(scrapedCharacter);
		return scrapedCharacter;
	}
	
	private String removeSpaceCharacter(String character) throws IllegalArgumentException{
		String removeSpaceCharacter = Pattern.compile("\\p{Z}").matcher(character).replaceAll("");
		if(removeSpaceCharacter.isEmpty()) {
			throw new IllegalArgumentException("응답메시지에 문자열이 존재하지 않습니다.");
		}
		return removeSpaceCharacter;
	}
}
