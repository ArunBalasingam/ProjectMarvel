package com.example.projectmarvel.presentation.model;

import java.util.List;

public class Results {
    private Integer id;
    private String name;
    private String description;
    private String modified;
    private Thumbnail thumbnail;
    private String resourceURI;
    private Comics comics;
    private Series series;
    private Stories stories;
    private Events events;
    private List<Urls> urls;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getModified() {
        return modified;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public Comics getComics() {
        return comics;
    }

    public Series getSeries() {
        return series;
    }

    public Stories getStories() {
        return stories;
    }

    public Events getEvents() {
        return events;
    }

    public List<Urls> getUrls() {
        return urls;
    }
}
