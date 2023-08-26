package org.example.entity.organism.plant;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.entity.map.Cell;
import org.example.entity.organism.Limits;
import org.example.entity.organism.Organism;

import java.util.concurrent.locks.ReentrantLock;

@NoArgsConstructor
@SuperBuilder
@Getter
@EqualsAndHashCode
@ToString
public abstract class Plant implements Organism {
    private static long serialUID = 1L;
    private int lastReproductionYear = 0;

    @Builder.Default
    private final long UID = serialUID++;
    @Builder.Default
    private ReentrantLock lock = new ReentrantLock();
    @JsonIgnore
    private Cell cell;
    private String icon;
    private Limits limits;
    private boolean isEaten = false;
    public void play() {
        //Не реалізовано
    }
    @Override
    public void setGameObjectCell(Cell cell){
        this.cell = cell;
    }
    @Override
    public String getGameObjectIcon(){
        return icon;
    }

}
