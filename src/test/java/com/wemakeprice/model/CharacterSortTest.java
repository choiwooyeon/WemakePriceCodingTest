package com.wemakeprice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {CharacterSort.class}
)
public class CharacterSortTest {

	@Autowired
	private CharacterSort characterSort;
	
	@Test
	public void number() {
		characterSort.sortCharacter("123가나다ABC");
		assertEquals("123", characterSort.getNumberCharacter());
	}
	
	@Test
	public void alphabet() {
		characterSort.sortCharacter("123가나다ABC");
		assertEquals("ABC", characterSort.getAlphabetCharacter());
	}
	
	@Test
	public void sortedNumberAndAlphabet() {
		characterSort.sortCharacter("1가나4다C4Aadi12l3io87sdflk~!@#$9");
		assertEquals("112344789", characterSort.getNumberCharacter());  // 144123879
		assertEquals("AaCddfiikllos", characterSort.getAlphabetCharacter());  // CAadiliosdflk --> AaCddfiikllos
	}
	
}
