package io.github.maciejwojcik913.MoviesWebApp.Mapper;

import io.github.maciejwojcik913.MoviesWebApp.DTO.GenreTo;
import io.github.maciejwojcik913.MoviesWebApp.Domain.GenreEntity;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectEntityException;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectTransferObjectException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for mapping object between GenreEntity and GenreTo transfer object classes.
 */
public class GenreMapper {

    /**
     * Maps GenreEntity object to transfer object.
     * @param entity GenreEntity object to be mapped.
     * @throws IncorrectEntityException if given entity is null.
     * @return GenreTo transfer object.
     */
    public static GenreTo map2To(GenreEntity entity) {
        if (entity == null)
            throw new IncorrectEntityException("Provided entity is null.");
        return new GenreTo(entity.getName());
    }

    /**
     * Maps GenreTo transfer object to GenreEntity.
     * @param genre - GenreTo object to be mapped.
     * @throws IncorrectTransferObjectException if transfer object is null
     * @return GenreEntity object.
     */
    public static GenreEntity map2Entity(GenreTo genre) {
        if (genre == null)
            throw new IncorrectTransferObjectException("Provided transfer object is null.");
        return new GenreEntity(genre.getName());
    }

    /**
     * Maps collection of GenreEntity to List of GenreTo transfer objects.<br>
     * More information by <code>map2To</code> method in this class.
     * @param entities collection of GenreEntity.
     * @return List of GenreTo
     * @throws IncorrectEntityException if provided collection contains any null value.
     */
    public static List<GenreTo> map2Tos(Collection<GenreEntity> entities) {
        return entities.stream()
                .map(GenreMapper::map2To)
                .collect(Collectors.toList());
    }

    /**
     * Maps collection of GenreTo transfer objects to List of GenreEntity entities.<br>
     * More information by <code>map2Entity</code> method in this class.
     * @param genreTos collection of GenreTo.
     * @return List of GenreEntity
     * @throws IncorrectTransferObjectException if provided collection contains any null value.
     */
    public static List<GenreEntity> map2Entities(Collection<GenreTo> genreTos) {
        return genreTos.stream()
                .map(GenreMapper::map2Entity)
                .collect(Collectors.toList());
    }
}
