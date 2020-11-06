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

    public boolean[][] execute(boolean[][] grid) {
        for (int x = begin.getX(); x <= end.getX(); x++) {
            for (int y = begin.getY(); y <= end.getY(); y++) {
                grid[x][y] = changeLight(grid[x][y]);
            }
        }
        return grid;
    }

    boolean changeLight(boolean light) {
        switch (operation) {
            case TOGGLE:
                return !light;
            case TURN_ON:
                return true;
            case TURN_OFF:
                return false;
        }
        throw new IllegalStateException("this should never happen. operation is " + operation);
    }

}
