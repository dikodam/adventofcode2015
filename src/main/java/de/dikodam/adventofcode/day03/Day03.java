package de.dikodam.adventofcode.day03;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.Iterator;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Day03 extends AbstractDay {
    public static void main(String[] args) {
        new Day03().performTasks();
    }

    @Override
    public void task1() {
        Iterator<Movement> movements = getMovements().iterator();
        long visitedPositionsCount = Stream.iterate(new Position(0, 0), previousPosition -> movements.next().apply(previousPosition))
                .limit(getMovements().size())
                .distinct()
                .count();

        System.out.println("T1: visited positions: " + visitedPositionsCount);
    }

    private List<Movement> getMovements() {
        return streamFirstInputLine()
                .map(this::inputToMovement)
                .collect(toList());
    }

    private Movement inputToMovement(String input) {
        switch (input) {
            case "^":
                return (pos) -> new Position(pos.getA(), pos.getB() + 1);
            case "<":
                return (pos) -> new Position(pos.getA() - 1, pos.getB());
            case ">":
                return (pos) -> new Position(pos.getA() + 1, pos.getB());
            case "v":
                return (pos) -> new Position(pos.getA(), pos.getB() - 1);
            default:
                throw new IllegalArgumentException(input);
        }
    }

    @Override
    public void task2() {
        // santa starts
        // next movement is for robo santa
        // next movement is for santa
        // even movements are for santa
        // uneven movements are for robo santa

        List<Movement> movements = getMovements();

        Stream<Position> santaPositions = extractPositions(movements, i -> i % 2 == 0);
        Stream<Position> roboSantaPositions = extractPositions(movements, i -> i % 2 != 0);

        long distinctPositionCount = Stream.concat(santaPositions, roboSantaPositions)
                .distinct()
                .count();

        System.out.println("T2: positions visited: " + distinctPositionCount);
    }

    private Stream<Position> extractPositions(List<Movement> movements, IntPredicate moveCondition) {
        List<Movement> actualMovementsList = IntStream
                .range(0, movements.size())
                .filter(moveCondition)
                .mapToObj(movements::get)
                .collect(toList());

        Iterator<Movement> moves = actualMovementsList.iterator();

        return Stream.iterate(new Position(0, 0), previousPosition -> moves.next().apply(previousPosition))
                .limit(actualMovementsList.size())
                .distinct();
    }
}
