package io.github.maciejwojcik913.MoviesWebApp.Mapper;

import io.github.maciejwojcik913.MoviesWebApp.DTO.MovieTo;
import io.github.maciejwojcik913.MoviesWebApp.Domain.MovieEntity;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectEntityException;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectTransferObjectException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class responsible for mapping object between MovieEntity and MovieTo transfer object classes.
 * Uses PersonMapper, GenreMapper, CountryMapper.
 */
public class MovieMapper {

    /**
     * Maps MovieEntity object to transfer object.
     * @param movie MovieEntity object to be mapped.
     * @throws IncorrectEntityException if given entity is null.
     * @return MovieTo transfer object.
     */
    public static MovieTo map2To(MovieEntity movie) {
        if (movie == null) {
            throw new IncorrectEntityException("Provided entity is null.");
        } else if (movie.getTitle() == null || movie.getPremiereDate() == null) {
            throw new IncorrectEntityException("Provided entity contains null required fields: {title: " + movie.getTitle() + ", premiereDate: " + movie.getPremiereDate() + "}.");
        }
        return new MovieTo(
                movie.getTitle(),
                movie.getPremiereDate(),
                movie.getId(),
                movie.getDescription(),
                movie.getCoverUrl(),
                movie.getRating(),
                new HashSet<>(GenreMapper.map2Tos(movie.getGenres())),
                new HashSet<>(CountryMapper.map2Tos(movie.getProductionCountries())),
                new HashSet<>(PersonMapper.map2Tos(movie.getDirectors())),
                new HashSet<>(PersonMapper.map2Tos(movie.getScenarists())),
                new HashSet<>(PersonMapper.map2Tos(movie.getActors()))
        );
    }

    /**
     * Maps MovieTo transfer object to MovieEntity.
     * @param movie - MovieTo object to be mapped.
     * @throws IncorrectTransferObjectException if transfer object is null
     * @return MovieEntity object.
     */
    public static MovieEntity map2Entity(MovieTo movie) {
        if (movie == null) {
            throw new IncorrectTransferObjectException("Provided transfer object is null.");
        }
        var entity = new MovieEntity(movie.getTitle(), movie.getPremiereDate());
        entity.setId(movie.getId());
        entity.setDescription(movie.getDescription());
        entity.setCoverUrl(movie.getCoverUrl());
        entity.setRating(movie.getRating());
        entity.setGenres(Set.copyOf(GenreMapper.map2Entities(movie.getGenres())));
        entity.setProductionCountries(Set.copyOf(CountryMapper.map2Entities(movie.getProductionCountries())));
        entity.setDirectors(Set.copyOf(PersonMapper.map2Entities(movie.getDirectors())));
        entity.setScenarists(Set.copyOf(PersonMapper.map2Entities(movie.getScenarists())));
        entity.setActors(Set.copyOf(PersonMapper.map2Entities(movie.getActors())));
        return entity;
    }

    /**
     * Maps collection of MovieEntity to List of MovieTo transfer objects.<br>
     * More information by <code>map2To</code> method in this class.
     * @param entities collection of MovieEntity.
     * @return List of MovieTo
     * @throws IncorrectEntityException if provided collection contains any null value.
     */
    public static List<MovieTo> map2Tos(Collection<MovieEntity> entities) {
        return entities.stream()
                .map(MovieMapper::map2To)
                .collect(Collectors.toList());
    }

    /**
     * Maps collection of MovieTo transfer objects to List of MovieEntity entities.<br>
     * More information by <code>map2Entity</code> method in this class.
     * @param tos collection of MovieTo.
     * @return List of MovieEntity
     * @throws IncorrectTransferObjectException if provided collection contains any null value.
     */
    public static List<MovieEntity> map2Entities(Collection<MovieTo> tos) {
        return tos.stream()
                .map(MovieMapper::map2Entity)
                .collect(Collectors.toList());
    }
}
