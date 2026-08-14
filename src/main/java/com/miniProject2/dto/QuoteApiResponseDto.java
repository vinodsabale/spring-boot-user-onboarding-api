package com.miniProject2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteApiResponseDto {
	private Integer id;
	@JsonProperty("q")
	private String quote;
	@JsonProperty("a")
	private String author;
	public QuoteApiResponseDto() {}
	
	public QuoteApiResponseDto(Integer id, String quote, String author) {
		super();
		this.id = id;
		this.quote = quote;
		this.author = author;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "QuoteApiResponseDto [id=" + id + ", quote=" + quote + ", author=" + author + "]";
	}
	
}
