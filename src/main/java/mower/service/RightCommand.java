package mower.service;

import mower.model.Lawn;
import mower.model.Mower;
import mower.model.Orientation;
import mower.model.Position;

public class RightCommand implements Command {

    @Override
    public void move(Mower mower, Lawn lawn) {
        Orientation orientation = mower.getPosition().getOrientation();

        switch (orientation) {
            case NORTH:
                orientation = Orientation.EAST;
                break;
            case WEST:
                orientation = Orientation.NORTH;
                break;
            case SOUTH:
                orientation = Orientation.WEST;
                break;
            case EAST:
                orientation = Orientation.SOUTH;
                break;
        }

        mower.setPosition(Position.from(mower.getPosition(), orientation));
    }

}
