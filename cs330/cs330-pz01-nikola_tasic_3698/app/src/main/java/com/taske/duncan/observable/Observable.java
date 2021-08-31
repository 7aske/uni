package com.taske.duncan.observable;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {
    protected List<Observer<T>> observerList;

    public Observable() {
        this.observerList = new ArrayList<>();
    }

    protected void doNotifyObservers(T data) {
        observerList.forEach(o -> o.update(data));
    }

    public void notifyObservers(T data) {
        doNotifyObservers(data);
    }

    public void subscribe(Observer<T> observer) {
        observerList.add(observer);
    }
}
