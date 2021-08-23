package com.example.homeworkten;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class CardSourceImpl implements com.example.homeworkten.CardSource {

    private List<com.example.homeworkten.CardData> dataSource;

    private Resources resources;

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public com.example.homeworkten.CardData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, com.example.homeworkten.CardData newCardData) {
        dataSource.set(position, newCardData);
    }

    @Override
    public void addCardData(com.example.homeworkten.CardData newCardData) {
        dataSource.add(newCardData);
    }

    @Override
    public void clearCardData() {
        dataSource.clear();
    }

    public CardSourceImpl(Resources resources) {
        dataSource = new ArrayList<>();
        this.resources = resources;
    }

    public CardSourceImpl init() {

        String[] note = resources.getStringArray(R.array.item_notes);
        String[] description = resources.getStringArray(R.array.item_description);

        for (int i = 0; i < note.length; i++) {
            dataSource.add(new com.example.homeworkten.CardData(note[i], description[i]));
        }
        return this;
    }
}
