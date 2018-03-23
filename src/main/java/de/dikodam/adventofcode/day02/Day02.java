package de.dikodam.adventofcode.day02;

import de.dikodam.adventofcode.tools.AbstractDay;
import de.dikodam.adventofcode.tools.Triple;

public class Day02 extends AbstractDay {

    public static void main(String[] args) {
        doTheMagic(Day02.class);
    }

    // format: lxwxh
    // surface: 2*l*w + 2*w*h + 2*h*l
    // additional: smallest area

    @Override
    public void task1() {
        long area = getInput()
            .stream()
            .map(this::parseAreas)
            .mapToInt(this::computeWholeArea)
            .sum();
        System.out.println("area needed: " + area);
    }

    private int computeWholeArea(Triple<Integer, Integer, Integer> areaTriple) {
        final int a = areaTriple.getA();
        final int b = areaTriple.getB();
        final int c = areaTriple.getC();
        final int minArea = Math.min(a, Math.min(b, c));
        return minArea + 2 * (a + b + c);
    }

    private Triple<Integer, Integer, Integer> parseAreas(String line) {
        String[] dimensions = line.split("x");

        final int length = Integer.parseInt(dimensions[0]);
        final int width = Integer.parseInt(dimensions[1]);
        final int height = Integer.parseInt(dimensions[2]);

        final int area1 = length * width;
        final int area2 = width * height;
        final int area3 = height * length;

        return new Triple<>(area1, area2, area3);
    }

    private int square(int i) {
        return i * i;
    }

    @Override
    public void task2() {

    }
}
