package com.chess.players.service;

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
import com.chess.players.model.Player;
import com.chess.players.model.Ratings;
import com.chess.players.model.RecordsByPlayer;
import com.chess.players.model.TopHundred;
import com.chess.players.model.TopRecord;
import com.chess.players.util.ConvertHtmlToJson;
import com.chess.players.util.HttpRequestUtil;

class PlayerServiceTest {

    private static final String FIDE_ID = "12345";
    private static final String RANK = "men";

    @InjectMocks
    private PlayerService service;

    @Mock
    private HttpRequestUtil requestUtil;

    @Mock
    private ConvertHtmlToJson convertHtmlToJson;

    private Player player;
    private TopHundred topHundred;
    private Ratings ratings;
    private ChartByPlayer chartPlayer;
    private Chart chart;
    private TopRecord record;
    //private RecordsByPlayer recordsByPlayer;
    private ResponseEntity responseEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        start();
    }

    @Test
    void findTop100Players() throws Exception {
        when(convertHtmlToJson.convertHtmlToJson(anyString(), anyString())).thenReturn(List.of(topHundred));
        when(requestUtil.get(anyString())).thenReturn(responseEntity);

        List<TopHundred> list = service.findTop100Players(RANK);

        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(TopHundred.class, list.get(0).getClass());
    }

    @Test
    void findPlayer() throws Exception {
        when(convertHtmlToJson.convertHtmlPlayerToJson(anyString())).thenReturn(player);
        when(requestUtil.get(anyString())).thenReturn(responseEntity);

        Player response = service.findPlayer(FIDE_ID);

        assertNotNull(response);
        assertTrue(!response.getName().isEmpty());
        assertEquals(Player.class, response.getClass());
    }

    @Test
    void findChartPlayer() throws Exception {
        when(convertHtmlToJson.convertHtmlChartToJson(anyString())).thenReturn(chartPlayer);
        when(requestUtil.get(anyString())).thenReturn(responseEntity);

        ChartByPlayer response = service.findChartPlayer(FIDE_ID);

        assertNotNull(response);
        assertNotNull(response.getName());
        assertNotNull(response.getChart());
        assertTrue(!response.getChart().get(0).getPeriod().isEmpty());
    }

    @Test
    void findTopRecords() throws Exception {
        when(convertHtmlToJson.convertHtmlTopRecordsToJson(anyString(), anyString())).thenReturn(List.of(record));
        when(convertHtmlToJson.convertHtmlPlayerToJson(anyString())).thenReturn(player);
        when(requestUtil.get(anyString())).thenReturn(responseEntity);

        RecordsByPlayer response = service.findTopRecords(FIDE_ID);

        assertNotNull(response);
        assertNotNull(response.getName());
        assertNotNull(response.getRecords());
        assertTrue(!response.getRecords().get(0).getPeriod().isEmpty());

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
        //recordsByPlayer = new RecordsByPlayer("Magnus, Carlsen", List.of(record));
        responseEntity = new ResponseEntity(new String(), HttpStatus.OK);
    }
}