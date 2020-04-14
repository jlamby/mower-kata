package mower.model;

import java.util.List;

public class Mower {

    private static int IDX = 0;

    private int id;
    private Position position;
    private List<CommandType> commands;

    public Mower(Position position) {
        this.position = position;
        this.id = IDX++;
    }

    public void moveAhead() {
        position = Position.moveAheadFrom(position);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<CommandType> getCommands() {
        return commands;
    }

    public void setCommands(List<CommandType> commands) {
        this.commands = commands;
    }

    @Override
    public String toString() {
        return "Mower#" + id + " [position=" + position + "]";
    }

}
