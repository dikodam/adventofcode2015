package de.dikodam.adventofcode.day01;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.Arrays;

public class Day01 extends AbstractDay {

    public static void main(String[] args) {
        new Day01().performTasks();
    }

    @Override
    public void task1() {
        String input = getInputLines().get(0);
        int sum = streamFirstInputLine()
                .mapToInt(this::parseInputCharToFloorChange)
                .sum();

        System.out.println("Task 1: final floor level is: " + sum);
    }

    int parseInputCharToFloorChange(String directionChar) {
        switch (directionChar) {
            case ")":
                return -1;
            case "(":
                return 1;
            default:
                // could be return 0, but I want to know if something goes wrong in the input file
                throw new IllegalArgumentException(directionChar);
        }
    }

    @Override
    public void task2() {
        String[] input = getInputLines().get(0).split("");
        int level = 0;
        int counter = 0;
        while (level >= 0) {
            level += parseInputCharToFloorChange(input[counter]);
            counter++;
        }

        System.out.println("Task 2: Char position of first basement is " + counter);
    }
}
