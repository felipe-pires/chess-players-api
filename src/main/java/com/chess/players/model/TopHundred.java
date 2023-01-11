package com.chess.players.model;

public class TopHundred {

    private Integer position;
    private String federation;
    private String name;
    private Integer yearOfBirth;
    private Integer standardRating;

    public TopHundred(Integer position, String name, String federation, Integer standardRating, Integer yearOfBirth) {
        this.position = position;
        this.federation = federation;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.standardRating = standardRating;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getFederation() {
        return this.federation;
    }

    public void setFederation(String federation) {
        this.federation = federation;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearOfBirth() {
        return this.yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getStandardRating() {
        return this.standardRating;
    }

    public void setStandardRating(Integer standardRating) {
        this.standardRating = standardRating;
    }

    @Override
    public String toString() {
        return "{" +
                " position='" + getPosition() + "'" +
                ", federation='" + getFederation() + "'" +
                ", name='" + getName() + "'" +
                ", yearOfBirth='" + getYearOfBirth() + "'" +
                ", standardRating='" + getStandardRating() + "'" +
                "}";
    }
}
