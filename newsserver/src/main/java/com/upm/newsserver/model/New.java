package com.upm.newsserver.model;

public class New {

	private String title;
	private String header;
	private String author;
	private String category;
	private String body;
	private boolean premium;
	
	public New() {
		
	};
	
	public New(String title, String author) {
		this.title = title;
		this.author = author;
		this.header = "";
		this.body = "";
		this.category = "";
		this.premium = false;
		this.toString();
	}
	
	public New(String title, String header, String author, String category, String body, boolean premium) {
		super();
		this.title = title;
		this.header = header;
		this.author = author;
		this.category = category;
		this.body = body;
		this.premium = premium;
	}

	public String getTitle() {
		return title;
	}

	public String getHeader() {
		return header;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public String getBody() {
		return body;
	}

	public boolean isPremium() {
		return premium;
	}

	@Override
	public String toString() {
		return "New [title=" + title + ", header=" + header + ", author=" + author + ", category=" + category
				+ ", body=" + body + ", premium=" + premium + "]";
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
}
