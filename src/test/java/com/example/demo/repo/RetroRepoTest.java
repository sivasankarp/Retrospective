package com.example.demo.repo;

import com.example.demo.entity.Feedback;
import com.example.demo.entity.Retro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RetroRepoTest {

    @InjectMocks //This is used when we need to use actual method and body of the class.
    private RetroRepo rr;

    @BeforeEach //It sets object value before executing the test.
    void setUp() {
        rr = new RetroRepo();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addRetro_ValidRetro_ReturnsCreated() {
        Retro retro = new Retro("Retro1","summary", LocalDate.now(), List.of("john", "doe"),Mockito.anyList());
        ResponseEntity<String> response = rr.addRetro(retro);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void addFeedback_ValidFeedback_ReturnsOk() {
        Retro retro = new Retro("Retro1","summary", LocalDate.now(), List.of("john", "doe"),Mockito.anyList());
        rr.addRetro(retro);
        Feedback feedback = new Feedback("john", "Good feedback", "POSITIVE");
        ResponseEntity<String> response = rr.addFeedback("Retro1", feedback);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void addFeedback_ParticipantNotInMeeting_ReturnsBadRequest() {
        Retro retro = new Retro("Retro1","summary", LocalDate.now(), List.of("john", "doe"),Mockito.anyList());
        rr.addRetro(retro);
        Feedback feedback = new Feedback("alisa", "Good feedback", "POSITIVE");
        ResponseEntity<String> response = rr.addFeedback("Retro1", feedback);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateFeedback_ValidFeedback_ReturnsOk() {
        Retro retro = new Retro("Retro1","summary", LocalDate.now(), List.of("john", "doe"),Mockito.anyList());
        rr.addRetro(retro);
        Feedback feedback = new Feedback("john", "Updated feedback", "POSITIVE");
        rr.addFeedback("Retro1", new Feedback("john", "Original feedback", "POSITIVE"));
        ResponseEntity<String> response = rr.updateFeedback("Retro1", feedback);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateFeedback_FeedbackNotFound_ReturnsNotFound() {
        Retro retro = new Retro("Retro1","summary", LocalDate.now(), List.of("john", "doe"),Mockito.anyList());
        rr.addRetro(retro);
        Feedback feedback = new Feedback("john", "Updated feedback", "POSITIVE");
        ResponseEntity<String> response = rr.updateFeedback("Retro1", feedback);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getRetros_ReturnsExpectedNumberOfRetros() {
        Retro retro1 = new Retro("Retro1","summary", LocalDate.now(), List.of("john", "doe"),Mockito.anyList());
        Retro retro2 = new Retro("Retro2","summary", LocalDate.now(), List.of("kris", "mark"),Mockito.anyList());
        Retro retro3 = new Retro("Retro3","summary", LocalDate.now(), List.of("more", "loe"),Mockito.anyList());
        rr.addRetro(retro1);
        rr.addRetro(retro2);
        rr.addRetro(retro3);
        List<Retro> retros = rr.getRetros(0, 2);
        assertEquals(2, retros.size());
    }

    @Test
    void addRetro_NullDate_ReturnsBadRequest() {
        Retro retro = new Retro("Retro1","summary", null, List.of("Participant1", "Participant2"),Mockito.anyList());
        ResponseEntity<String> response = rr.addRetro(retro);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }



    @Test
    void testFindByDate_ExistingDate_ReturnsRetros() {
        Retro retro = new Retro("Retro1", "summary", LocalDate.now(), List.of("john", "doe", "alisa"), Mockito.anyList());
        rr.addRetro(retro);

        List<Retro> result = rr.searchRetrospectives(LocalDate.now());
        assertEquals(1, result.size());
    }

    @Test
    void testFindByDate_NoRetros_ReturnsEmptyList() {
        List<Retro> result = rr.searchRetrospectives(LocalDate.now().minusDays(1));
        assertTrue(result.isEmpty());
    }
}
