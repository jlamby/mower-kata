package mower.model;

import mower.service.Command;
import mower.service.ForwardCommand;
import mower.service.LeftCommand;
import mower.service.RightCommand;

public enum CommandType {

    LEFT(new LeftCommand()),
    RIGHT(new RightCommand()),
    FORWARD(new ForwardCommand());

    private CommandType(Command handler) {
        this.handler = handler;
    }

    private Command handler;

    public Command getHandler() {
        return handler;
    }

    public static CommandType retrieveByLetter(char letter) {
        switch (letter) {
            case 'L':
                return LEFT;
            case 'R':
                return RIGHT;
            case 'F':
                return FORWARD;
        }
        return null;
    }

}
