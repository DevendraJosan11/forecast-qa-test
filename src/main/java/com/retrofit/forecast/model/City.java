package com.retrofit.forecast.model;

public class City {

    private String title;
    private Long woeid;

    public String getTitle() {
        return title;
    }

    public City setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getWoeid() {
        return woeid;
    }

    public City setWoeid(Long woeid) {
        this.woeid = woeid;
        return this;
    }
}
