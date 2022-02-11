package game;

import model.Coordinates;
import model.Ship;
import model.Status;

import java.util.*;

public class Game {

    private Input input;
    private boolean isRunning;
    private List<Ship> ships;
    private static final String BATTLESHIP = "Battleship";
    private static final String DESTROYER = "Destroyer";
    private static final int SIZE = 10;
    private char[][] map = new char[SIZE][SIZE];
    public static final Map<String, Integer> shipTypes = Map.of(
            BATTLESHIP, 5,
            DESTROYER, 4
    );
    public static final List<Character> columns = List.of(new Character[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'
    });

    public Game() {
        this.input = new Input();
        this.isRunning = true;
        this.ships = new ArrayList<>();
    }

    public void initializeGame() {
        boolean intersect = true;
        createMap();
        initializeShips();
        while(intersect) {
            ships.forEach(c -> c.getCoordinates().clear());
            placeShips(ships);
            intersect = doesIntersect();
        }
    }

    public void start() {
        while (isRunning) {
            printMap();
            input.getInput(columns);
            shot(input.x, input.y);
            isRunning = !isGameWon();
        }
        printMap();
        System.out.println("Congratulations, you won!");
    }

    public void placeShips(List<Ship> ships) {
        boolean shipPlaced;
        for (Ship ship : ships) {
            shipPlaced = false;
            while (!shipPlaced) {
                Random rand = new Random();
                Coordinates startingIndex = new Coordinates(rand.nextInt(10), rand.nextInt(10));
                if (ship.isHorizontal()) {
                    shipPlaced = ship.checkBoundaries(startingIndex.getX());
                } else {
                    shipPlaced = ship.checkBoundaries(startingIndex.getY());
                }
            }
        }
    }

    public boolean doesIntersect() {
        List<Coordinates> allCoordinates = new ArrayList<>();
        Set<List<Integer>> duplicates = new HashSet<>();
        for (Ship ship : ships) {
            allCoordinates.addAll(ship.getCoordinates());
        }
        return allCoordinates.stream().anyMatch(p -> !duplicates.add(List.of(p.getX(), p.getY())));
    }

    public boolean isGameWon() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public void markCoordinateStatusAsHit(Ship ship, int x, int y) {
        for (Coordinates coordinate : ship.getCoordinates()) {
            if (coordinate.getX() == x && coordinate.getY() == y) {
                coordinate.setStatus(Status.HIT);
            }
        }
    }

    private void initializeShips() {
        Random rand = new Random();
        boolean horizontal;
        for (int i = 0; i < 2; i++) {
            horizontal = rand.nextBoolean();
            ships.add(new Ship(DESTROYER, horizontal));
        }
        horizontal = rand.nextBoolean();
        ships.add(new Ship(BATTLESHIP, horizontal));
    }

    public void markShipAsSunkOnTheBoard(Ship ship) {
        if(ship.isSunk()) {
            for (Coordinates coordinate : ship.getCoordinates()) {
                map[coordinate.getX()][coordinate.getY()] = 'S';
            }
        }
    }

    public void shot(int x, int y) {
        for (Ship ship : ships) {
            if (map[x][y] == 'H' || map[x][y] == 'S') {
                break;
            }
            if (ship.isHit(x, y)) {
                map[x][y] = 'H';
                markCoordinateStatusAsHit(ship, x, y);
                markShipAsSunkOnTheBoard(ship);
                break;
            } else {
                map[x][y] = 'M';
            }
        }
    }

    public void createMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = '-';
            }
        }
    }

    public void printMap() {
        System.out.print("  ");
        for(int i = 0; i < SIZE; i++) {
            System.out.print(columns.get(i) + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE ; j++) {
                if (j == 0) {
                    System.out.print(i + " ");
                }
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public List<Ship> getShips() {
        return this.ships;
    }

    public char[][] getMap() {
        return this.map;
    }
}
