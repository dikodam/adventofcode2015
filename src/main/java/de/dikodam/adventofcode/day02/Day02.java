package de.dikodam.adventofcode.day02;

import de.dikodam.adventofcode.tools.AbstractDay;
import de.dikodam.adventofcode.tools.Triple;

import static java.lang.Integer.parseInt;
import static java.lang.Math.min;

public class Day02 extends AbstractDay {

    public static void main(String[] args) {
        new Day02().performTasks();
    }

    // format: lxwxh
    // surface: 2*l*w + 2*w*h + 2*h*l
    // additional: smallest area

    @Override
    public void task1() {
        long area = getInputLines()
                .stream()
                .map(this::parseDimensions)
                .map(this::dimensionsToAreas)
                .mapToInt(this::computeWholeArea)
                .sum();
        System.out.println("area needed: " + area);
    }

    private int computeWholeArea(Triple<Integer, Integer, Integer> areaTriple) {
        final int a = areaTriple.getA();
        final int b = areaTriple.getB();
        final int c = areaTriple.getC();
        final int minArea = min(a, min(b, c));
        return minArea + 2 * (a + b + c);
    }

    private Triple<Integer, Integer, Integer> parseDimensions(String line) {
        String[] dimensions = line.split("x");
        return new Triple<>(parseInt(dimensions[0]), parseInt(dimensions[1]), parseInt(dimensions[2]));
    }

    private Triple<Integer, Integer, Integer> dimensionsToAreas(Triple<Integer, Integer, Integer> dimensions) {
        int length = dimensions.getA();
        int width = dimensions.getB();
        int height = dimensions.getC();

        final int area1 = length * width;
        final int area2 = width * height;
        final int area3 = height * length;

        return new Triple<>(area1, area2, area3);
    }

    @Override
    public void task2() {
        int ribbonLength = getInputLines()
                .stream()
                .map(this::parseDimensions)
                .mapToInt(this::computeRibbonLength)
                .sum();

        System.out.println("Ribbon length: " + ribbonLength);
    }

    private int computeRibbonLength(Triple<Integer, Integer, Integer> dimensions) {
        return shortesDistanceAroundSides(dimensions) + volume(dimensions);
    }

    private int shortesDistanceAroundSides(Triple<Integer, Integer, Integer> dimensions) {
        int length = dimensions.getA();
        int width = dimensions.getB();
        int height = dimensions.getC();

        int d1 = 2 * (length + width);
        int d2 = 2 * (length + height);
        int d3 = 2 * (width + height);

        return min(d1, min(d2, d3));
    }

    private int volume(Triple<Integer, Integer, Integer> triple) {
        return triple.getA() * triple.getB() * triple.getC();
    }
}
