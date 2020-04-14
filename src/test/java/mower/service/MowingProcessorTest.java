package mower.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import mower.model.CommandType;
import mower.model.Lawn;
import mower.model.Mower;
import mower.model.Orientation;
import mower.model.Position;

class MowingProcessorTest {

    private MowingProcessor cut = new MowingProcessor();

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void isRunning_should_return_true_when_at_least_one_mower_keeps_working() {
        Lawn lawn = fakeLawn();

        cut.start(lawn);

        assertThat(cut.isRunning()).isTrue();
    }

    @Test
    void isRunning_should_return_false_when_all_mowers_are_done() throws Exception {
        Lawn lawn = fakeLawn();

        cut.start(lawn);

        Thread.sleep(50);

        assertThat(cut.isRunning()).isFalse();
    }

    private Lawn fakeLawn() {
        Mower mower = new Mower(new Position(1, 1, Orientation.EAST));
        mower.setCommands(Arrays.asList(CommandType.LEFT));

        Lawn lawn = new Lawn(2, 2);
        lawn.addMower(mower);

        return lawn;
    }
}
