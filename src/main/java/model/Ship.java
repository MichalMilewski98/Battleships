package model;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private int length;
    private boolean horizontal;
    private boolean isSunk;
    private String name;
    private List<Coordinates> coordinates;

    public Ship(String name, int length, boolean horizontal) {
        this.name = name;
        this.length = length;
        this.horizontal = horizontal;
        this.isSunk = false;
        this.coordinates = new ArrayList<>();
    }
}
