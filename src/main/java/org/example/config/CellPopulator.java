package org.example.config;


import org.example.entity.map.Cell;
import org.example.entity.map.GameMap;


public class CellPopulator {
   private final GameObjectGenerator gameObjectGenerator = GameObjectGenerator.getInstance();
   private static CellPopulator instance;
   private CellPopulator() {
   }

   public static CellPopulator getInstance() {
      if (instance == null) {
         instance = new CellPopulator();
      }
      return instance;
   }

   public void populateGameMap(GameMap gameMap) {
      Cell[][] cells = gameMap.getCells(); // Отримати вже створені клітинки
      for (int row = 0; row < cells.length; row++) {
         for (int column = 0; column < cells[row].length; column++) {
            Cell cell = cells[row][column];
            populateCellWithResidents(cell); // Заповнити резидентів для клітинки
         }
      }
   }

   private void populateCellWithResidents(Cell cell) {
      cell.setResidents(gameObjectGenerator.generateGameObjects(cell));
   }
}