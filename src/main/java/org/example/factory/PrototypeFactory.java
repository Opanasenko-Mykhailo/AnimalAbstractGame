package org.example.factory;


public interface PrototypeFactory<T> {
    T createPrototype(Class<? extends T> type);
}