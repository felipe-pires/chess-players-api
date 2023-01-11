package com.chess.players.model;

public class Player {
    private Integer position;
    private String name;
    private String federation;
    private String fideId;
    private Integer yearOfBirth;
    private String title;
    private Ratings ratings;

    public Player(Integer position, String name, String federation, String fideId, Integer yearOfBirth, String title,
            Ratings ratings) {
        this.position = position;
        this.name = name;
        this.federation = federation;
        this.fideId = fideId;
        this.yearOfBirth = yearOfBirth;
        this.title = title;
        this.ratings = ratings;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFederation() {
        return this.federation;
    }

    public void setFederation(String federation) {
        this.federation = federation;
    }

    public String getFideId() {
        return this.fideId;
    }

    public void setFideId(String fideId) {
        this.fideId = fideId;
    }

    public Integer getYearOfBirth() {
        return this.yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Ratings getRatings() {
        return this.ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "{" +
                " position='" + getPosition() + "'" +
                ", name='" + getName() + "'" +
                ", federation='" + getFederation() + "'" +
                ", fideId='" + getFideId() + "'" +
                ", yearOfBirth='" + getYearOfBirth() + "'" +
                ", title='" + getTitle() + "'" +
                ", ratings='" + getRatings() + "'" +
                "}";
    }
}
