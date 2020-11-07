package de.dikodam.adventofcode.day03;


import java.util.function.UnaryOperator;

@FunctionalInterface
public interface Movement extends UnaryOperator<Position> {
}
