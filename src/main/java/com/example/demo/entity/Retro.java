package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Retro implements Serializable{

private String name;
private String summary;
private LocalDate date;
private List<String> participants;
private List<Feedback> feedbackList;
public Retro() {
	super();
	// TODO Auto-generated constructor stub
}
public Retro(String name, String summary, LocalDate date, List<String> participants, List<Feedback> feedbackList) {
	super();
	this.name = name;
	this.summary = summary;
	this.date = date;
	this.participants = participants;
	this.feedbackList = feedbackList;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSummary() {
	return summary;
}
public void setSummary(String summary) {
	this.summary = summary;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public List<String> getParticipants() {
	return participants;
}
public void setParticipants(List<String> participants) {
	this.participants = participants;
}
public List<Feedback> getFeedbackList() {
	return feedbackList;
}
public void setFeedbackList(List<Feedback> feedbackList) {
	this.feedbackList = feedbackList;
}



}