package mower.service;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mower.model.Lawn;
import mower.model.Mower;
import mower.model.Orientation;
import mower.model.Position;

class ForwardCommandTest {

    @InjectMocks
    private ForwardCommand cut;

    @Mock
    private Mower mower;

    @Mock
    private Lawn lawn;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);

        when(mower.getPosition()).thenReturn(new Position(1, 2, Orientation.EAST));
        when(lawn.getHeight()).thenReturn(2);
        when(lawn.getWidth()).thenReturn(2);
    }

    @Test
    void should_call_moveAhead_when_moving_is_allowed() {
        cut.move(mower, lawn);

        verify(mower).moveAhead();
    }

    @Test
    void should_not_call_moveAhead_when_moving_is_not_allowed_because_of_x_lower_boundary() {
        when(mower.getPosition()).thenReturn(new Position(0, 2, Orientation.WEST));

        cut.move(mower, lawn);

        verify(mower, never()).moveAhead();
    }

    @Test
    void should_not_call_moveAhead_when_moving_is_not_allowed_because_of_y_lower_boundary() {
        when(mower.getPosition()).thenReturn(new Position(1, 0, Orientation.SOUTH));

        cut.move(mower, lawn);

        verify(mower, never()).moveAhead();
    }

    @Test
    void should_not_call_moveAhead_when_moving_is_not_allowed_because_of_x_upper_boundary() {
        when(mower.getPosition()).thenReturn(new Position(2, 2, Orientation.EAST));

        cut.move(mower, lawn);

        verify(mower, never()).moveAhead();
    }

    @Test
    void should_not_call_moveAhead_when_moving_is_not_allowed_because_of_y_upper_boundary() {
        when(mower.getPosition()).thenReturn(new Position(1, 2, Orientation.NORTH));

        cut.move(mower, lawn);

        verify(mower, never()).moveAhead();
    }

    @Test
    void should_not_call_moveAhead_when_moving_is_not_allowed_because_of_an_another_mower_on_postion() {
        Mower secondMower = new Mower(new Position(1, 1, Orientation.NORTH));

        when(lawn.getMowers()).thenReturn(Arrays.asList(secondMower));

        when(mower.getPosition()).thenReturn(new Position(1, 2, Orientation.SOUTH));

        cut.move(mower, lawn);

        verify(mower, never()).moveAhead();
    }

}
