package com.taske.duncan.observable;

public interface Observer<T> {
    void update(T payload);
}
