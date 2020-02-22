package com.wemakeprice.model;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Scraper {
	
	private String character;
	
	public String getCharacter() {
		return this.character;
	}
	
	public Scraper(final String url, final CharacterType characterType) {
		validUrl(url);
		
		String scrapedCharacter = this.scrap(url);
		this.character = scrapedCharacter;
		
		if(CharacterType.TEXT == characterType) {
			this.character = scrapedCharacter.replaceAll("<[^>]*>", "").replaceAll("\\s", "");
		}
	}
	
	public static void validUrl(String url) {
		Pattern pattern = Pattern.compile("^((http(s?))\\:\\/\\/?)([0-9a-zA-Z-]+\\.)+[a-zA-Z]{2,6}(\\:[0-9]+)?(\\/\\S*)?");
		Matcher matcher = pattern.matcher(url);
		
		if(!matcher.matches()) {
			throw new IllegalArgumentException("URL을 확인해 주세요.");
		}
	}
	
	private String scrap(final String url) {
		try {
			Document doc = Jsoup.connect(url)
					.timeout(3000)
					.get();
			return doc.outerHtml();
		} catch(IOException e) {
			throw new IllegalStateException("사이트 연결이 불안정합니다.");
		}
		
	}
}
