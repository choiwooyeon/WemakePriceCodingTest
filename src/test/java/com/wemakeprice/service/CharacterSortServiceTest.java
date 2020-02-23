package com.wemakeprice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import com.wemakeprice.model.CharacterSort;
import com.wemakeprice.model.CharacterSortResponseDTO;

public class CharacterSortServiceTest {

	@Test
	public void joinedNumberAndAlphabet() {
		CharacterSortService service = new CharacterSortService();
		CharacterSort characterSort = new CharacterSort("1 12334D BaA");
		assertEquals("A1a1B2D334", service.joinedNumberAndAlphabet(characterSort));
	}
	
	
	@Test
	public void divideAndRemainderText() {
		CharacterSortService service = new CharacterSortService();
		assertEquals(new CharacterSortResponseDTO("정렬을 완료하였습니다.", "A1a1B2D33", 
        		"4"), service.divideAndRemainderText("A1a1B2D334", new BigInteger("3")));
	}
}
