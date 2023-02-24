package io.github.maciejwojcik913.MoviesWebApp.Mapper;

import io.github.maciejwojcik913.MoviesWebApp.DTO.CountryTo;
import io.github.maciejwojcik913.MoviesWebApp.DTO.GenreTo;
import io.github.maciejwojcik913.MoviesWebApp.DTO.MovieTo;
import io.github.maciejwojcik913.MoviesWebApp.DTO.PersonTo;
import io.github.maciejwojcik913.MoviesWebApp.Domain.CountryEntity;
import io.github.maciejwojcik913.MoviesWebApp.Domain.Embeddable.RatingEmbeddable;
import io.github.maciejwojcik913.MoviesWebApp.Domain.GenreEntity;
import io.github.maciejwojcik913.MoviesWebApp.Domain.MovieEntity;
import io.github.maciejwojcik913.MoviesWebApp.Domain.PersonEntity;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectEntityException;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectTransferObjectException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MovieMapperTest {

    @Test
    void map2ToShouldSuccessfullyMapEntityToTransferObjectWithParameters_title_premiereDate() {
        // given
        var movieToMap = new MovieEntity("title", LocalDate.of(1999, 2, 14));
        var expectedAfterMapping = new MovieTo("title", LocalDate.of(1999, 2, 14));

        // when
        MovieTo mapped = MovieMapper.map2To(movieToMap);

        // then
        assertThat(mapped, is(expectedAfterMapping));
    }

    @Test
    void map2ToShouldSuccessfullyMapEntityToTransferObjectWithRequiredParametersAndAdditionalParameters() {
        // given
        var movie = prepareMovieEntities().get(0);

        // when
        var movieTo = MovieMapper.map2To(movie);

        // then
        assertAll(
                () -> assertThat(movieTo.getId(), equalTo(movieTo.getId())),
                () -> assertThat(movieTo.getTitle(), equalTo(movieTo.getTitle())),
                () -> assertThat(movieTo.getPremiereDate(), equalTo(movieTo.getPremiereDate())),
                () -> assertThat(movieTo.getDescription(), equalTo(movieTo.getDescription())),
                () -> assertThat(movieTo.getCoverUrl(), equalTo(movieTo.getCoverUrl())),
                () -> assertThat(movieTo.getRating(), equalTo(movieTo.getRating())),
                () -> assertThat(movieTo.getGenres(), equalTo(movieTo.getGenres())),
                () -> assertThat(movieTo.getProductionCountries(), equalTo(movieTo.getProductionCountries())),
                () -> assertThat(movieTo.getDirectors(), equalTo(movieTo.getDirectors())),
                () -> assertThat(movieTo.getScenarists(), equalTo(movieTo.getScenarists())),
                () -> assertThat(movieTo.getActors(), equalTo(movieTo.getActors()))
        );

    }

    @Test
    void map2ToShouldThrowIncorrectEntityExceptionWhenEntityIsNull() {
        //given when then
        assertThrows(IncorrectEntityException.class, () -> MovieMapper.map2To(null));
    }

    @Test
    void map2ToShouldThrowIncorrectEntityExceptionWhenTryingMapEntityWithNullParameter_title_ToTransferObject() {
        // given
        var movie = new MovieEntity(null, LocalDate.now());

        // when then
        var exception = assertThrows(IncorrectEntityException.class, () -> MovieMapper.map2To(movie));
        assertThat(exception.getMessage(), containsString("title: null"));
    }

    @Test
    void map2ToShouldThrowIncorrectEntityExceptionWhenTryingMapEntityWithNullParameter_premiereDate_ToTransferObject() {
        // given
        var movie = new MovieEntity("title", null);

        // when then
        var exception = assertThrows(IncorrectEntityException.class, () -> MovieMapper.map2To(movie));
        assertThat(exception.getMessage(), containsString("premiereDate: null"));
    }

    @Test
    void map2EntityShouldSuccessfullyMapTransferObjectToEntityWithParameters_title_premiereDate() {
        // given
        var movie = new MovieTo("title", LocalDate.of(1983, 1, 23));
        var expectedAfterMapping = new MovieEntity("title", LocalDate.of(1983, 1, 23));

        // when
        var mapped = MovieMapper.map2Entity(movie);

        // then
        assertThat(mapped, samePropertyValuesAs(expectedAfterMapping));
    }

    @Test
    void map2EntityShouldSuccessfullyMapTransferObjectToEntityWithParameters_title_premiereDate_AndAdditionalParameters() {
        // given
        var premiereDate = LocalDate.of(2010, 10, 10);
        var movie = new MovieTo("title", premiereDate, 65L, "desc", "url", null, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        var expectedAfterMapping = new MovieEntity("title", premiereDate);
        expectedAfterMapping.setId(65L);
        expectedAfterMapping.setDescription("desc");
        expectedAfterMapping.setCoverUrl("url");

        // when
        var mapped = MovieMapper.map2Entity(movie);

        // then
        assertThat(mapped, samePropertyValuesAs(expectedAfterMapping));
    }

    @Test
    void map2EntityShouldThrowIncorrectTransferObjectExceptionWhenTransferObjectIsNull() {
        // given when then
        assertThrows(IncorrectTransferObjectException.class,
                () -> MovieMapper.map2Entity(null)
        );
    }

    @Test
    void map2TosShouldConvertCorrectEntitiesToCorrectTransferObjects() {
        // given
        List<MovieEntity> movies = prepareMovieEntities();

        // when
        var movieTos = MovieMapper.map2Tos(movies);

        // then
        assertAll(
                () -> assertThat(movieTos.size(), is(movies.size())),
                () -> assertThat(movieTos.get(0).getId(), equalTo(movies.get(0).getId())),
                () -> assertThat(movieTos.get(0).getDescription(), equalTo(movies.get(0).getDescription())),
                () -> assertThat(movieTos.get(0).getCoverUrl(), equalTo(movies.get(0).getCoverUrl())),
                () -> assertThat(movieTos.get(0).getRating(), equalTo(movies.get(0).getRating())),
                () -> assertThat(movieTos.get(1).getId(), equalTo(movies.get(1).getId())),
                () -> assertThat(movieTos.get(1).getDescription(), equalTo(movies.get(1).getDescription())),
                () -> assertThat(movieTos.get(1).getCoverUrl(), equalTo(movies.get(1).getCoverUrl())),
                () -> assertThat(movieTos.get(1).getRating(), equalTo(movies.get(1).getRating()))
        );
    }

    @Test
    void map2TosShouldThrowIncorrectEntityExceptionIfAnyEntityIsNull() {
        // given
        List<MovieEntity> entities = new ArrayList<>(prepareMovieEntities());
        entities.add(null);

        // when then
        assertThrows(IncorrectEntityException.class,
                () -> MovieMapper.map2Tos(entities)
        );
    }

    @Test
    void map2EntitiesShouldConvertCorrectTransferObjectsToCorrectEntities() {
        // given
        List<MovieTo> movieTos = prepareMovieTos();

        // when
        var entities = MovieMapper.map2Entities(movieTos);

        // then
        assertAll(
                () -> assertThat(entities.size(), is(movieTos.size())),
                () -> assertThat(entities.get(0).getId(), equalTo(movieTos.get(0).getId())),
                () -> assertThat(entities.get(0).getDescription(), equalTo(movieTos.get(0).getDescription())),
                () -> assertThat(entities.get(0).getCoverUrl(), equalTo(movieTos.get(0).getCoverUrl())),
                () -> assertThat(entities.get(0).getRating(), equalTo(movieTos.get(0).getRating())),
                () -> assertThat(entities.get(1).getId(), equalTo(movieTos.get(1).getId())),
                () -> assertThat(entities.get(1).getDescription(), equalTo(movieTos.get(1).getDescription())),
                () -> assertThat(entities.get(1).getCoverUrl(), equalTo(movieTos.get(1).getCoverUrl())),
                () -> assertThat(entities.get(1).getRating(), equalTo(movieTos.get(1).getRating()))
        );
    }

    @Test
    void map2EntitiesShouldThrowIncorrectTransferObjectExceptionIfAnyTransferObjectIsNull() {
        // given
        List<MovieTo> tos = new ArrayList<>(prepareMovieTos());
        tos.add(null);

        // when then
        assertThrows(IncorrectTransferObjectException.class,
                () -> MovieMapper.map2Entities(tos)
        );
    }

    /**
     * Methods providing test data.
     */
    private List<MovieEntity> prepareMovieEntities() {
        var genres = prepareMovieGenreEntities();
        var countries = prepareMovieProductionCountryEntities();
        var people = preparePersonEntities();

        var m1 = new MovieEntity("title1", LocalDate.now());
        m1.setId(1L);
        m1.setDescription("desc1");
        m1.setCoverUrl("url1");
        m1.setRating(new RatingEmbeddable(423, 7.89));
        m1.setGenres(Set.of(genres.get(0), genres.get(1)));
        m1.setProductionCountries(Set.of(countries.get(3)));
        m1.setDirectors(Set.of(people.get(0)));
        m1.setScenarists(Set.of(people.get(1), people.get(0)));
        m1.setActors(Set.of(people.get(2), people.get(3), people.get(4)));

        var m2 = new MovieEntity("title2", LocalDate.now());
        m2.setId(2L);
        m2.setDescription("desc2");
        m2.setCoverUrl("url2");
        m2.setRating(new RatingEmbeddable(3421, 5.27));
        m2.setGenres(Set.of(genres.get(2), genres.get(3)));
        m2.setProductionCountries(Set.of(countries.get(1), countries.get(2)));
        m2.setDirectors(Set.of(people.get(0), people.get(1)));
        m2.setScenarists(Set.of(people.get(2), people.get(0)));
        m2.setActors(Set.of(people.get(2), people.get(5), people.get(4)));

        return List.of(m1, m2);
    }

    private List<PersonEntity> preparePersonEntities() {
        return List.of(
                new PersonEntity("fName1", "lName1", LocalDate.of(2001, 1, 1)),
                new PersonEntity("fName2", "lName2", LocalDate.of(2002, 2, 2)),
                new PersonEntity("fName3", "lName3", LocalDate.of(2003, 3, 3)),
                new PersonEntity("fName4", "lName4", LocalDate.of(2004, 4, 4)),
                new PersonEntity("fName5", "lName5", LocalDate.of(2005, 5, 5)),
                new PersonEntity("fName6", "lName6", LocalDate.of(2006, 6, 6))
        );
    }

    private List<GenreEntity> prepareMovieGenreEntities() {
        return List.of(
                new GenreEntity("Drama"),
                new GenreEntity("Comedy"),
                new GenreEntity("Horror"),
                new GenreEntity("Action")
        );
    }

    private List<CountryEntity> prepareMovieProductionCountryEntities() {
        return List.of(
                new CountryEntity("USA"),
                new CountryEntity("France"),
                new CountryEntity("Germany"),
                new CountryEntity("Japan")
        );
    }

    private List<MovieTo> prepareMovieTos() {
        var genres = prepareMovieGenreTos();
        var countries = prepareMovieProductionCountryTos();
        var people = preparePersonTos();

        var m1 = new MovieTo("title1", LocalDate.now());
        m1.setId(1L);
        m1.setDescription("desc1");
        m1.setCoverUrl("url1");
        m1.setRating(new RatingEmbeddable(423, 7.89));
        m1.setGenres(Set.of(genres.get(0), genres.get(1)));
        m1.setProductionCountries(Set.of(countries.get(3)));
        m1.setDirectors(Set.of(people.get(0)));
        m1.setScenarists(Set.of(people.get(1), people.get(0)));
        m1.setActors(Set.of(people.get(2), people.get(3), people.get(4)));

        var m2 = new MovieTo("title2", LocalDate.now());
        m2.setId(2L);
        m2.setDescription("desc2");
        m2.setCoverUrl("url2");
        m2.setRating(new RatingEmbeddable(243, 3.39));
        m2.setGenres(Set.of(genres.get(1)));
        m2.setProductionCountries(Set.of(countries.get(1), countries.get(2)));
        m2.setDirectors(Set.of(people.get(0), people.get(1)));
        m2.setScenarists(Set.of(people.get(1), people.get(0)));
        m2.setActors(Set.of(people.get(0), people.get(2), people.get(4)));

        return List.of(m1, m2);
    }

    private List<PersonTo> preparePersonTos() {
        return List.of(
                new PersonTo("fName1", "lName1", LocalDate.of(2001, 1, 1)),
                new PersonTo("fName2", "lName2", LocalDate.of(2002, 2, 2)),
                new PersonTo("fName3", "lName3", LocalDate.of(2003, 3, 3)),
                new PersonTo("fName4", "lName4", LocalDate.of(2004, 4, 4)),
                new PersonTo("fName5", "lName5", LocalDate.of(2005, 5, 5)),
                new PersonTo("fName6", "lName6", LocalDate.of(2006, 6, 6))
        );
    }

    private List<GenreTo> prepareMovieGenreTos() {
        return List.of(
                new GenreTo("Drama"),
                new GenreTo("Comedy"),
                new GenreTo("Horror"),
                new GenreTo("Action")
        );
    }

    private List<CountryTo> prepareMovieProductionCountryTos() {
        return List.of(
                new CountryTo("USA"),
                new CountryTo("France"),
                new CountryTo("Germany"),
                new CountryTo("Japan")
        );
    }
}