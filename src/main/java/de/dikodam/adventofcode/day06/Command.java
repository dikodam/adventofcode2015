package de.dikodam.adventofcode.day06;

public class Command {

    enum Operation {
        TURN_ON,
        TURN_OFF,
        TOGGLE
    }

    private final Coordinates begin;
    private final Coordinates end;
    private final Operation operation;

    public Command(Coordinates begin, Coordinates end, Operation operation) {
        this.begin = begin;
        this.end = end;
        this.operation = operation;
    }

    public <T> T[][] execute(T[][] grid, InterpretationStrategy<T> commandInterpretationStrategy) {
        for (int x = begin.getX(); x <= end.getX(); x++) {
            for (int y = begin.getY(); y <= end.getY(); y++) {
                grid[x][y] = commandInterpretationStrategy.apply(grid[x][y], operation);
            }
        }
        return grid;
    }

}
