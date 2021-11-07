package ar.com.reba.testproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public class ResponseBuilder {

    public static <T> ResponseEntity create(Collection<T> collection, HttpStatus status) {
        return new ResponseEntity<>(collection, status);
    }

    public static <T> ResponseEntity create(T object, HttpStatus status) {
        return new ResponseEntity<>(object, status);
    }

    public static ResponseEntity create(HttpStatus status) {
        return new ResponseEntity(status);
    }
}
