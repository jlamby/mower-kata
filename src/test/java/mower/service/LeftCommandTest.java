package mower.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mower.model.Lawn;
import mower.model.Mower;
import mower.model.Orientation;
import mower.model.Position;

class LeftCommandTest {

    @InjectMocks
    private LeftCommand cut;

    @Mock
    private Lawn lawn;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_not_move_position() {
        Mower mower = new Mower(new Position(1, 2, Orientation.SOUTH));

        cut.move(mower, lawn);

        Position position = mower.getPosition();

        assertThat(position.getX()).isEqualTo(1);
        assertThat(position.getY()).isEqualTo(2);
    }

    @Test
    void should_head_west_when_turning_left_from_the_north() {
        Mower mower = new Mower(new Position(1, 2, Orientation.NORTH));

        cut.move(mower, lawn);

        assertThat(mower.getPosition().getOrientation()).isEqualTo(Orientation.WEST);
    }

    @Test
    void should_head_south_when_turning_left_from_the_west() {
        Mower mower = new Mower(new Position(1, 2, Orientation.WEST));

        cut.move(mower, lawn);

        assertThat(mower.getPosition().getOrientation()).isEqualTo(Orientation.SOUTH);
    }

    @Test
    void should_head_east_when_turning_left_from_the_south() {
        Mower mower = new Mower(new Position(1, 2, Orientation.SOUTH));

        cut.move(mower, lawn);

        assertThat(mower.getPosition().getOrientation()).isEqualTo(Orientation.EAST);
    }

    @Test
    void should_head_north_when_turning_left_from_the_east() {
        Mower mower = new Mower(new Position(1, 2, Orientation.EAST));

        cut.move(mower, lawn);

        assertThat(mower.getPosition().getOrientation()).isEqualTo(Orientation.NORTH);
    }

}
