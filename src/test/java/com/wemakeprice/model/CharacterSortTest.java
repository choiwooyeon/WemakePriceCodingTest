package com.wemakeprice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CharacterSortTest {

	@Test
	public void number() {
		CharacterSort characterSort = new CharacterSort("123가나다ABC");
		assertEquals("123", characterSort.getNumber());
	}
	
	@Test
	public void sortedNumberAndAlphabet() {
		CharacterSort characterSort = new CharacterSort("1가나4다C4Aadi12l3io87sdflk~!@#$9");
		assertEquals("112344789", characterSort.getNumber());  // 144123879
		assertEquals("AaCddfiikllos", characterSort.getAlpahpet());  // CAadiliosdflk --> AaCddfiikllos
	}
}
