package io.github.maciejwojcik913.MoviesWebApp.DTO;

import java.util.Objects;

public class GenreTo {

    private String name;

    public GenreTo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreTo genreTO = (GenreTo) o;
        return Objects.equals(name, genreTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
