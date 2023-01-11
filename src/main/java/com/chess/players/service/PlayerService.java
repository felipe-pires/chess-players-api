package com.chess.players.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chess.players.model.ChartByPlayer;
import com.chess.players.model.EventsByPlace;
import com.chess.players.model.Player;
import com.chess.players.model.RecordsByPlayer;
import com.chess.players.model.TopHundred;
import com.chess.players.model.TopRecord;
import com.chess.players.util.ConvertHtmlToJson;
import com.chess.players.util.HttpRequestUtil;

@Service
public class PlayerService {

    @Autowired
    private HttpRequestUtil requestUtil;

    @Autowired
    private ConvertHtmlToJson convert;

    public List<TopHundred> findTop100Players(String rank) throws Exception {
        ResponseEntity responseEntity = requestUtil.get("https://ratings.fide.com/a_top.phtml?list=" + rank);
        return convert.convertHtmlToJson(responseEntity.getBody().toString(), rank);
    }

    public Player findPlayer(String fideId) throws Exception {
        ResponseEntity responseEntity = requestUtil.get("https://ratings.fide.com/profile/" + fideId);
        return convert.convertHtmlToJson(responseEntity.getBody().toString());
    }

    public ChartByPlayer findChartPlayer(String fideId) throws Exception {
        ResponseEntity responseEntity = requestUtil.get("https://ratings.fide.com/profile/" + fideId + "/chart");
        return convert.convertHtmlChartToJson(responseEntity.getBody().toString());
    }

    public RecordsByPlayer findTopRecords(String fideId) throws Exception {
        ResponseEntity responseEntity = requestUtil.get("https://ratings.fide.com/a_top_records.phtml?event=" + fideId);
        List<TopRecord> records = convert.convertHtmlTopRecordsToJson(responseEntity.getBody().toString(), fideId);
        String name = findPlayer(fideId).getName();
        return new RecordsByPlayer(name, records);
    }

    public List<EventsByPlace> findEvents() throws Exception {
        ResponseEntity responseEntity = requestUtil.get("https://fide.com/calendar");
        return convert.convertHtmlEventsToJson(responseEntity.getBody().toString());
    }
}
