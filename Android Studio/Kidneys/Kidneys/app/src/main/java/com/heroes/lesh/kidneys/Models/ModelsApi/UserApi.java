package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserApi {
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("gender")
    @Expose
    String gender;
    @SerializedName("image")
    @Expose
    String image;
    @SerializedName("dateofbirth")
    @Expose
    String dateOfBirth;
    @SerializedName("weight")
    @Expose
    double weight;
    @SerializedName("height")
    @Expose
    double height;
    @SerializedName("datecatheter")
    @Expose
    String dateCatheter;
    @SerializedName("typeofsolution")
    @Expose
    double typeOfSolution;
    @SerializedName("imc")
    @Expose
    double imc;
    @SerializedName("typeoftreatment")
    @Expose
    String typeOfTreatment;
    @SerializedName("emergencycontact")
    @Expose
    String emergencycontact;

    public UserApi() {

    }

    public UserApi(String email, String name, String gender, String image, String dateOfBirth, double weight, double height, String dateCatheter, double typeOfSolution, double imc, String typeOfTreatment, String emergencycontact) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.image = image;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;
        this.dateCatheter = dateCatheter;
        this.typeOfSolution = typeOfSolution;
        this.imc = imc;
        this.typeOfTreatment = typeOfTreatment;
        this.emergencycontact = emergencycontact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getDateCatheter() {
        return dateCatheter;
    }

    public void setDateCatheter(String dateCatheter) {
        this.dateCatheter = dateCatheter;
    }

    public double getTypeOfSolution() {
        return typeOfSolution;
    }

    public void setTypeOfSolution(double typeOfSolution) {
        this.typeOfSolution = typeOfSolution;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public String getTypeOfTreatment() {
        return typeOfTreatment;
    }

    public void setTypeOfTreatment(String typeOfTreatment) {
        this.typeOfTreatment = typeOfTreatment;
    }

    public String getEmergencycontact() {
        return emergencycontact;
    }

    public void setEmergencycontact(String emergencycontact) {
        this.emergencycontact = emergencycontact;
    }
}
