package com.wemakeprice.model;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {Scraper.class}
)
public class ScraperTest {

	@Autowired
	private Scraper scraper;
	
	@Test
	public void validUrlContents() throws IOException {
		String contents = scraper.scrap("http://www.naver.com", CharacterType.NOTHTML);
		assertEquals(true, contents.indexOf("<")>=0?false:true);
		
		contents = scraper.scrap("http://www.naver.com", CharacterType.TEXTALL);
		assertEquals(true, contents.indexOf("<")>=0?true:false);
	}
}
