package de.dikodam.adventofcode.day06;

import java.util.function.BiFunction;

@FunctionalInterface
public interface InterpretationStrategy<T> extends BiFunction<T, Command.Operation, T> {


}
