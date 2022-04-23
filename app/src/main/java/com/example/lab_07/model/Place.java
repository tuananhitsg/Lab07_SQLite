package com.example.lab_07;

import java.io.Serializable;

public class Place implements Serializable {
    private int id;
    private String name;

    public Place(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Place(int id) {
        this.id = id;
    }

    public Place(String name) {
        this.name = name;
    }

    public Place() {
    }

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
