package de.dikodam.adventofcode.day06;

import de.dikodam.adventofcode.tools.Tuple;

public class Coordinates extends Tuple<Integer, Integer> {
    public Coordinates(Integer integer, Integer integer2) {
        super(integer, integer2);
    }

    public Integer getX() {
        return getA();
    }

    public Integer getY() {
        return getB();
    }
}
