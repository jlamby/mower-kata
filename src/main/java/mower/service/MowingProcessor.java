package mower.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mower.model.CommandType;
import mower.model.Lawn;
import mower.model.Mower;

public class MowingProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MowingProcessor.class);

    private List<Thread> threads = new ArrayList<>();

    public void start(Lawn lawn) {
        for (Mower m : lawn.getMowers()) {
            threads.add(new Thread(newMowingTask(m, lawn)));
        }

        startThreads();
    }

    private void startThreads() {
        threads.forEach(Thread::start);
    }

    public boolean isRunning() {
        cleanDeadThreads();

        return !threads.isEmpty();
    }

    private void cleanDeadThreads() {
        threads.removeIf(t -> !t.isAlive());
    }

    private Runnable newMowingTask(Mower m, Lawn lawn) {
        return () -> {
            for (CommandType c : m.getCommands()) {
                c.getHandler().move(m, lawn);

                LOGGER.debug("New position of {}", m);
            }
        };
    }
}
