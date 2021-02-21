package com.vukic.rma_projekt_1;

import java.io.Serializable;
import java.util.ArrayList;

public class results implements Serializable {

    private boolean adult;
    private String backdrop_path;
    private ArrayList<Integer> genre_ids;
    private int id;
    private String original_language;
    private String original_title;
    private String overview;
    private String release_date;

    public results(boolean adult, String original_title, String backdrop_path , String release_date) {
        this.adult = adult;
        this.original_title = original_title;
        this.backdrop_path=backdrop_path;
        this.release_date = release_date;
        //this.poster_path = poster_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getBackdrop_path() {
        return "https://image.tmdb.org/t/p/w500/" + backdrop_path;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

}

