package com.example.homeworkten.ripository;

import android.content.res.Resources;

import com.example.homeworkten.R;
import com.example.homeworkten.model.CardData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CardSourceImpl implements CardSource {

    private List<CardData> dataSource;

    private Resources resources;

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData newCardData) {
        dataSource.set(position, newCardData);
    }

    @Override
    public void addCardData(CardData newCardData) {
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
            dataSource.add(new CardData(note[i], description[i], Calendar.getInstance().getTime()));
        }
        return this;
    }
}
