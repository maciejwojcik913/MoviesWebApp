package io.github.maciejwojcik913.MoviesWebApp.DTO;

import io.github.maciejwojcik913.MoviesWebApp.Domain.CountryEntity;
import io.github.maciejwojcik913.MoviesWebApp.Domain.Embeddable.RatingEmbeddable;
import io.github.maciejwojcik913.MoviesWebApp.Domain.GenreEntity;
import io.github.maciejwojcik913.MoviesWebApp.Domain.PersonEntity;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectTransferObjectException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MovieTo {

    private String title;
    private LocalDate premiereDate;
    private Long id;
    private String description;
    private String coverUrl;
    private RatingEmbeddable rating;
    private Set<GenreEntity> genres = new HashSet<>();
    private Set<CountryEntity> productionCountries = new HashSet<>();
    private Set<PersonEntity> directors = new HashSet<>();
    private Set<PersonEntity> scenarists = new HashSet<>();
    private Set<PersonEntity> actors = new HashSet<>();

    private MovieTo() {
    }

    public MovieTo(String title, LocalDate premiereDate) {
        this();

        if (title == null || title.isEmpty())
            throw new IncorrectTransferObjectException("Title cannot be null or empty.");

        if (premiereDate == null)
            throw new IncorrectTransferObjectException("Premiere date cannot be null.");

        this.title = title;
        this.premiereDate = premiereDate;
    }

    public MovieTo(String title, LocalDate premiereDate, Long id, String description, String coverUrl, RatingEmbeddable rating, Set<GenreEntity> genres, Set<CountryEntity> productionCountries, Set<PersonEntity> directors, Set<PersonEntity> scenarists, Set<PersonEntity> actors) {
        this(title, premiereDate);
        this.id = id;
        this.description = description;
        this.coverUrl = coverUrl;
        this.rating = rating;
        this.genres = genres;
        this.productionCountries = productionCountries;
        this.directors = directors;
        this.scenarists = scenarists;
        this.actors = actors;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getPremiereDate() {
        return premiereDate;
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

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public RatingEmbeddable getRating() {
        return rating;
    }

    public void setRating(RatingEmbeddable rating) {
        this.rating = rating;
    }

    public Set<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreEntity> genres) {
        this.genres = genres;
    }

    public Set<CountryEntity> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(Set<CountryEntity> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public Set<PersonEntity> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<PersonEntity> directors) {
        this.directors = directors;
    }

    public Set<PersonEntity> getScenarists() {
        return scenarists;
    }

    public void setScenarists(Set<PersonEntity> scenarists) {
        this.scenarists = scenarists;
    }

    public Set<PersonEntity> getActors() {
        return actors;
    }

    public void setActors(Set<PersonEntity> actors) {
        this.actors = actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieTo movieTo = (MovieTo) o;
        return title.equals(movieTo.title) && premiereDate.equals(movieTo.premiereDate) && Objects.equals(id, movieTo.id) && Objects.equals(description, movieTo.description) && Objects.equals(coverUrl, movieTo.coverUrl) && Objects.equals(rating, movieTo.rating) && Objects.equals(genres, movieTo.genres) && Objects.equals(productionCountries, movieTo.productionCountries) && Objects.equals(directors, movieTo.directors) && Objects.equals(scenarists, movieTo.scenarists) && Objects.equals(actors, movieTo.actors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, premiereDate, id, description, coverUrl, rating, genres, productionCountries, directors, scenarists, actors);
    }
}
