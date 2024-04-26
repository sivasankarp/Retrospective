package com.example.demo.repo;

import com.example.demo.entity.Feedback;
import com.example.demo.entity.Retro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// This class handles the data access operations for retrospectives
@Repository
public class RetroRepo {

    // Logger for logging messages
    private final Logger logger = LoggerFactory.getLogger(RetroRepo.class);

    // In-memory storage for retrospectives using a map
    private final Map<String, Retro> retrospectives = new HashMap<>();

    // Method to add a new retrospective
    public ResponseEntity<String> addRetro(Retro retro) {
        if (retro.getDate() == null || retro.getParticipants().isEmpty()) {
            return ResponseEntity.badRequest().body("Date and participants are required.");
        }
        retrospectives.put(retro.getName(), retro);
        logger.info("New retrospective added: {}", retro.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body("Retro Added!");
    }

    // Method to add feedback to a retrospective
    public ResponseEntity<String> addFeedback(String retroName, Feedback feedback) {
        Retro retro = retrospectives.get(retroName);
        if (retro == null) {
            return ResponseEntity.notFound().build();
        }
        if (!retro.getParticipants().contains(feedback.getName())) {
            return ResponseEntity.badRequest().body(feedback.getName() + " was not in the meeting!");
        }
        retro.getFeedbackList().add(feedback);
        logger.info("Feedback added to retrospective: {}", retroName);
        return ResponseEntity.ok("Feedback has been added!");
    }

    // Method to update feedback in a retrospective
    public ResponseEntity<String> updateFeedback(String retroName, Feedback feedback) {
        Retro retro = retrospectives.get(retroName);
        if (retro == null) {
            return ResponseEntity.notFound().build();
        }
        for (Feedback fb : retro.getFeedbackList()) {
            if (fb.getName().equals(feedback.getName())) {
                fb.setBody(feedback.getBody());
                fb.setFeedbackType(feedback.getFeedbackType());
                logger.info("Feedback updated for retrospective: {}", retroName);
                return ResponseEntity.ok("Feedback updated successfully!");
            }
        }
        return ResponseEntity.notFound().build();
    }

 // Method to retrieve all retrospectives with optional pagination
    public List<Retro> getRetros(int page, int size) {
        // Pagination logic here
        List<Retro> list = new ArrayList<>(retrospectives.values());
        int start = Math.max(0, page * size);
        int end = Math.min(list.size(), (page + 1) * size);
        if (start > end) {
            return new ArrayList<>();
        }
        logger.info("Retrieving retros from index {} to {}", start, end);
        return new ArrayList<>(list.subList(start, end));
    }

    // Method to search retrospectives by date with optional pagination
    public List<Retro> searchRetrospectives(LocalDate date) {
        // Search logic here
        List<Retro> list = new ArrayList<>(retrospectives.values());
        List<Retro> retroList = list.stream()
                .filter(r -> r.getDate().equals(date))
                .collect(Collectors.toList());
        logger.info("Found {} retro for the date {}", list.size(), date);
        return retroList;
    }
}

