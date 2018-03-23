package de.dikodam.adventofcode.tools;

import java.util.Objects;

public class Tuple<A, B> {
    private final A a;
    private final B b;

    public Tuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> aDouble = (Tuple<?, ?>) o;
        return Objects.equals(a, aDouble.a) &&
            Objects.equals(b, aDouble.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "Tuple{" + "a=" + a + ", b=" + b + '}';
    }
}
