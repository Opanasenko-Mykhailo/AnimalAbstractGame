package org.example.gameObjects;

import org.example.entity.map.Cell;
import org.example.entity.organism.Limits;
import org.example.entity.organism.Reproducible;

public interface GameObject extends Reproducible{
    void play();
    Limits getLimits();
    void setGameObjectCell(Cell cell);
    Cell getCell();
    String getGameObjectIcon();

}

