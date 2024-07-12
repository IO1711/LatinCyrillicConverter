package com.uzbConverter.UzbekCyrillicConverter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uzbConverter.UzbekCyrillicConverter.api.modul.Alphabet;
import com.uzbConverter.UzbekCyrillicConverter.service.ConvertService;

@Controller
public class indexController {

    @Autowired
    private ConvertService convertService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/addData")
    public String addDataGetMapping(){
        return "addData";
    }

    @PostMapping("/addData")
    public ResponseEntity<String> addData(Alphabet alphabet){
        convertService.addData(alphabet);

        try{
            String jsonData = objectMapper.writeValueAsString(alphabet);
            return ResponseEntity.ok(jsonData);
        }
        catch(JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to convert to json in addData");
        }
    }

    @PostMapping("/convertLatCyr")
    public ResponseEntity<String> convertLatCyr(String receivedText){
        return ResponseEntity.ok(convertService.convertLatCyr(receivedText));
    }

    @PostMapping("/convertCyrLat")
    public ResponseEntity<String> convertCyrLat(String receivedText){
        return ResponseEntity.ok(convertService.convertCyrLat(receivedText));
    }

}
