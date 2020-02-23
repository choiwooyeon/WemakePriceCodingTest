package com.wemakeprice.model;


/**
 * HTTP 결과 캐릭터 타입 정의
 * 
 * @author wooyeon.choi
 * @since 2020.02.23
 *
 */
public enum CharacterType {
	
	NOTHTML("NOTHTML"),	//HTML태그 미포함.
	TEXT("TEXT");	//HTML태그 포함.
	
	private CharacterType(final String description) {
		this.description = description;
	}
	
	private final String description;
	public String getDescription() {
		return this.description;
	}
}
