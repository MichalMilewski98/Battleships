package model;

public class Coordinates {

    private int x;
    private int y;
    private Status status;


    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = Status.NOT_HIT;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
