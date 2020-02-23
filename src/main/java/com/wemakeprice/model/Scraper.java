package com.wemakeprice.model;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP 응답결과 긁어오는 클래스
 * 
 * @author wooyeon.choi
 * @since 2020.02.23
 *
 */
public class Scraper {
	Logger logger = LoggerFactory.getLogger(Scraper.class);
	
	private String character;
	
	public String getCharacter() {
		return this.character;
	}
	
	public Scraper() {
		
	}
	
	public Scraper(final String url, final CharacterType characterType) {
		validUrl(url);
		
		String scrapedCharacter = scrap(url);
		this.character = scrapedCharacter;
		
		
		if(CharacterType.NOTHTML == characterType) {
			this.character = scrapedCharacter.replaceAll("<[^>]*>", "").replaceAll("\\s", "");
		}
	}
	
	public boolean validUrl(String url) {
		Pattern pattern = Pattern.compile("^((http(s?))\\:\\/\\/?)([0-9a-zA-Z-]+\\.)+[a-zA-Z]{2,6}(\\:[0-9]+)?(\\/\\S*)?");
		Matcher matcher = pattern.matcher(url);
		
		if(!matcher.matches()) {
			logger.error("URL을 확인해 주세요. url :{} ", url);
			throw new IllegalArgumentException("URL을 확인해 주세요.(http or https)");
		}
		
		return true;
	}
	
	private String scrap(final String url) {
		try {
			Document doc = Jsoup.connect(url)
					.timeout(5000)
					.get();
			return doc.outerHtml();
		} catch(IOException e) {
			logger.error("사이트 연결 실패! url :{}, message : {} ", url, e.getMessage());
			throw new IllegalStateException("사이트 연결이 불안정합니다.");
		}
		
	}
}
