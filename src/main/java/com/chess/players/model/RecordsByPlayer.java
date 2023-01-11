package com.chess.players.model;

import java.util.List;

public class RecordsByPlayer {
    private String name;
    private List<TopRecord> records;

    public RecordsByPlayer(String name, List<TopRecord> records) {
        this.name = name;
        this.records = records;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TopRecord> getRecords() {
        return this.records;
    }

    public void setRecords(List<TopRecord> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", records='" + getRecords() + "'" +
                "}";
    }
}
