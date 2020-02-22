package com.wemakeprice.service;

import org.springframework.stereotype.Service;

import com.wemakeprice.model.CharacterSort;
import com.wemakeprice.model.CharacterSortResponseDTO;
import com.wemakeprice.model.CharacterType;
import com.wemakeprice.model.Scraper;

@Service
public class CharacterSortService {
	
	public CharacterSortResponseDTO sort(final String url, 
			final CharacterType characterType,
			final int groupCount) {
		
		Scraper scraper = new Scraper(url, characterType);
		CharacterSort characterSort = new CharacterSort(scraper.getCharacter());
		
		String joinCharacter = joinedNumberAndAlphabet(characterSort);
				
		return diviedText(joinCharacter, groupCount);
	}
	
	public String joinedNumberAndAlphabet(CharacterSort characterSort) {
		
		StringBuffer stringbuffer = new StringBuffer();
		for(int i = 0, length = characterSort.commonLength(); i < length; i++) {
			stringbuffer.append(characterSort.getAlpahpet().charAt(i));
			stringbuffer.append(characterSort.getNumber().charAt(i));
		}
		
		stringbuffer.append(characterSort.remainSubstring());

		return stringbuffer.toString();
	}
	
	public CharacterSortResponseDTO diviedText(String text, int groupCount) {
		
		int totalSize = text.length();
        int remainder = Math.floorMod(totalSize, groupCount);
        int size = totalSize - remainder;
        
        return new CharacterSortResponseDTO("정렬을 완료하였습니다.", text.substring(0, size), 
        		text.substring(size, totalSize));
	}
}
