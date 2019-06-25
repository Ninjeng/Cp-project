package com.example.cpproject;

public class Books {
    private String Title;
    private String Author;
    private String Year;
    private String Publisher;
    private String url;

    public Books() {
    }

    public Books(String title, String author, String year, String publisher, String url) {

        Title = title;
        Author = author;
        Year = year;
        Publisher = publisher;
        this.url = url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
