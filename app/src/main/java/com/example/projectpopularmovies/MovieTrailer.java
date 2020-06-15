package com.example.projectpopularmovies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieTrailer {
    @SerializedName("id")
    private String id;
    @SerializedName("iso_639_1")
    private String iso639;
    @SerializedName("iso_3166_1")
    private String iso3166;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("name")
    private String name;
    @SerializedName("site")
    private String site;
    @SerializedName("size")
    private int size;
    @SerializedName("type")
    private String type;

    public String getId() {
        return id;
    }

    public String getIso639() {
        return iso639;
    }

    public String getIso3166() {
        return iso3166;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}
