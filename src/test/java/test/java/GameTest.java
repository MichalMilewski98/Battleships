package test.java;

import game.Game;
import model.Coordinates;
import model.Ship;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {
    private static final int SIZE = 10;
    private static final char[][] map = new char[SIZE][SIZE];
    private static final boolean HORIZONTAL = true;
    private static final boolean VERTICAL = false;
    private static final int DESTROYER_LENGTH = 5;
    private static final int BATTLESHIP_LENGTH = 4;
    private Game game;

    public GameTest() {
        game = new Game();
        //game.initializeGame();
    }

    @Test
    public void shouldShipBeMarkedAsSunk() {
        game.createMap();
        Ship ship = new Ship("Destroyer", DESTROYER_LENGTH, HORIZONTAL);
        game.markShipAsSunkOnTheBoard(ship);
        for (Coordinates coordinate : ship.getCoordinates()) {
            Assert.assertEquals('S', map[coordinate.getX()][coordinate.getY()]);
        }
    }

    @Test
    public void shouldGameKeepRunning() {
        Ship ship = new Ship("Destroyer", DESTROYER_LENGTH, HORIZONTAL);
        //game.addShip(ship);
        Assert.assertFalse(game.isGameWon());
    }

    @Test
    public void shouldGameEndWhenAllShipsAreSunk() {
        Ship ship = new Ship("Destroyer", DESTROYER_LENGTH, VERTICAL);
        ship.setSunk(true);
        Assert.assertTrue(game.isGameWon());
    }

}
