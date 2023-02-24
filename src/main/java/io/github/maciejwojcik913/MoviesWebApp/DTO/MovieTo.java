package io.github.maciejwojcik913.MoviesWebApp.DTO;

import io.github.maciejwojcik913.MoviesWebApp.Domain.Embeddable.RatingEmbeddable;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectTransferObjectException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Transfer object class for MovieEntity.<br>
 * Requires not null and not empty parameters: title, premiereDate.
 */
public class MovieTo {

    private String title;
    private LocalDate premiereDate;
    private Long id;
    private String description;
    private String coverUrl;
    private RatingEmbeddable rating;
    private Set<GenreTo> genres = new HashSet<>();
    private Set<CountryTo> productionCountries = new HashSet<>();
    private Set<PersonTo> directors = new HashSet<>();
    private Set<PersonTo> scenarists = new HashSet<>();
    private Set<PersonTo> actors = new HashSet<>();

    private MovieTo() {
    }

    /**
     * Constructor that provides not null and not empty parameters.
     * @param title required
     * @param premiereDate required
     * @throws IncorrectTransferObjectException if any parameter is null or empty.
     */
    public MovieTo(String title, LocalDate premiereDate) {
        this();

        if (title == null || title.isEmpty())
            throw new IncorrectTransferObjectException("Title cannot be null or empty.");

        if (premiereDate == null)
            throw new IncorrectTransferObjectException("Premiere date cannot be null.");

        this.title = title;
        this.premiereDate = premiereDate;
    }

    /**
     * Parameters requirements:
     * @param title not null, not empty
     * @param premiereDate not null
     * @param id optional
     * @param description optional
     * @param coverUrl optional
     * @param rating optional
     * @param genres optional
     * @param productionCountries optional
     * @param directors optional
     * @param scenarists optional
     * @param actors optional
     */
    public MovieTo(String title, LocalDate premiereDate, Long id, String description, String coverUrl, RatingEmbeddable rating, Set<GenreTo> genres, Set<CountryTo> productionCountries, Set<PersonTo> directors, Set<PersonTo> scenarists, Set<PersonTo> actors) {
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

    public Set<GenreTo> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreTo> genres) {
        this.genres = genres;
    }

    public Set<CountryTo> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(Set<CountryTo> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public Set<PersonTo> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<PersonTo> directors) {
        this.directors = directors;
    }

    public Set<PersonTo> getScenarists() {
        return scenarists;
    }

    public void setScenarists(Set<PersonTo> scenarists) {
        this.scenarists = scenarists;
    }

    public Set<PersonTo> getActors() {
        return actors;
    }

    public void setActors(Set<PersonTo> actors) {
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
