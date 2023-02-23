package io.github.maciejwojcik913.MoviesWebApp.Mapper;

import io.github.maciejwojcik913.MoviesWebApp.DTO.CountryTo;
import io.github.maciejwojcik913.MoviesWebApp.Domain.CountryEntity;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectEntityException;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectTransferObjectException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for mapping object between GenreEntity and GenreTo transfer object classes.
 */
public class CountryMapper {

    /**
     * Maps CountryEntity object to transfer object.
     * @param entity CountryEntity object to be mapped.
     * @throws IncorrectEntityException if given entity is null.
     * @return CountryTo transfer object.
     */
    public static CountryTo map2To(CountryEntity entity) {
        if (entity == null)
            throw new IncorrectEntityException("Provided entity is null.");
        return new CountryTo(entity.getName());
    }

    /**
     * Maps CountryTo transfer object to CountryEntity.
     * @param country CountryTo object to be mapped.
     * @throws IncorrectTransferObjectException if transfer object is null
     * @return CountryEntity object.
     */
    public static CountryEntity map2Entity(CountryTo country) {
        if (country == null)
            throw new IncorrectTransferObjectException("Provided transfer object is null.");
        return new CountryEntity(country.getName());
    }

    /**
     * Maps collection of CountryEntity to List of CountryTo transfer objects.<br>
     * More information by <code>map2To</code> method in this class.
     * @param entities collection of CountryEntity.
     * @return List of CountryTo
     * @throws IncorrectEntityException if provided collection contains any null value.
     */
    public static List<CountryTo> map2Tos(Collection<CountryEntity> entities) {
        return entities.stream()
                .map(CountryMapper::map2To)
                .collect(Collectors.toList());
    }

    /**
     * Maps collection of CountryTo transfer objects to List of CountryEntity entities.<br>
     * More information by <code>map2Entity</code> method in this class.
     * @param countryTos collection of CountryTo.
     * @return List of CountryEntity
     * @throws IncorrectTransferObjectException if provided collection contains any null value.
     */
    public static List<CountryEntity> map2Entities(Collection<CountryTo> countryTos) {
        return countryTos.stream()
                .map(CountryMapper::map2Entity)
                .collect(Collectors.toList());
    }
}
