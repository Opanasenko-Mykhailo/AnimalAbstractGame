package org.example.factory;

import org.example.gameObjects.GameObject;

public interface PrototypeFactory<T> {
    T createPrototype(Class<? extends T> type);
}