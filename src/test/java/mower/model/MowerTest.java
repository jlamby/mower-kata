package mower.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MowerTest {

    @Test
    void moveAhead_should_move_mower_one_unit_given_an_orientation() {
        Mower mower = new Mower(new Position(1, 3, Orientation.SOUTH));

        mower.moveAhead();

        Position position = mower.getPosition();

        assertThat(position.getX()).isEqualTo(1);
        assertThat(position.getY()).isEqualTo(2);
    }

}
