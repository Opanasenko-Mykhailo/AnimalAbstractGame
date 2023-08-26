package org.example;

import org.example.config.AppConfigurator;

public class Main {
    public static void main(String[] args) {
        AppConfigurator.getInstance().init();
    }
}