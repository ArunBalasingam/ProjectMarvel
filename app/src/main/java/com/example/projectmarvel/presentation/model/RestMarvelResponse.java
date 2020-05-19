package com.example.projectmarvel.presentation.model;

public class RestMarvelResponse {
    private Integer code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private String etag;
    private Data data;

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public String getEtag() {
        return etag;
    }

    public Data getData() {
        return data;
    }
}
