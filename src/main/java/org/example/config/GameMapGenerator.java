package org.example.config;

import lombok.Getter;
import org.example.entity.map.Cell;
import org.example.entity.map.GameMap;

public class GameMapGenerator {
    @Getter
    private static final GameMapGenerator instance = new GameMapGenerator();
    private final CellGenerator cellGenerator = CellGenerator.getInstance();
    private final PrototypeLoader prototypeLoader = PrototypeLoader.getInstance();

    private GameMapGenerator() {
    }

    public GameMap createGameMap() {
        GameMap gameMap = prototypeLoader.loadPrototype(GameMap.class);
        Cell[][] cells = cellGenerator.createCells(gameMap.getWidth(), gameMap.getHeight());
        mapCells(cells);
        gameMap.setCells(cells);
        return gameMap;
    }

    private void mapCells(Cell[][] cells) {
        int numRows = cells.length;
        int numCols = cells[0].length;

        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numCols; column++) {
                Cell currentCell = cells[row][column];

                if (column + 1 < numCols) {
                    currentCell.setNextCell(cells[row][column + 1]); // Сусід справа
                }
                if (row + 1 < numRows) {
                    currentCell.setNextCell(cells[row + 1][column]); // Сусід знизу
                }
                if (column - 1 >= 0) {
                    currentCell.setNextCell(cells[row][column - 1]); // Сусід зліва
                }
                if (row - 1 >= 0) {
                    currentCell.setNextCell(cells[row - 1][column]); // Сусід зверху
                }
            }
        }
    }
}