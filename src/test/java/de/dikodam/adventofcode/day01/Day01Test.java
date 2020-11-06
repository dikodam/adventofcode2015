package de.dikodam.adventofcode.day01;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class Day01Test {

    Day01 dayUnderTest = new Day01();

    @Test
    void parseInputCharToFloorChange() {
        assertAll(
                () -> assertThat(dayUnderTest.parseInputCharToFloorChange(")")).isEqualTo(-1),
                () -> assertThat(dayUnderTest.parseInputCharToFloorChange("(")).isEqualTo(1),
                () -> assertThatThrownBy(() -> dayUnderTest.parseInputCharToFloorChange("blep"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
}