package mower.model;

public class Position {

    private int x;
    private int y;
    private Orientation orientation;

    public Position(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public static Position moveAheadFrom(Position pos) {
        Position position = new Position(pos.x, pos.y, pos.orientation);
        position.moveAhead();

        return position;
    }

    public static Position from(Position pos, Orientation newOrientation) {
        return new Position(pos.x, pos.y, newOrientation);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    private void moveAhead() {
        int newX = x + orientation.getDeltaX();
        int newY = y + orientation.getDeltaY();

        x = newX;
        y = newY;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation;
    }

}
