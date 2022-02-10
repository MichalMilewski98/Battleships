package model;
import java.util.ArrayList;
import java.util.List;

import static game.Game.shipTypes;

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
        if (coordinates.size() < 1) return false;
        for (Coordinates coordinate : coordinates) {
            if (coordinate.getStatus().equals(Status.NOT_HIT)) {
                return false;
            }
        }
        this.setSunk(true);
        return true;
    }

    public boolean checkBoundaries(int startingIndex) {
        int endingIndex = getMaxEndingIndex(startingIndex, shipTypes.get(getName()));
        if (!(startingIndex <= endingIndex && endingIndex < 10)) {
            return false;
        } else {
            addCoordinatesToShip(startingIndex, endingIndex);
            return true;
        }
    }

    private void addCoordinatesToShip(int startingIndex, int secondIndex) {
        for (int i = secondIndex; i >= startingIndex; i--) {
            if (this.isHorizontal()) {
                getCoordinates().add(new Coordinates(i, secondIndex));
            } else {
                getCoordinates().add(new Coordinates(secondIndex, i));
            }
        }
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

