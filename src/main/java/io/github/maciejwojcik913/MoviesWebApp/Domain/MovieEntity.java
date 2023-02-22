package io.github.maciejwojcik913.MoviesWebApp.Domain;

import io.github.maciejwojcik913.MoviesWebApp.Domain.Abstract.AbstractEntity;
import io.github.maciejwojcik913.MoviesWebApp.Domain.Embeddable.RatingEmbeddable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie", uniqueConstraints = @UniqueConstraint(columnNames = {"title", "premiere_date"}))
public class MovieEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 80)
    private String title;

    @Column(nullable = false, name = "premiere_date")
    private LocalDate premiereDate;

    @Column(length = 1000)
    private String description;

    @Column(name = "cover_url")
    private String coverUrl;

    @Embedded
    private RatingEmbeddable rating;

    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_name", referencedColumnName = "name")
    )
    private Set<GenreEntity> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "movie_production_countries",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "country_name", referencedColumnName = "name")
    )
    private Set<CountryEntity> productionCountries = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "movie_directors",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id")
    )
    private Set<PersonEntity> directors = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "movie_scenarists",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id")
    )
    private Set<PersonEntity> scenarists = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id")
    )
    private Set<PersonEntity> actors = new HashSet<>();

    public MovieEntity() {
    }

    public MovieEntity(String title, LocalDate premiereDate) {
        this.title = title;
        this.premiereDate = premiereDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(LocalDate premiereDate) {
        this.premiereDate = premiereDate;
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
}
