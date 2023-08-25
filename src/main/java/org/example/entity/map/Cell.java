package org.example.entity.map;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.gameObjects.GameObject;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


@Getter
@Builder
public class Cell {
    private static long serialUID = 1L;
    @Setter
    @Getter
    private Map<Class<? extends GameObject>, Set<GameObject>> residents;

    @Builder.Default
    private final long UID = serialUID++;

    @Builder.Default
    private final ReentrantLock lock = new ReentrantLock();
    private final List<Cell> nextCells = new ArrayList<>();
    private Cell currentCell;

    public void setNextCell(Cell cell) {
        nextCells.add(cell);
    }
    public Set<GameObject> getResidents(Class<? extends GameObject> residentClass) {
        if (residents.containsKey(residentClass)) {
            return residents.get(residentClass);
        } else {
            return new HashSet<>();
        }
    }
}
