package com.chess.players.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chess.players.util.ConvertHtmlToJson;
import com.chess.players.util.HttpRequestUtil;
import com.google.gson.JsonObject;

@Service
public class PlayerService {

    @Autowired
    private HttpRequestUtil requestUtil;

    @Autowired
    private ConvertHtmlToJson convert;

    public JsonObject findTop100Players(String rank) throws Exception {
        ResponseEntity responseEntity = requestUtil.get("https://ratings.fide.com/a_top.phtml?list=" + rank);
        return convert.convertHtmlToJson(responseEntity.getBody().toString(), rank);
    }

    public JsonObject findPlayer(String fideId) throws Exception{
        ResponseEntity responseEntity = requestUtil.get("https://ratings.fide.com/profile/" + fideId);
        return convert.convertHtmlToJson(responseEntity.getBody().toString());
    }

    public JsonObject findChartPlayer(String fideId) throws Exception{
        ResponseEntity responseEntity = requestUtil.get("https://ratings.fide.com/profile/" + fideId + "/chart");
        return convert.convertHtmlChartToJson(responseEntity.getBody().toString());
    }

}
