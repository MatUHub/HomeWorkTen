package com.example.homeworkten;

public class CardData {
    private String note;

    private String description;

    public CardData(String note, String description) {
        this.note = note;
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public String getDescription() {
        return description;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
