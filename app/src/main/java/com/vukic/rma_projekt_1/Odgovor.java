package com.vukic.rma_projekt_1;

import java.util.ArrayList;

public class Odgovor {

    //public boolean isAdult;
    private int page;
    private ArrayList<results> results;

    /*public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }*/

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<results> getResults() {
        return results;
    }

    public void setResults(ArrayList<results> results) {
        this.results = results;
    }
}
