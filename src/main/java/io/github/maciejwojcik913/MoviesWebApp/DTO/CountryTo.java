package io.github.maciejwojcik913.MoviesWebApp.DTO;

import java.util.Objects;

public class CountryTo {

    private String name;

    public CountryTo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryTo countryTO = (CountryTo) o;
        return Objects.equals(name, countryTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
