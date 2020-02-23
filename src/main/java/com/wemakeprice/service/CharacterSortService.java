package com.wemakeprice.service;

import java.math.BigInteger;

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
	
	public CharacterSortResponseDTO sortAndDevide(final String url, 
			final CharacterType characterType,
			final BigInteger groupCount) {
		
		Scraper scraper = new Scraper(url, characterType);
		CharacterSort characterSort = new CharacterSort(scraper.getCharacter());
		
		String joinCharacter = joinedNumberAndAlphabet(characterSort);
				
		return divideAndRemainderText(joinCharacter, groupCount);
	}
	
	
	public String joinedNumberAndAlphabet(CharacterSort characterSort) {
		
		StringBuffer stringbuffer = new StringBuffer();
		for(int i = 0, length = characterSort.commonLength(); i < length; i++) {
			stringbuffer.append(characterSort.getAlphabet().charAt(i));
			stringbuffer.append(characterSort.getNumber().charAt(i));
		}
		
		stringbuffer.append(characterSort.remainSubstring());

		return stringbuffer.toString();
	}
	
	
	public CharacterSortResponseDTO divideAndRemainderText(String text, BigInteger groupCount) {
		
		int totalSize = text.length();
		BigInteger[] value = new BigInteger(""+totalSize).divideAndRemainder(groupCount);
		int size = totalSize - value[1].intValue();
        return new CharacterSortResponseDTO("정렬을 완료하였습니다.", text.substring(0, size), 
        		text.substring(size, totalSize));
	}
}
