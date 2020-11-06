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
        int sum = Arrays.stream(input.split(""))
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
                throw new IllegalArgumentException(directionChar);
        }
    }

    @Override
    public void task2() {
        String[] input = getInputLines().get(0).split("");
        int level = 0;
        int firstBasementCharIndex = 0;

        for (String s : input) {
            level += parseInputCharToFloorChange(s);
            firstBasementCharIndex++;
            if (level == -1) {
                break;
            }
        }

        System.out.println("Task 2: Char position of first basement is " + firstBasementCharIndex);
    }
}
