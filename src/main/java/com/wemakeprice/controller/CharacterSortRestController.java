package com.wemakeprice.controller;

import java.math.BigInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wemakeprice.model.CharacterType;
import com.wemakeprice.service.CharacterSortService;
import com.wemakeprice.model.CharacterSortResponseDTO;

/**
 * 컨트롤러
 * 
 * @author wooyeon.choi
 * @since 2020.02.23
 *
 */
@RestController
public class CharacterSortRestController {
	
	private final CharacterSortService characterSortService;
	public CharacterSortRestController(CharacterSortService characterSortService) {
		this.characterSortService = characterSortService;
	}
	
	
	@GetMapping(value = "/api/scrap")
	public ResponseEntity<CharacterSortResponseDTO> scraper(@RequestParam final String url, 
			@RequestParam final CharacterType characterType,
			@RequestParam final BigInteger groupCount) {
		
		try {
			if(groupCount.compareTo(new BigInteger("0")) < 1) {
				throw new IllegalArgumentException("출력 단위는 양수를 입력해 주세요.");
			}
			
			return ResponseEntity.ok(characterSortService.sortAndDevide(url, characterType, groupCount));
		} catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest()
					.body(new CharacterSortResponseDTO(e.getMessage(), "", ""));
		} catch(IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new CharacterSortResponseDTO(e.getMessage(), "", ""));
		}
	}
}
