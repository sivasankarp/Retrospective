package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Feedback;
import com.example.demo.entity.Retro;
import com.example.demo.repo.RetroRepo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

// This class defines the REST API endpoints for managing retrospectives
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    // Injecting RetroRepo dependency using Spring's dependency injection
    @Autowired
    private RetroRepo retroRepo;

    // Endpoint to retrieve all retrospectives with optional pagination
    @GetMapping("/retros")
    public List<Retro> getRetros(@RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return retroRepo.getRetros(page, pageSize);
    }

    // Endpoint to add a new retrospective
    @PostMapping("/retro")
    public ResponseEntity<String> addRetro(@RequestBody Retro retro) {
        return retroRepo.addRetro(retro);
    }

    // Endpoint to add feedback to a retrospective
    @PostMapping("/retro/{retroname}/feedback")
    public ResponseEntity<String> addFeedback(@PathVariable String retroname,
                                              @RequestBody Feedback feedback) {
        return retroRepo.addFeedback(retroname, feedback);
    }

    // Endpoint to update feedback in a retrospective
    @PutMapping("/retro/{retroname}/feedback")
    public ResponseEntity<String> updateFeedback(@PathVariable String retroname,
                                                 @RequestBody Feedback feedback) {
        return retroRepo.updateFeedback(retroname, feedback);
    }

    // Endpoint to search retrospectives by date with optional pagination
    @GetMapping("/retros/search")
    public List<Retro> searchRetrospectives(@RequestParam(required = false) String date)
                                             {
//    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
        LocalDate localDate = LocalDate.parse(date);
        return retroRepo.searchRetrospectives(localDate);
    }
}
