package de.dikodam.adventofcode.day06;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.List;

import static de.dikodam.adventofcode.day06.Command.Operation.*;
import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

// oprating on a 1000x1000 boolean[][]
// process commands in order (turn on, turn off, toggle grids)
public class Day06 extends AbstractDay {

    String ex1 = "turn on 0,0 through 999,999";
    String ex2 = "toggle 0,0 through 999,0";
    String ex3 = "toggle 0,0 through 999,0";

    public static void main(String[] args) {
        System.out.println("12345".substring(3));

        new Day06().performTasks();
    }

    @Override
    public void task1() {
        // how many lights are lit after processing all commands?
        // all lights start turned off
        boolean[][] grid = new boolean[1000][1000];

        List<Command> commands = streamInputLines()
                .map(this::parseCommand)
                .collect(toList());

        // TODO how would the functional solution look like? .reduce()? .collect()?
        for (Command command : commands) {
            grid = command.execute(grid);
        }

        System.out.println("T1: " + countLights(grid) + " lights are lit.");
    }

    private int countLights(boolean[][] grid) {
        int counter = 0;
        for (boolean[] lineOfLights : grid) {
            for (boolean lightIsLit : lineOfLights) {
                if (lightIsLit) {
                    counter++;
                }
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
    public void task2() {

    }
}
