package com.example.homeworkten;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Observer> observers;

    public Publisher() {
        this.observers = new ArrayList<Observer>();
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyTask(CardData cardData) {
        for (Observer observer : observers) {
            observer.updateState(cardData);
            unsubscribe(observer);
        }
    }
}
