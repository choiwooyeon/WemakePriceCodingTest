package com.wemakeprice;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodingTestApplicationTests {
	Logger logger = LoggerFactory.getLogger(CodingTestApplicationTests.class);
	
	@Test
	void contextLoads() {
		logger.info("테스트입니다.");
	}

}
