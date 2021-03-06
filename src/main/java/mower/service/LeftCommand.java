package mower.service;

import mower.model.Lawn;
import mower.model.Mower;
import mower.model.Orientation;
import mower.model.Position;

public class LeftCommand implements Command {

    @Override
    public void move(Mower mower, Lawn lawn) {
        Orientation orientation = mower.getPosition().getOrientation();

        switch (orientation) {
            case NORTH:
                orientation = Orientation.WEST;
                break;
            case WEST:
                orientation = Orientation.SOUTH;
                break;
            case SOUTH:
                orientation = Orientation.EAST;
                break;
            case EAST:
                orientation = Orientation.NORTH;
                break;
        }

        mower.setPosition(Position.from(mower.getPosition(), orientation));
    }

}
