package com.chess.players.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component
public class ConvertHtmlToJson {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public JsonObject convertHtmlToJson(String source, String rank) throws JSONException {
        int index = 0;
        Document doc = Jsoup.parse(source);
        JSONObject jsonObject = null;
        JSONObject teste = new JSONObject();
        JSONArray array = new JSONArray();
        String[] texts = null;
        for (Element table : doc.select("table")) {
            for (Element row : table.select("tr")) {
                texts = new String[6];
                jsonObject = new JSONObject();
                Elements tds = row.select("td");

                for (Element e : tds) {
                    texts[index] = e.text();
                    index++;
                }
                if (texts.length > 0 && texts[0] != null) {
                    jsonObject.put("position", Integer.parseInt(texts[0]));
                    jsonObject.put("name", texts[1]);
                    jsonObject.put("federation", texts[2]);
                    jsonObject.put("standardRating", Integer.parseInt(texts[3]));
                    jsonObject.put("yearOfBirth", Integer.parseInt(texts[5]));
                    array.put(jsonObject);
                }
                index = 0;
            }
        }
        teste.put("Top 100 " + rank, array);
        return gson.fromJson(teste.toString(), JsonObject.class);
    }

    public JsonObject convertHtmlToJson(String source) throws JSONException {
        int index = 0;
        Document document = Jsoup.parse(source);
        JSONObject player = new JSONObject();
        JSONObject ratingsJson = new JSONObject();
        String[] profile = new String[6];
        String[] ratings = new String[3];

        Element divProfile = document.getElementsByClass("profile-top__right").first();

        Elements dataRatings = divProfile.getElementsByClass("profile-top-rating-data");
        for (Element e : dataRatings) {
            ratings[index] = e.text().contains("Not rated") ? "Not rated" : e.text().replaceAll("[\\D]", "");
            index++;
        }
        if (ratings.length > 0) {
            ratingsJson.put("ratingStandard",
                    ratings[0].contains("Not rated") ? ratings[0] : Integer.parseInt(ratings[0]));
            ratingsJson.put("ratingRapid",
                    ratings[1].contains("Not rated") ? ratings[1] : Integer.parseInt(ratings[1]));
            ratingsJson.put("ratingBlitz",
                    ratings[2].contains("Not rated") ? ratings[2] : Integer.parseInt(ratings[2]));
        }

        index = 0;

        Elements name = divProfile.getElementsByClass("profile-top-title");
        player.put("name", name.first().text());

        Elements dataProfile = divProfile.getElementsByClass("profile-top-info__block__row__data");

        for (Element e : dataProfile) {
            profile[index] = e.text();
            index++;
        }
        if (profile.length > 0) {
            player.put("position", Integer.parseInt(profile[0]));
            player.put("federation", profile[1]);
            player.put("fideID", profile[2]);
            player.put("yearOfBirth", Integer.parseInt(profile[3]));
            player.put("title", profile[5]);
            player.put("ratings", ratingsJson);
        }
        return gson.fromJson(player.toString(), JsonObject.class);
    }

    public JsonObject convertChartToJson(String source) throws JSONException {
        int index = 0;
        Document document = Jsoup.parse(source);
        JSONObject chart = null;
        JSONArray dataChart = new JSONArray();

        String[] data = null;

        Element divProfile = document.getElementsByClass("profile-top__right").first();
        Elements name = divProfile.getElementsByClass("profile-top-title");

        for (Element table : document.getElementsByClass("profile-table_chart-table")) {

            for (Element tr : table.select("tr")) {
                data = new String[7];
                chart = new JSONObject();
                Elements tds = tr.select("td");

                for (Element e : tds) {
                    data[index] = e.text();
                    index++;
                }

                if (data.length > 0 && data[0] != null) {
                    chart.put("period", data[0]);
                    chart.put("standardRating", data[1] != null && !data[1].isEmpty() ? Integer.parseInt(data[1]) : 0);
                    chart.put("rapidRating", data[3] != null && !data[3].isEmpty() ? Integer.parseInt(data[3]) : 0);
                    chart.put("blitzRating", data[5] != null && !data[5].isEmpty() ? Integer.parseInt(data[5]) : 0);
                    dataChart.put(chart);
                }
                index = 0;
            }
        }
        return gson.fromJson(new JSONObject().put(name.first().text(), dataChart).toString(), JsonObject.class);
    }

    public JsonArray convertTopRecordsToJson(String source) throws JSONException {
        int index = 0;
        Document document = Jsoup.parse(source);
        JSONObject recordJson = null;
        JSONArray records = new JSONArray();
        String[] record = new String[6];

        for (Element tr : document.select("table").select("tr")) {
            recordJson = new JSONObject();
            for (Element td : tr.select("td")) {
                record[index] = td.text();
                index++;
            }
            index = 0;
            if (record.length > 0 && record[0] != null) {
                recordJson.put("period", record[0]);
                recordJson.put("position", record[1]);
                recordJson.put("title", record[2]);
                recordJson.put("rating", record[3]);
                recordJson.put("games", record[4]);
                records.put(recordJson);
            }
        }
        return gson.fromJson(records.toString(), JsonArray.class);
    }
}
