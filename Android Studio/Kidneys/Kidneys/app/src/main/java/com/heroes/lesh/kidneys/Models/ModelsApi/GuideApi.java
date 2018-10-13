package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GuideApi {
    @SerializedName("idguide")
    @Expose
    private int idGuide;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("numpages")
    @Expose
    private int numPages;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("pdf")
    @Expose
    private String pdf;
    @SerializedName("author")
    @Expose
    String author;
    @SerializedName("yearpublication")
    @Expose
    String yearPublication;
    @SerializedName("reference")
    @Expose
    String reference;
    @SerializedName("content")
    @Expose
    String content;

    public GuideApi() {

    }

    public GuideApi(int idGuide, String name, String description, int numPages, String image, String pdf, String author, String yearPublication, String reference, String content) {
        this.idGuide = idGuide;
        this.name = name;
        this.description = description;
        this.numPages = numPages;
        this.image = image;
        this.pdf = pdf;
        this.author = author;
        this.yearPublication = yearPublication;
        this.reference = reference;
        this.content = content;
    }

    public int getIdGuide() {
        return idGuide;
    }

    public void setIdGuide(int idGuide) {
        this.idGuide = idGuide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(String yearPublication) {
        this.yearPublication = yearPublication;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
