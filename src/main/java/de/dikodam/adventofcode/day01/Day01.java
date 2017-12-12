package de.dikodam.adventofcode.day01;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.Arrays;

public class Day01 extends AbstractDay {

    public static void main(String[] args) {
        Day01 day01 = new Day01();
        day01.task1();
        day01.task2();
    }

    @Override
    public void task1() {
        String input = getInput(getClass().getSimpleName()).get(0);
        int sum = Arrays.stream(input.split(""))
            .mapToInt(this::parseInputCharToFloorChange)
            .sum();

        System.out.println("Task 1: final floor level is: " + sum);
    }

    private int parseInputCharToFloorChange(String directionChar) {
        switch (directionChar) {
            case ")":
                return -1;
            case "(":
                return 1;
            default:
                throw new IllegalArgumentException(directionChar);
        }
    }

    @Override
    public void task2() {

    }
}
