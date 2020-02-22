package com.wemakeprice.model;

public enum CharacterType {
	
	HTML("HTML"),
	TEXT("TEXT");
	
	private CharacterType(final String description) {
		this.description = description;
	}
	
	private final String description;
	public String getDescription() {
		return this.description;
	}
}
