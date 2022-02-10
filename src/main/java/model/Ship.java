package model;

import model.Coordinates;

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

    public boolean isSunk() {
        for (model.Coordinates coordinate : coordinates) {
            if (coordinate.getStatus().equals(model.Status.NOT_HIT)) {
                return false;
            }
        }
        this.setSunk(true);
        return true;
    }

    public boolean isHit(int x, int y) {
        return coordinates.stream().anyMatch(c -> c.getX() == x &&
                c.getY() == y &&
                c.getStatus() == Status.NOT_HIT);
    }

    public List<Coordinates> getCoordinates() {
        return this.coordinates;
    }

    public void addCoordinate(Coordinates coordinate) {
        this.coordinates.add(coordinate);
    }

    public boolean isHorizontal() {
        return this.horizontal;
    }

    public void setSunk(boolean sunk) {
        this.isSunk = sunk;
    }

    public String getName() {
        return name;
    }

    public int getMaxEndingIndex(int endIndex, int length) {
        return endIndex + length - 1;
    }
}
