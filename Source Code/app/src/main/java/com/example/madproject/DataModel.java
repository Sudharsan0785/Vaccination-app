package com.example.madproject;

public class DataModel {
    private int id;
    private String name;

    public DataModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters for id and name
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

