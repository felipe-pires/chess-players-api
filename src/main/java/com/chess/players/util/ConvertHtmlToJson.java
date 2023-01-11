package com.chess.players.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.chess.players.model.Chart;
import com.chess.players.model.ChartByPlayer;
import com.chess.players.model.Event;
import com.chess.players.model.EventsByPlace;
import com.chess.players.model.Player;
import com.chess.players.model.Ratings;
import com.chess.players.model.TopHundred;
import com.chess.players.model.TopRecord;

@Component
public class ConvertHtmlToJson {

    public List<TopHundred> convertHtmlToJson(String source, String rank) throws Exception {
        int index = 0;
        Document doc = Jsoup.parse(source);
        TopHundred player = null;
        List<TopHundred> listPlayers = new ArrayList<>();

        String[] texts = null;

        try {
            for (Element table : doc.select("table")) {
                for (Element row : table.select("tr")) {
                    texts = new String[6];

                    Elements tds = row.select("td");

                    for (Element e : tds) {
                        texts[index] = e.text();
                        index++;
                    }
                    if (texts.length > 0 && texts[0] != null) {
                        player = new TopHundred(
                                Integer.parseInt(texts[0]),
                                texts[1],
                                texts[2],
                                Integer.parseInt(texts[3]),
                                Integer.parseInt(texts[5]));
                        listPlayers.add(player);
                    }
                    index = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao buscar rank top 100" + rank);
        }
        return listPlayers;
    }

    public Player convertHtmlToJson(String source) throws Exception {
        int index = 0;
        Document document = Jsoup.parse(source);
        Player player = null;
        String[] profile = new String[6];
        String[] rating = new String[3];
        Ratings ratings = null;

        try {
            Element divProfile = document.getElementsByClass("profile-top__right").first();

            Elements dataRatings = divProfile.getElementsByClass("profile-top-rating-data");
            for (Element e : dataRatings) {
                rating[index] = e.text().contains("Not rated") ? e.text() : e.text().replaceAll("[\\D]", "");
                index++;
            }
            if (rating.length > 0) {
                ratings = new Ratings(
                        rating[0],
                        rating[1],
                        rating[2]);
            }

            index = 0;

            Elements name = divProfile.getElementsByClass("profile-top-title");

            Elements dataProfile = divProfile.getElementsByClass("profile-top-info__block__row__data");

            for (Element e : dataProfile) {
                profile[index] = e.text();
                index++;
            }
            if (profile.length > 0) {
                player = new Player(
                        Integer.parseInt(profile[0]),
                        name.first().text(),
                        profile[1],
                        profile[2],
                        Integer.parseInt(profile[3]),
                        profile[5],
                        ratings);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao buscar jogador");
        }
        return player;
    }

    public ChartByPlayer convertHtmlChartToJson(String source) throws Exception {
        int index = 0;
        Document document = Jsoup.parse(source);
        Chart chart = null;
        List<Chart> dataChart = new ArrayList<>();
        String[] data = null;
        Elements name = null;

        try {
            Element divProfile = document.getElementsByClass("profile-top__right").first();
            name = divProfile.getElementsByClass("profile-top-title");

            for (Element table : document.getElementsByClass("profile-table_chart-table")) {

                for (Element tr : table.select("tr")) {
                    data = new String[7];
                    Elements tds = tr.select("td");

                    for (Element e : tds) {
                        data[index] = e.text();
                        index++;
                    }

                    if (data.length > 0 && data[0] != null) {
                        chart = new Chart(
                                data[0],
                                data[1] != null && !data[1].isEmpty() ? data[1] : "0",
                                data[3] != null && !data[3].isEmpty() ? data[3] : "0",
                                data[5] != null && !data[5].isEmpty() ? data[5] : "0");
                        dataChart.add(chart);
                    }
                    index = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao buscar chart de progresso do jogador");
        }
        return new ChartByPlayer(name.first().text(), dataChart);
    }

    public List<TopRecord> convertHtmlTopRecordsToJson(String source, String fideId) throws Exception {
        int index = 0;
        Document document = Jsoup.parse(source);
        TopRecord topRecord = null;
        List<TopRecord> records = new ArrayList<>();
        String[] record = new String[6];

        try {
            for (Element tr : document.select("table").select("tr")) {

                for (Element td : tr.select("td")) {
                    record[index] = td.text();
                    index++;
                }
                index = 0;
                if (record.length > 0 && record[0] != null) {
                    topRecord = new TopRecord(
                            record[0],
                            record[3],
                            record[4],
                            record[1],
                            record[2]);
                    records.add(topRecord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao buscar records de jogador");
        }
        return records;
    }

    public List<EventsByPlace> convertHtmlEventsToJson(String source) throws Exception {
        int index = 0;
        int indexCalendar = 0;
        Document document = Jsoup.parse(source);
        List<EventsByPlace> eventsByPlace = new ArrayList<>();
        Event dataEvent = null;
        List<Event> events = null;
        String[] event = new String[4];
        String[] calendars = new String[5];

        try {
            for (Element div : document.getElementsByClass("calendar-table__title")) {
                calendars[index] = div.text();
                index++;
            }
            index = 0;

            for (Element div : document.getElementsByClass("calendar-table")) {
                events = new ArrayList<>();
                for (Element table : div.select("tbody")) {
                    for (Element tr : table.select("tr")) {

                        Elements tds = tr.select("td");

                        for (Element td : tds) {
                            event[index] = td.text();
                            index++;
                        }

                        index = 0;

                        if (event.length > 0 && event[0] != null && !event[0].contains("More")) {
                            dataEvent = new Event(
                                    event[0],
                                    event[1],
                                    event[2],
                                    event[3]);
                            events.add(dataEvent);
                        }
                    }
                    eventsByPlace.add(new EventsByPlace(calendars[indexCalendar], events));
                    indexCalendar++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao buscar eventos");
        }
        return eventsByPlace;
    }
}
