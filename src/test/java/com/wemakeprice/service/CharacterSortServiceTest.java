package com.wemakeprice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wemakeprice.model.CharacterSort;
import com.wemakeprice.model.CharacterSortResponseDTO;
import com.wemakeprice.model.Scraper;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {CharacterSort.class, CharacterSortService.class, Scraper.class}
)
public class CharacterSortServiceTest {

	@Autowired
	private CharacterSort characterSort;
	@Autowired
	private CharacterSortService characterSortService;
	
	
	@Test
	public void joinedNumberAndAlphabet() {
		characterSort.sortCharacter("1 12334D BaA");
		assertEquals("A1a1B2D334", characterSortService.joinedNumberAndAlphabet(characterSort));
	}
	
	
	@Test
	public void divideAndRemainderText() {
		assertEquals(new CharacterSortResponseDTO("정렬을 완료하였습니다.", "A1a1B2D33", 
        		"4"), characterSortService.divideAndRemainderText("A1a1B2D334", 3));
	}
}
