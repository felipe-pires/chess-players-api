package com.chess.players.model;

public class TopRecord {
    private String period;
    private String rating;
    private String games;
    private String position;
    private String title;

    public TopRecord(String period, String rating, String games, String position, String title) {
        this.period = period;
        this.rating = rating;
        this.games = games;
        this.position = position;
        this.title = title;
    }

    public String getPeriod() {
        return this.period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGames() {
        return this.games;
    }

    public void setGames(String games) {
        this.games = games;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "{" +
                " period='" + getPeriod() + "'" +
                ", rating='" + getRating() + "'" +
                ", games='" + getGames() + "'" +
                ", position='" + getPosition() + "'" +
                ", title='" + getTitle() + "'" +
                "}";
    }
}
