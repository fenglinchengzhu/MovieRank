package com.example.kinglu.movierank;

public class MovieCelebrity {
    private String mCelebrityName;
    private String mCelebrityImageUrl;
    private String mCelebritySummary;
    private String mCelebrityId;
    private String mCelebrityCast;

    public MovieCelebrity(String name, String id){
        this.mCelebrityName = name;
        this.mCelebrityId = id;
    }

    public MovieCelebrity(String name, String imageUrl, String id){
        this.mCelebrityName = name;
        this.mCelebrityImageUrl = imageUrl;
        this.mCelebrityId = id;
    }

    public void setCelebrityName(String celebrityName) {
        this.mCelebrityName = celebrityName;
    }

    public String getCelebrityName() {
        return mCelebrityName;
    }

    public void setCelebritySummary(String celebritySummary) {
        this.mCelebritySummary = celebritySummary;
    }

    public String getCelebritySummary() {
        return mCelebritySummary;
    }

    public void setCelebrityId(String celebrityId) {
        this.mCelebrityId = celebrityId;
    }

    public String getCelebrityId() {
        return mCelebrityId;
    }

    public void setCelebrityImageUrl(String celebrityImageUrl) {
        this.mCelebrityImageUrl = celebrityImageUrl;
    }

    public String getCelebrityImageUrl() {
        return mCelebrityImageUrl;
    }

    public String getCelebrityCast() {
        return mCelebrityCast;
    }

    public void setCelebrityCast(String celebrityCast) {
        this.mCelebrityCast = celebrityCast;
    }
}

