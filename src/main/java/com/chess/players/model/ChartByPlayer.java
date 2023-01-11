package com.chess.players.model;

import java.util.List;

public class ChartByPlayer {
    private String name;
    private List<Chart> chart;

    public ChartByPlayer(String name, List<Chart> chart) {
        this.name = name;
        this.chart = chart;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Chart> getChart() {
        return this.chart;
    }

    public void setChart(List<Chart> chart) {
        this.chart = chart;
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", chart='" + getChart() + "'" +
                "}";
    }
}
