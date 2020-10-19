package com.enea.allergendatabase.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "allergen")
public class Allergen {

    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String allergen_name, allergen_information,allergen_occurrence, image_url;

    public Allergen(){

    }

    public Allergen(String allergen_name, String allergen_information, String allergen_occurrence, String image_url) {
        this.allergen_name = allergen_name;
        this.allergen_information = allergen_information;
        this.allergen_occurrence = allergen_occurrence;
        this.image_url = image_url;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAllergen_name() {
        return allergen_name;
    }

    public void setAllergen_name(String allergen_name) {
        this.allergen_name = allergen_name;
    }

    public String getAllergen_information() {
        return allergen_information;
    }

    public void setAllergen_information(String allergen_information) {
        this.allergen_information = allergen_information;
    }

    public String getAllergen_occurrence() {
        return allergen_occurrence;
    }

    public void setAllergen_occurrence(String allergen_occurrence) {
        this.allergen_occurrence = allergen_occurrence;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
