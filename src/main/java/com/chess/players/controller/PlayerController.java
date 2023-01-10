package com.chess.players.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chess.players.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService service;

    @GetMapping("/top-hundred")
    public ResponseEntity findTop100Players(@RequestParam String rank) throws Exception {
            return new ResponseEntity(service.findTop100Players(rank), HttpStatus.OK);
    }

    @GetMapping("/{fideId}")
    public ResponseEntity findPlayer(@PathVariable String fideId) throws Exception {
            return new ResponseEntity(service.findPlayer(fideId), HttpStatus.OK);
    }

    @GetMapping("/{fideId}/chart")
    public ResponseEntity findChartPlayer(@PathVariable String fideId) throws Exception {
            return new ResponseEntity(service.findChartPlayer(fideId), HttpStatus.OK);
    }

    @GetMapping("/{fideId}/top-records")
    public ResponseEntity findTopRecords(@PathVariable String fideId) throws Exception {
            return new ResponseEntity(service.findTopRecords(fideId), HttpStatus.OK);
    }

    @GetMapping("/events")
    public ResponseEntity findEvents() throws Exception {
            return new ResponseEntity(service.findEvents(), HttpStatus.OK);
    }
}
