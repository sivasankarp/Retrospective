package com.example.demo.entity;

public class Feedback {
	
	private String name;
	private String body;
	private String feedbackType;
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Feedback(String name, String body, String feedbackType) {
		super();
		this.name = name;
		this.body = body;
		this.feedbackType = feedbackType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getFeedbackType() {
		return feedbackType;
	}
	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}
	

}
