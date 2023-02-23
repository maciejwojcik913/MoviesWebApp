package io.github.maciejwojcik913.MoviesWebApp.Mapper;

import io.github.maciejwojcik913.MoviesWebApp.DTO.PersonTo;
import io.github.maciejwojcik913.MoviesWebApp.Domain.PersonEntity;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectEntityException;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectTransferObjectException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for mapping object between PersonEntity and PersonTo transfer object classes.
 */
public class PersonMapper {

    /**
     * Maps PersonEntity object to transfer object.
     * @param entity PersonEntity.class object to be mapped.
     * @throws IncorrectEntityException if given entity is null.
     * @return PersonTo transfer object.
     */
    public static PersonTo map2To(PersonEntity entity) {
        if (entity == null) {
            throw new IncorrectEntityException("Provided entity is null.");
        } else if (entity.getFirstName() == null || entity.getLastName() == null || entity.getBirthDate() == null) {
            throw new IncorrectEntityException("Provided entity contains null required fields: {firstName: " + entity.getFirstName() + ", lastName: " + entity.getLastName() + ", birthDate: " + entity.getBirthDate() + "}.");
        }
        return new PersonTo(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthDate(),
                entity.getId(),
                entity.getDescription(),
                entity.getPhotoUrl(),
                entity.getRating()
        );
    }

    /**
     * Maps PersonTo transfer object to PersonEntity.
     * @param person - PersonTo.class object to be mapped.
     * @throws IncorrectTransferObjectException if transfer object is null
     * @return PersonEntity object.
     */
    public static PersonEntity map2Entity(PersonTo person) {
        if (person == null) {
            throw new IncorrectTransferObjectException("Provided transfer object is null.");
        }
        var entity = new PersonEntity(person.getFirstName(), person.getLastName(), person.getBirthDate());
        entity.setId(person.getId());
        entity.setDescription(person.getDescription());
        entity.setPhotoUrl(person.getPhotoUrl());
        entity.setRating(person.getRating());
        return entity;
    }

    /**
     * Maps collection of PersonEntity to List of PersonTo transfer objects.<br>
     * More information by <code>map2To</code> method in this class.
     * @param entities collection of PersonEntity.
     * @return List of PersonTo
     * @throws IncorrectEntityException if provided collection contains any null value.
     */
    public static List<PersonTo> map2Tos(Collection<PersonEntity> entities) {
        return entities.stream()
                .map(PersonMapper::map2To)
                .collect(Collectors.toList());
    }

    /**
     * Maps collection of PersonTo transfer objects to List of PersonEntity entities.<br>
     * More information by <code>map2Entity</code> method in this class.
     * @param countryTos collection of PersonEntity.
     * @return List of PersonEntity
     * @throws IncorrectTransferObjectException if provided collection contains any null value.
     */
    public static List<PersonEntity> map2Entities(Collection<PersonTo> countryTos) {
        return countryTos.stream()
                .map(PersonMapper::map2Entity)
                .collect(Collectors.toList());
    }
}
