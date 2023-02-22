package io.github.maciejwojcik913.MoviesWebApp.Domain;

import io.github.maciejwojcik913.MoviesWebApp.Domain.Abstract.AbstractEntity;
import io.github.maciejwojcik913.MoviesWebApp.Domain.Embeddable.RatingEmbeddable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "person", uniqueConstraints = @UniqueConstraint(columnNames = {"first_name", "last_name", "birth_date"}))
public class PersonEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(length = 1000)
    private String description;

    @Column(name = "photo_url")
    private String photo;

    @Embedded
    private RatingEmbeddable rating;

    public PersonEntity() {
    }

    public PersonEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public RatingEmbeddable getRating() {
        return rating;
    }

    public void setRating(RatingEmbeddable rating) {
        this.rating = rating;
    }
}
