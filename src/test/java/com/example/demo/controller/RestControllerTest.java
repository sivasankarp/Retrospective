package com.example.demo.controller;

import com.example.demo.entity.Feedback;
import com.example.demo.entity.Retro;
import com.example.demo.repo.RetroRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RestControllerTest {
    @Mock
    private RetroRepo retroRepo;

    @InjectMocks
    private RestController restController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getRetros_ReturnsRetrospectives() {
        List<Feedback> feedbackList= List.of(new Feedback("john", "Updated feedback", ".POSITIVE"));
        List<Retro> retrospectives = List.of(new Retro("Retro1","summary", LocalDate.now(), List.of("john", "doe"), feedbackList));
        when(retroRepo.getRetros(0, 10)).thenReturn(retrospectives);
        List<Retro> result = restController.getRetros(0, 10);
        assertEquals(retrospectives, result);
    }

    @Test
    void addRetro_ValidRetro_ReturnsCreated() {
        List<Feedback> feedbackList= List.of(new Feedback("john", "Updated feedback", ".POSITIVE"));
        Retro retro = new Retro("Retro1","summary", LocalDate.now(), List.of("john", "doe"),feedbackList);
        when(retroRepo.addRetro(retro)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Retro Added!"));
        ResponseEntity<String> response = restController.addRetro(retro);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void addFeedback_ValidFeedback_ReturnsOk() {
        Feedback feedback = new Feedback("john", "Good feedback", "POSITIVE");
        when(retroRepo.addFeedback("Retro1", feedback)).thenReturn(ResponseEntity.ok("Feedback has been added!"));
        ResponseEntity<String> response = restController.addFeedback("Retro1", feedback);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateFeedback_ValidFeedback_ReturnsOk() {
        Feedback feedback = new Feedback("john", "Updated feedback", ".POSITIVE");
        when(retroRepo.updateFeedback("Retro1", feedback)).thenReturn(ResponseEntity.ok("Feedback updated successfully!"));
        ResponseEntity<String> response = restController.updateFeedback("Retro1", feedback);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void searchRetrospectives_ValidDate_ReturnsRetrospectives() {
        String date=LocalDate.now().toString();
        System.out.println(date);
        List<Feedback> feedbackList= List.of(new Feedback("john", "Updated feedback", ".POSITIVE"));
        List<Retro> retrospectives = List.of(new Retro("Retro1","summary", LocalDate.now(), List.of("john", "doe"),feedbackList));
        when(retroRepo.searchRetrospectives(LocalDate.now())).thenReturn(retrospectives);
        List<Retro> result = restController.searchRetrospectives(date);
        assertEquals(retrospectives, result);
    }

}
