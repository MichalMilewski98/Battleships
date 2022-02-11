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
    private Game game;

    public GameTest() {
        game = new Game();
    }

    @Test
    public void shouldThreeShipsBeInitializedWhenTheGameStarts() {
        game.initializeGame();
        Assert.assertEquals(game.getShips().size(), 3);
    }

    @Test
    public void shouldShipBeMarkedAsSunk() {
        game.createMap();
        Ship ship = new Ship("Destroyer", HORIZONTAL);
        game.markShipAsSunkOnTheBoard(ship);
        for (Coordinates coordinate : ship.getCoordinates()) {
            Assert.assertEquals('S', map[coordinate.getX()][coordinate.getY()]);
        }
    }

    @Test
    public void shouldGameKeepRunningWhenShipsAreNotSunk() {
        Ship ship = new Ship("Destroyer", HORIZONTAL);
        game.getShips().add(ship);
        Assert.assertFalse(game.isGameWon());
    }

    @Test
    public void shouldGameEndWhenAllShipsAreSunk() {
        Ship ship = new Ship("Destroyer", VERTICAL);
        ship.setSunk(true);
        Assert.assertTrue(game.isGameWon());
    }

    @Test
    public void shouldShipBeMarkedHit() {
        game.createMap();
        game.getShips().add(new Ship("Destroyer", VERTICAL));
        Ship ship = game.getShips().get(0);
        ship.addCoordinate(new Coordinates(1, 1));
        ship.addCoordinate(new Coordinates(1, 12));
        game.shot(1, 1);
        Assert.assertSame('H', game.getMap()[1][1]);
    }
}
