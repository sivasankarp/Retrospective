package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Feedback;
import com.example.demo.entity.Retro;
import com.example.demo.repo.RetroRepo;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
	
	@Autowired
	RetroRepo rr;
	
	@GetMapping("/retros")
	public List<Retro> getRetros(){
		
		return rr.showRetro();
	}
	
	@PostMapping("/retro")
	public String addRetro(@RequestBody Retro retro) {
		List<Feedback> feedbackList = new ArrayList<>();
		retro.setFeedbackList(feedbackList);
		return rr.insertRetro(retro);
		
//		List<String> nameList = new ArrayList<>();
//		List<Feedback> feedbackList = new ArrayList<>();
//		nameList.add("abc");
//		nameList.add("def");
//		Date date = new Date();
//		2024-04-25T10:20:29.086+00:00
//		
//		Retro a= new Retro("retro1", "xyz", date, nameList, feedbackList);
//		rr.insertRetro(a);
	}
	@PostMapping("/retro/{retroname}")
	public String addFeedback(@PathVariable(name="retroname")String retroname, @RequestBody Feedback feedback) {
		
//		Retro r = new Retro();
//		for(Retro re:rr.showRetro()) {
//			if(re.getName().equals(retroname)) {
//				rr.showRetro().get(rr.showRetro().indexOf((Retro)re)).getFeedbackList().add(feedback);
//				
//			} 
//		}
		return rr.addFeedback(retroname, feedback);
		
//        r.getFeedbackList().add(new Feedback("abc", "Sprint objective met", "Positive"));
	}
	
	@PostMapping("/uretro/{retroname}")
	public void updateRetro(@PathVariable(name="retroname")String retroname, @RequestBody Retro retro) {
		
		rr.updateRetroDetails(retroname, retro);
	}
	
	@PostMapping("/ufeedback/{retroname}")
	public void updateFeedback(@PathVariable(name="retroname")String retroname, @RequestBody Feedback feedback) {
		
		rr.updateFeedbackDetails(retroname, feedback);
	}
	
	
	
}
