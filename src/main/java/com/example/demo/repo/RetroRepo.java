package com.example.demo.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Feedback;
import com.example.demo.entity.Retro;

@Repository
public class RetroRepo {
	
	List<Retro> list = new ArrayList<>(); //
	
	
	public String insertRetro(Retro r){
		for(Retro re:list) {
			if(re.getName().equals(r.getName())) {
				return "Retro already exist!";
			}
		}
		list.add(r);
		return "Retro Added!";
	}
	
	public List<Retro> showRetro(){
		return list;
	}
	
	public String addFeedback(String retroname, Feedback feedback) {
		for(Retro r:list) {
			if(!r.getFeedbackList().isEmpty()){
				for(Feedback f: r.getFeedbackList()) {
					if(f.getName().equals(feedback.getName()))
						return "Feedback Already Recorded!";
				}
				
			}
			if(r.getName().equals(retroname)) {
				if(r.getParticipants().contains(feedback.getName())) {
					r.getFeedbackList().add(feedback);
					return "Feedback Has Been Added!";
				}else {
					return feedback.getName() + " Was Not In The Meeting!";
				}
			}
		}
		return retroname + " Not Found!";
	}
	
	public void updateRetroDetails(String retroname, Retro retro) {
		for(Retro r:list) {
			if(r.getName().equals(retroname)) {
				if(retro.getSummary()!=null) {
					r.setSummary(retro.getSummary());
				}
				if(retro.getDate()!= null) {
					r.setDate(retro.getDate());
				}
				if(retro.getParticipants()!=null) {
					r.setParticipants(retro.getParticipants());
				}			
			}
		}
	}
	
	public void updateFeedbackDetails(String retroname, Feedback feedback) {
		for(Retro r:list) {
			if(r.getName().equals(retroname)) {
				for(Feedback f:r.getFeedbackList()) {
					if(f.getName().equals(feedback.getName())) {
						if(feedback.getFeedbackType()!=null) {
							f.setFeedbackType(feedback.getFeedbackType());
						}
						if(feedback.getBody()!=null) {
							f.setBody(feedback.getBody());
						
					}
				}
				}
			}
		}
	}
	
}
