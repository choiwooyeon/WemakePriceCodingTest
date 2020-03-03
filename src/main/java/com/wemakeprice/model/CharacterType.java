package com.wemakeprice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * HTTP 결과 캐릭터 타입 정의
 * 
 * @author wooyeon.choi
 * @since 2020.02.23
 *
 */
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public enum CharacterType {
	
	NOTHTML("NOTHTML"),	//HTML태그 미포함.
	TEXTALL("TEXTALL");	//HTML태그 포함.
	
	@Getter
	private final String description;
}
