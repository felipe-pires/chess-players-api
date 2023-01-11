package com.chess.players.model;

public class Chart {
    private String period;
    private String ratingStandard;
    private String ratingRapid;
    private String ratingBlitz;

    public Chart(String period, String ratingStandard, String ratingRapid, String ratingBlitz) {
        this.period = period;
        this.ratingStandard = ratingStandard;
        this.ratingRapid = ratingRapid;
        this.ratingBlitz = ratingBlitz;
    }

    public String getPeriod() {
        return this.period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getRatingStandard() {
        return this.ratingStandard;
    }

    public void setRatingStandard(String ratingStandard) {
        this.ratingStandard = ratingStandard;
    }

    public String getRatingRapid() {
        return this.ratingRapid;
    }

    public void setRatingRapid(String ratingRapid) {
        this.ratingRapid = ratingRapid;
    }

    public String getRatingBlitz() {
        return this.ratingBlitz;
    }

    public void setRatingBlitz(String ratingBlitz) {
        this.ratingBlitz = ratingBlitz;
    }

    @Override
    public String toString() {
        return "{" +
            " period='" + getPeriod() + "'" +
            ", ratingStandard='" + getRatingStandard() + "'" +
            ", ratingRapid='" + getRatingRapid() + "'" +
            ", ratingBlitz='" + getRatingBlitz() + "'" +
            "}";
    }
}
