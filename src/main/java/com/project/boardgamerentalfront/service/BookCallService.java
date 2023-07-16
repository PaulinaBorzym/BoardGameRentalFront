package com.project.boardgamerentalfront.service;

import com.google.gson.Gson;
import com.project.boardgamerentalfront.domain.BookCall;
import com.project.boardgamerentalfront.domain.Rent;
import elemental.json.JsonObject;
import elemental.json.impl.JreJsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class BookCallService {
    private final RestTemplate restTemplate = new RestTemplate();
    private Set<BookCall> bookCalls;
    private static BookCallService bookCallService;


    private BookCallService() {
        this.bookCalls = getBookCall();
    }


    public static BookCallService getInstance() {
        if (bookCallService == null) {
            bookCallService= new BookCallService();
        }
        return bookCallService;
    }
    public Set<BookCall> getBookCall() {
        ResponseEntity<BookCall[]> rs = restTemplate.getForEntity("http://localhost:8080/v1/book/call",BookCall[].class);
        return Arrays.stream(rs.getBody()).collect(Collectors.toSet());
    }

    public void save(BookCall bookCall) {
        this.bookCalls.add(bookCall);
        restTemplate.postForObject("http://localhost:8080/v1/book/call", bookCall, BookCall.class);
    }
}
