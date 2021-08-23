package com.example.homeworkten;

public interface CardSource {
    int size();
    com.example.homeworkten.CardData getCardData(int position);

    void deleteCardData(int position);
    void updateCardData(int position, com.example.homeworkten.CardData newCardData);
    void addCardData(com.example.homeworkten.CardData newCardData);
    void clearCardData();
}
