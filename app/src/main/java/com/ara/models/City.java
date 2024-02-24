package com.ara.models;

public class City {

    private String food;
    private String history;
    private String map;
    private String title;
    private String visit;

    public City() {
    }

    public City(String food, String history, String map, String title, String visit) {
        this.food = food;
        this.history = history;
        this.map = map;
        this.title = title;
        this.visit = visit;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }
}
