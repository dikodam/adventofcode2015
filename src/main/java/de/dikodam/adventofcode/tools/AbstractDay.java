package de.dikodam.adventofcode.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public abstract class AbstractDay {

    public abstract void task1();

    public abstract void task2();

    private List<String> inputLines;

    public List<String> getInputLines() {
        if (inputLines == null) {
            inputLines = readInput();
        }
        return inputLines;
    }

    private List<String> readInput() {
        String fileName = getClass().getSimpleName();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(getClass().getResource("/" + fileName).toURI())))) {
            return br.lines().collect(toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    protected void performTasks() {
        task1();
        task2();
    }

}
