package com.chess.players.model;

public class Ratings {
    private String ratingStandard;
    private String ratingRapid;
    private String ratingBlitz;

    public Ratings(String ratingStandard, String ratingRapid, String ratingBlitz) {
        this.ratingStandard = ratingStandard;
        this.ratingRapid = ratingRapid;
        this.ratingBlitz = ratingBlitz;
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
            " ratingStandard='" + getRatingStandard() + "'" +
            ", ratingRapid='" + getRatingRapid() + "'" +
            ", ratingBlitz='" + getRatingBlitz() + "'" +
            "}";
    }
}
