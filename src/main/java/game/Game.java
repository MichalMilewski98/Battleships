package game;

import model.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {

    private Input input;
    private boolean isRunning;
    private List<Ship> ships;
    private static final String BATTLESHIP = "Battleship";
    private static final String DESTROYER = "Destroyer";
    private static final int SIZE = 10;
    private static final char[][] map = new char[SIZE][SIZE];
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
        createMap();
    }

    public void start() {
        while (isRunning) {
            printMap();
            input.getInput(columns);
        }
        printMap();
        System.out.println("Congratulations, you won!");
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


}
