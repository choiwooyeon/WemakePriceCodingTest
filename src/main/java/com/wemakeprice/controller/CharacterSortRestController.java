package com.wemakeprice.controller;

import javax.validation.constraints.Min;
import java.io.IOException;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
@Validated
public class CharacterSortRestController {
	static final Logger logger = LoggerFactory.getLogger(CharacterSortRestController.class);
	
	@Autowired
	private CharacterSortService characterSortService;
	
	
	
	@GetMapping(value = "/api/scrap")
	public ResponseEntity<CharacterSortResponseDTO> scraper(@RequestParam @NotNull @Pattern(regexp = "^((http(s?))\\:\\/\\/?)([0-9a-zA-Z-]+\\.)+[a-zA-Z]{2,6}(\\:[0-9]+)?(\\/\\S*)?") final String url,
			@RequestParam final CharacterType characterType,
			@RequestParam @Min(1) @Max(Long.MAX_VALUE) final long groupCount)
					throws IllegalArgumentException, MethodArgumentNotValidException, IOException {
		logger.info("url : {}, type : {}, groupUnit : {}", url, characterType, groupCount);
		return ResponseEntity.ok(characterSortService.sortAndDevide(url, characterType, groupCount));
	}
}
