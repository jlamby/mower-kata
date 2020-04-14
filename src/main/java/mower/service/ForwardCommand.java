package mower.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mower.model.Lawn;
import mower.model.Mower;
import mower.model.Position;

public class ForwardCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForwardCommand.class);

    @Override
    public void move(Mower mower, Lawn lawn) {
        Position futurePosition = Position.moveAheadFrom(mower.getPosition());

        String invalidMoveCausedBy = null;

        if (futurePosition.getX() < 0 || futurePosition.getY() < 0) {
            invalidMoveCausedBy = "lower boundary";
        }

        if (futurePosition.getX() > lawn.getWidth() || futurePosition.getY() > lawn.getHeight()) {
            invalidMoveCausedBy = "upper boundary";
        }

        for (Mower m : lawn.getMowers()) {
            if (futurePosition.getX() == m.getPosition().getX() && futurePosition.getY() == m.getPosition().getY()) {
                invalidMoveCausedBy = "position already occupied";
            }
        }

        if (invalidMoveCausedBy != null) {
            LOGGER.warn("Invalid move detected : {}", invalidMoveCausedBy);
            return;
        }

        mower.moveAhead();
    }

}
