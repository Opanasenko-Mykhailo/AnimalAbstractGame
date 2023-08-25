package org.example.factory;


import java.util.Collection;

public interface PrototypeFactory<T> {
    T createPrototype(Class<? extends T> type);
    Collection<T> getPrototypes();
    void registerPrototype(T prototype);
}