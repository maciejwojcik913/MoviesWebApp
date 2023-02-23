package io.github.maciejwojcik913.MoviesWebApp.DTO;

import io.github.maciejwojcik913.MoviesWebApp.Domain.Embeddable.RatingEmbeddable;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectTransferObjectException;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Transfer object class for PersonEntity.<br>
 * Requires not null and not empty parameters: firstName, lastName, birthDate
 */
public class PersonTo {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Long id;
    private String description;
    private String photoUrl;
    private RatingEmbeddable rating;

    private PersonTo() {
    }

    /**
     * Constructor which provides not null and not empty parameters.
     * @param firstName required
     * @param lastName required
     * @param birthDate required
     * @throws IncorrectTransferObjectException if any parameter is null or empty.
     */
    public PersonTo(String firstName, String lastName, LocalDate birthDate) {
        this();

        if (firstName == null || firstName.isEmpty())
            throw new IncorrectTransferObjectException("First name cannot be null or empty.");

        if (lastName == null || lastName.isEmpty())
            throw new IncorrectTransferObjectException("Last name cannot be null or empty.");

        if (birthDate == null)
            throw new IncorrectTransferObjectException("Birth date cannot be null.");

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    /**
     * Parameters requirements:
     * @param firstName not null, not empty
     * @param lastName not null, not empty
     * @param birthDate not null
     * @param id optional
     * @param description optional
     * @param photoUrl optional
     * @param rating optional
     */
    public PersonTo(String firstName, String lastName, LocalDate birthDate, Long id, String description, String photoUrl, RatingEmbeddable rating) {
        this(firstName, lastName, birthDate);
        this.id = id;
        this.description = description;
        this.photoUrl = photoUrl;
        this.rating = rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public RatingEmbeddable getRating() {
        return rating;
    }

    public void setRating(RatingEmbeddable rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonTo personTo = (PersonTo) o;
        return firstName.equals(personTo.firstName) && lastName.equals(personTo.lastName) && birthDate.equals(personTo.birthDate) && Objects.equals(id, personTo.id) && Objects.equals(description, personTo.description) && Objects.equals(photoUrl, personTo.photoUrl) && Objects.equals(rating, personTo.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDate, id, description, photoUrl, rating);
    }
}
