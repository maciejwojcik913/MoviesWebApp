package io.github.maciejwojcik913.MoviesWebApp.Mapper;

import io.github.maciejwojcik913.MoviesWebApp.DTO.CountryTo;
import io.github.maciejwojcik913.MoviesWebApp.Domain.CountryEntity;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectEntityException;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectTransferObjectException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CountryMapper {

    public static CountryTo map2To(CountryEntity entity) {
        if (entity == null)
            throw new IncorrectEntityException("Provided entity is null.");
        return new CountryTo(entity.getName());
    }

    public static CountryEntity map2Entity(CountryTo country) {
        if (country == null)
            throw new IncorrectTransferObjectException("Provided transfer object is null.");
        return new CountryEntity(country.getName());
    }

    public static List<CountryTo> map2Tos(Collection<CountryEntity> entities) {
        return entities.stream()
                .map(CountryMapper::map2To)
                .collect(Collectors.toList());
    }

    public static List<CountryEntity> map2Entities(Collection<CountryTo> countryTos) {
        return countryTos.stream()
                .map(CountryMapper::map2Entity)
                .collect(Collectors.toList());
    }
}
