package com.example.projectmarvel.presentation.model;

import com.example.projectmarvel.presentation.model.Items;

import java.util.List;

public class Format {
    private Integer available;
    private String collectionURI;
    private List<Items> items;
    private Integer returned;

    public Integer getAvailable() {
        return available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public List<Items> getItems() {
        return items;
    }

    public Integer getReturned() {
        return returned;
    }
}
