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
        List<Function<Tuple<Integer, Integer>, Tuple<Integer, Integer>>> movements = getMovements();
        Iterator<Function<Tuple<Integer, Integer>, Tuple<Integer, Integer>>> movementsIterator = movements.iterator();
        long visitedPositionsCount = Stream
                .iterate(new Tuple<>(0, 0), previousTuple -> movementsIterator.next().apply(previousTuple))
                .limit(movements.size())
                .distinct()
                .count();

        System.out.println("T1: visited positions: " + visitedPositionsCount);
    }

    private List<Function<Tuple<Integer, Integer>, Tuple<Integer, Integer>>> getMovements() {
        return Arrays.
                stream(getInputLines().get(0).split(""))
                .map(this::inputToMovement)
                .collect(toList());
    }

    private Function<Tuple<Integer, Integer>, Tuple<Integer, Integer>> inputToMovement(String input) {
        switch (input) {
            case "^":
                return (position) -> new Tuple<>(position.getA(), position.getB() + 1);
            case "<":
                return (position) -> new Tuple<>(position.getA() - 1, position.getB());
            case ">":
                return (position) -> new Tuple<>(position.getA() + 1, position.getB());
            case "v":
                return (position) -> new Tuple<>(position.getA(), position.getB() - 1);
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

        List<Function<Tuple<Integer, Integer>, Tuple<Integer, Integer>>> movements = getMovements();

        Stream<Tuple<Integer, Integer>> santaPositionsStream = buildTupleStream(movements, i -> i % 2 == 0);
        Stream<Tuple<Integer, Integer>> roboSantaPositionsStream = buildTupleStream(movements, i -> i % 2 != 0);

        long positionCount = Stream.concat(santaPositionsStream, roboSantaPositionsStream)
                .distinct()
                .count();

        System.out.println("T2: positions visited: " + positionCount);
    }

    private Stream<Tuple<Integer, Integer>> buildTupleStream(List<Function<Tuple<Integer, Integer>, Tuple<Integer, Integer>>> movements, IntPredicate movementFilter) {
        List<Function<Tuple<Integer, Integer>, Tuple<Integer, Integer>>> filteredMovements = IntStream
                .range(0, movements.size())
                .filter(movementFilter)
                .mapToObj(movements::get)
                .collect(toList());

        Iterator<Function<Tuple<Integer, Integer>, Tuple<Integer, Integer>>> iterator = filteredMovements.iterator();

        return Stream.iterate(new Tuple<>(0, 0), previousTuple -> iterator.next().apply(previousTuple))
                .limit(filteredMovements.size())
                .distinct();
    }
}
