package io.github.maciejwojcik913.MoviesWebApp.Mapper;

import io.github.maciejwojcik913.MoviesWebApp.DTO.GenreTo;
import io.github.maciejwojcik913.MoviesWebApp.Domain.GenreEntity;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectEntityException;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectTransferObjectException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GenreMapper {

    public static GenreTo map2To(GenreEntity entity) {
        if (entity == null)
            throw new IncorrectEntityException("Provided entity is null.");
        return new GenreTo(entity.getName());
    }

    public static GenreEntity map2Entity(GenreTo country) {
        if (country == null)
            throw new IncorrectTransferObjectException("Provided transfer object is null.");
        return new GenreEntity(country.getName());
    }

    public static List<GenreTo> map2Tos(Collection<GenreEntity> entities) {
        return entities.stream()
                .map(GenreMapper::map2To)
                .collect(Collectors.toList());
    }

    public static List<GenreEntity> map2Entities(Collection<GenreTo> countryTos) {
        return countryTos.stream()
                .map(GenreMapper::map2Entity)
                .collect(Collectors.toList());
    }
}
