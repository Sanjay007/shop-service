package com.idealo.output;

public class ItemOut {
	private Long id;
	String title;
	String text;
	double price;

	public ItemOut() {

	}

	public ItemOut(Long id, String title, String text, double price) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
