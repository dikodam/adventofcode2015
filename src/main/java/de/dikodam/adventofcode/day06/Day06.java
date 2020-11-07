package de.dikodam.adventofcode.day06;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.List;
import java.util.function.Function;

import static de.dikodam.adventofcode.day06.Command.Operation.*;
import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;

// operating on a 1000x1000 boolean[][]
// process commands in order (turn on, turn off, toggle grids)
public class Day06 extends AbstractDay {

    String ex1 = "turn on 0,0 through 999,999";
    String ex2 = "toggle 0,0 through 999,0";
    String ex3 = "toggle 0,0 through 999,0";

    public static void main(String[] args) {
        new Day06().performTasks();
    }

    // this seems wrong...
    static Boolean[][] initializeBooleanGrid() {
        Boolean[][] grid = new Boolean[1000][1000];
        for (int x = 0; x < 1000; x++) {
            Boolean[] row = new Boolean[1000];
            for (int y = 0; y < 1000; y++) {
                row[y] = Boolean.FALSE;
            }
            grid[x] = row;
        }
        return grid;
    }

    // this seems wrong...
    static Integer[][] initializeIntegerGrid() {
        Integer[][] grid = new Integer[1000][1000];
        for (int x = 0; x < 1000; x++) {
            Integer[] row = new Integer[1000];
            for (int y = 0; y < 1000; y++) {
                row[y] = 0;
            }
            grid[x] = row;
        }
        return grid;
    }

    private <T> int sumGridUsing(T[][] grid, Function<T, Integer> toInt) {
        int counter = 0;
        for (T[] row : grid) {
            for (T light : row) {
                counter += toInt.apply(light);
            }
        }
        return counter;
    }

    Command parseCommand(String inputLine) {
        if (inputLine.startsWith("turn on")) {
            return buildCommand(inputLine.substring(8), TURN_ON);
        }
        if (inputLine.startsWith("turn off")) {
            return buildCommand(inputLine.substring(9), TURN_OFF);
        }
        if (inputLine.startsWith("toggle")) {
            return buildCommand(inputLine.substring(7), TOGGLE);
        }
        throw new IllegalArgumentException("inputLine " + inputLine + " is invalid");
    }

    Command buildCommand(String commandLine, Command.Operation operation) {
        String[] coordinates = commandLine.split(" through ");
        Coordinates begin = parseCoordinates(coordinates[0]);
        Coordinates end = parseCoordinates(coordinates[1]);
        return new Command(begin, end, operation);
    }

    Coordinates parseCoordinates(String coordinate) {
        String[] xAndY = coordinate.split(",");
        int x = parseInt(xAndY[0]);
        int y = parseInt(xAndY[1]);
        return new Coordinates(x, y);
    }

    @Override
    public void task1() {

        InterpretationStrategy<Boolean> switchingLights = (light, operation) -> {
            switch (operation) {
                case TOGGLE:
                    return !light;
                case TURN_ON:
                    return true;
                case TURN_OFF:
                    return false;
                default:
                    throw new IllegalStateException("this should never happen. operation is " + operation);
            }
        };

        // how many lights are lit after processing all commands?
        // all lights start turned off
        Boolean[][] grid = initializeBooleanGrid();

        List<Command> commands = streamInputLines()
                .map(this::parseCommand)
                .collect(toList());

        // in FP this would be a  fold
        for (Command command : commands) {
            grid = command.execute(grid, switchingLights);
        }

        Function<Boolean, Integer> booleanToInt = (b) -> b ? 1 : 0;
        System.out.println("T1: " + sumGridUsing(grid, booleanToInt) + " lights are lit.");
    }

    @Override
    public void task2() {

        InterpretationStrategy<Integer> changingBrigthness = (light, operation) -> {
            switch (operation) {
                case TURN_ON:
                    return light + 1;
                case TURN_OFF:
                    return max(light - 1, 0);
                case TOGGLE:
                    return light + 2;
                default:
                    throw new IllegalStateException("this should never happen. operation is " + operation);
            }
        };

        Integer[][] grid = initializeIntegerGrid();

        List<Command> commands = streamInputLines()
                .map(this::parseCommand)
                .collect(toList());

        for (Command command : commands) {
            grid = command.execute(grid, changingBrigthness);
        }
        System.out.println("T2: Total brightness is " + sumGridUsing(grid, identity()));
    }
}
