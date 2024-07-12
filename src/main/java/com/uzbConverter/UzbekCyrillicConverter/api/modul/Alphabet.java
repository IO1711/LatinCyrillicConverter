package com.uzbConverter.UzbekCyrillicConverter.api.modul;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Alphabet {

    @Id
    @GeneratedValue
    private int id;

    private String latin;

    private String cyrillic;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatin() {
        return this.latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public String getCyrillic() {
        return this.cyrillic;
    }

    public void setCyrillic(String cyrillic) {
        this.cyrillic = cyrillic;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", latin='" + getLatin() + "'" +
            ", cyrillic='" + getCyrillic() + "'" +
            "}";
    }

}
