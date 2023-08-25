package org.example.entity.map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class GameMap {
    @Setter
    @Getter
    private static GameMap instance;

    private int width;
    private int height;
    @Setter
    private Cell[][] cells;
}
