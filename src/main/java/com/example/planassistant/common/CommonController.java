package com.example.planassistant.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class CommonController  {

    public ResponseEntity CreatedReturn(Object data){
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }
    public ResponseEntity OkReturn(Object data){
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
    public ResponseEntity AcceptedReturn(Object data){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);

    }

}
