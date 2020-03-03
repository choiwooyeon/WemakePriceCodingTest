package com.wemakeprice.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemakeprice.model.CharacterSort;
import com.wemakeprice.model.CharacterSortResponseDTO;
import com.wemakeprice.model.CharacterType;
import com.wemakeprice.model.Scraper;


/**
 * HTTP 응답결과 정렬 서비스
 * 
 * @author wooyeon.choi
 * @since 2020.02.23
 *
 */
@Service
public class CharacterSortService {
	
	@Autowired
	private Scraper scraper;
	
	@Autowired
	private CharacterSort characterSort;
	
	private String scrap(String url, CharacterType type) throws IOException {
		String character = scraper.scrap(url, type);
		return character;
	}
	
	public CharacterSortResponseDTO sortAndDevide(final String url, 
			final CharacterType characterType,
			final long groupCount) throws IOException {
		
		characterSort.sortCharacter(scrap(url, characterType));
		String joinCharacter = joinedNumberAndAlphabet(characterSort);
				
		return divideAndRemainderText(joinCharacter, groupCount);
	}
	
	
	public String joinedNumberAndAlphabet(CharacterSort characterSort) {
		
		StringBuilder builder = new StringBuilder();
		for(int i = 0, length = characterSort.commonLength(); i < length; i++) {
			builder.append(characterSort.getAlphabetCharacter().charAt(i));
			builder.append(characterSort.getNumberCharacter().charAt(i));
		}
		
		builder.append(characterSort.remainSubstring());

		return builder.toString();
	}
	
	
	public CharacterSortResponseDTO divideAndRemainderText(String text, long groupCount) {
		
		int totalSize = text.length();
		int remainderLength = (int) Math.floorMod(totalSize, groupCount);
		int size = totalSize - remainderLength;
        return new CharacterSortResponseDTO("정렬을 완료하였습니다.", text.substring(0, size), 
        		text.substring(size, totalSize));
	}
}
