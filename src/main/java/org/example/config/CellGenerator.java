package org.example.config;

import lombok.Getter;
import org.example.entity.map.Cell;

@Getter
public class CellGenerator {
    private final GameObjectGenerator gameObjectGenerator = GameObjectGenerator.getInstance();
    private static final CellGenerator instance = new CellGenerator();
    private CellGenerator() {
    }

    public static CellGenerator getInstance() {
        return instance;
    }

    private Cell createCell() {
        Cell cell = Cell.builder().build();
        cell.setResidents(gameObjectGenerator.generateGameObjects(cell));
        return cell;
    }
    public Cell[][] createCells(int width, int height) {
        Cell[][] cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = createCell();
            }
        }
        return cells;
    }
}
