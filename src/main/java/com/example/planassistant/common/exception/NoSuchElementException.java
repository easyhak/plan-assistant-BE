package com.example.planassistant.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.crossstore.ChangeSetPersister;

@AllArgsConstructor
@Getter
public class NoSuchElementException extends RuntimeException{
    String errorMessage;
}
