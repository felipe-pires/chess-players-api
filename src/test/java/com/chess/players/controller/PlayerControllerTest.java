package com.chess.players.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.chess.players.model.Chart;
import com.chess.players.model.ChartByPlayer;
import com.chess.players.model.Event;
import com.chess.players.model.EventsByPlace;
import com.chess.players.model.Player;
import com.chess.players.model.Ratings;
import com.chess.players.model.TopHundred;
import com.chess.players.model.TopRecord;
import com.chess.players.service.PlayerService;

class PlayerControllerTest {

    private static final String FIDE_ID = "12345";
    private static final String RANK = "men";


    @InjectMocks
    private PlayerController controller;

    @Mock
    private PlayerService service;

    private Player player;
    private TopHundred topHundred;
    private Ratings ratings;
    private ChartByPlayer chartPlayer;
    private Chart chart;
    private TopRecord record;
    private Event event;
    private EventsByPlace eventsByPlace;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        start();
    }

    @Test
    void findTop100Players() throws Exception {
        when(service.findTop100Players(anyString())).thenReturn(List.of(topHundred));

        ResponseEntity<List<TopHundred>> response = controller.findTop100Players(RANK);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(!response.getBody().isEmpty());
    }

    @Test
    void findPlayer() throws Exception {
        when(service.findPlayer(anyString())).thenReturn(player);

        ResponseEntity<Player> response = controller.findPlayer(FIDE_ID);

        assertNotNull(response);
        assertNotNull(response.getBody().getName());
        assertTrue(!response.getBody().getName().isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Player.class, response.getBody().getClass());
    }

    @Test
    void findChartPlayer() throws Exception {
        when(service.findChartPlayer(anyString())).thenReturn(chartPlayer);

        ResponseEntity<ChartByPlayer> response = controller.findChartPlayer(FIDE_ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getChart().size() > 0);
        assertNotNull(response.getBody().getName());
        assertTrue(!response.getBody().getName().isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findTopRecords() {
    }

    @Test
    void findEvents() {
    }

    private void start(){
        ratings = new Ratings( "2900", "2800", "2700");
        player = new Player(1, "Carlsen, Magnus", "NOR", "12345", 1989, "GM", ratings);
        topHundred = new TopHundred(1, "Carlsen, Magnus", "NOR", 2840, 1990);
        chart = new Chart("Jan-203","2840","2870", "2800");
        chartPlayer = new ChartByPlayer("Carlsen, Magnus", List.of(chart));
        record = new TopRecord("Jan-2023","2900","3", "1", "GM");
        event = new Event("chess","sp","01-2023", "02-2023");
        eventsByPlace = new EventsByPlace("brasil", List.of(event));
    }
}