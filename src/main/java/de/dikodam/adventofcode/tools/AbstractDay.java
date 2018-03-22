package de.dikodam.adventofcode.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public abstract class AbstractDay {

    public abstract void task1();

    public abstract void task2();

    public Stream<String> streamInput(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(
            new File(getClass().getResource("/" + fileName).toURI())))) {
            return br.lines();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Stream<String> streamInput() {
        return streamInput(getClass().getSimpleName());
    }

    public List<String> getInput(String fileName) {
        return streamInput(fileName).collect(toList());
    }

    public List<String> getInput() {
        return getInput(getClass().getSimpleName());
    }

}
