package mower.model;

public enum Orientation {

    NORTH(0, 1),
    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1);

    private final int deltaX;
    private final int deltaY;

    private Orientation(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public static Orientation fromValue(String value) {
        switch (value) {
            case "N":
                return NORTH;
            case "S":
                return SOUTH;
            case "W":
                return WEST;
            case "E":
                return EAST;
        }

        return null;
    }

}
