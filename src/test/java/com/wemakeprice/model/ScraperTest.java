package com.wemakeprice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ScraperTest {

	@Test
	public void validUrl() {
		Scraper scraper = new Scraper();
		assertEquals(true, scraper.validUrl("http://www.naver.com"));
	}
}
