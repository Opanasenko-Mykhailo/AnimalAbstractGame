package org.example.entity.map;


import org.example.utils.Probably;
import org.example.gameObjects.GameObject;

import java.util.*;
public class CellHelper {
    private static final Object lock = new Object(); // Спільний лок для всіх операцій

    public static List<Cell> getNeighborCells(Cell cell) {
        synchronized (lock) {
            return cell.getNextCells();
        }
    }

    public static void addOrganism(Cell cell, GameObject organism) {
        synchronized (lock) {
            if (cell != null && organism != null) {
                // Копіюємо residents для зміни
                Map<Class<? extends GameObject>, Set<GameObject>> residentsCopy = new HashMap<>(cell.getResidents());
                residentsCopy.computeIfAbsent(organism.getClass(), k -> new HashSet<>()).add(organism);
                cell.setResidents(residentsCopy);
                organism.setGameObjectCell(cell);
            }
        }
    }

    public static void removeOrganism(Cell cell, GameObject organism) {
        synchronized (lock) {
            if (cell != null && organism != null) {
                // Копіюємо residents для зміни
                Map<Class<? extends GameObject>, Set<GameObject>> residentsCopy = new HashMap<>(cell.getResidents());
                residentsCopy.computeIfPresent(organism.getClass(), (k, v) -> {
                    v.remove(organism);
                    return v.isEmpty() ? null : v;
                });
                cell.setResidents(residentsCopy);
                organism.setGameObjectCell(null);
            }
        }
    }


    public static void moveOrganismToCell(Cell sourceCell, Cell targetCell, GameObject organism) {
        synchronized (lock) {
            if (sourceCell != null && targetCell != null && organism != null) {
                // Копіюємо residents для зміни
                Map<Class<? extends GameObject>, Set<GameObject>> sourceResidentsCopy = new HashMap<>(sourceCell.getResidents());
                Map<Class<? extends GameObject>, Set<GameObject>> targetResidentsCopy = new HashMap<>(targetCell.getResidents());

                sourceResidentsCopy.computeIfPresent(organism.getClass(), (k, v) -> {
                    v.remove(organism);
                    return v.isEmpty() ? null : v;
                });
                targetResidentsCopy.computeIfAbsent(organism.getClass(), k -> new HashSet<>()).add(organism);

                sourceCell.setResidents(sourceResidentsCopy);
                targetCell.setResidents(targetResidentsCopy);
                organism.setGameObjectCell(targetCell);
            }
        }
    }
    public static Cell chooseNextCell(List<Cell> neighborCells, int distance) {
        Cell nextCell = null;
        for (int i = 0; i < distance; i++) {
            List<Cell> nextNeighborCells = new ArrayList<>();
            for (Cell cell : neighborCells) {
                nextNeighborCells.addAll(CellHelper.getNeighborCells(cell));
            }
            if (!nextNeighborCells.isEmpty()) {
                int randomIndex = Probably.randomInt(0, nextNeighborCells.size());
                nextCell = nextNeighborCells.get(randomIndex);
                neighborCells = new ArrayList<>(nextNeighborCells);
            } else {
                break;
            }
        }
        return nextCell;
    }
}
