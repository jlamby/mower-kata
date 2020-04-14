package mower.service;

import mower.model.Lawn;
import mower.model.Mower;

public interface Command {

    void move(Mower mower, Lawn lawn);

}
