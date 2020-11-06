package de.dikodam.adventofcode.day03;

import de.dikodam.adventofcode.tools.AbstractDay;
import de.dikodam.adventofcode.tools.Tuple;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
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
        List<Movement> movements = getMovements();
        Iterator<Movement> movementsIterator = movements.iterator();
        long visitedPositionsCount = Stream
                .iterate(new Position(0, 0), previousTuple -> movementsIterator.next().apply(previousTuple))
                .limit(movements.size())
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
                return (position) -> new Position(position.getA(), position.getB() + 1);
            case "<":
                return (position) -> new Position(position.getA() - 1, position.getB());
            case ">":
                return (position) -> new Position(position.getA() + 1, position.getB());
            case "v":
                return (position) -> new Position(position.getA(), position.getB() - 1);
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

        Stream<Position> santaPositionsStream = buildTupleStream(movements, i -> i % 2 == 0);
        Stream<Position> roboSantaPositionsStream = buildTupleStream(movements, i -> i % 2 != 0);

        long positionCount = Stream.concat(santaPositionsStream, roboSantaPositionsStream)
                .distinct()
                .count();

        System.out.println("T2: positions visited: " + positionCount);
    }

    private Stream<Position> buildTupleStream(List<Movement> movements, IntPredicate movementFilter) {
        List<Movement> filteredMovements = IntStream
                .range(0, movements.size())
                .filter(movementFilter)
                .mapToObj(movements::get)
                .collect(toList());

        Iterator<Movement> iterator = filteredMovements.iterator();

        return Stream.iterate(new Position(0, 0), previousTuple -> iterator.next().apply(previousTuple))
                .limit(filteredMovements.size())
                .distinct();
    }
}
