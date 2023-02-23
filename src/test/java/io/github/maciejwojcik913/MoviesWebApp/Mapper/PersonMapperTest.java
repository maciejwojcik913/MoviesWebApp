package io.github.maciejwojcik913.MoviesWebApp.Mapper;

import io.github.maciejwojcik913.MoviesWebApp.DTO.PersonTo;
import io.github.maciejwojcik913.MoviesWebApp.Domain.Embeddable.RatingEmbeddable;
import io.github.maciejwojcik913.MoviesWebApp.Domain.PersonEntity;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectEntityException;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectTransferObjectException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonMapperTest {

    @Test
    void map2ToShouldSuccessfullyMapEntityToTransferObjectWithParameters_firstName_lastName_birthDate() {
        // given
        var personEntity = new PersonEntity("Johnny", "Deep", LocalDate.of(1965, 7, 21));

        // when
        var personTo = PersonMapper.map2To(personEntity);

        // then
        assertAll(
                () -> assertThat(personTo.getFirstName(), equalTo(personEntity.getFirstName())),
                () -> assertThat(personTo.getLastName(), equalTo(personEntity.getLastName())),
                () -> assertThat(personTo.getBirthDate(), equalTo(personEntity.getBirthDate())),
                () -> assertThat(personTo.getId(), equalTo(null)),
                () -> assertThat(personTo.getDescription(), equalTo(null)),
                () -> assertThat(personTo.getPhotoUrl(), equalTo(null)),
                () -> assertThat(personTo.getRating(), equalTo(null))
        );
    }

    @Test
    void map2ToShouldSuccessfullyMapEntityToTransferObjectWithParameters_firstName_lastName_birthDate_AndAdditionalParameters() {
        // given
        var personEntity = new PersonEntity("Brad", "Pitt", LocalDate.of(1962, 1, 22));
        personEntity.setDescription("description");
        personEntity.setRating(new RatingEmbeddable(572, 6.74));

        // when
        var personTo = PersonMapper.map2To(personEntity);

        // then
        assertAll(
                () -> assertThat(personTo.getFirstName(), equalTo(personEntity.getFirstName())),
                () -> assertThat(personTo.getLastName(), equalTo(personEntity.getLastName())),
                () -> assertThat(personTo.getBirthDate(), equalTo(personEntity.getBirthDate())),
                () -> assertThat(personTo.getId(), equalTo(null)),
                () -> assertThat(personTo.getDescription(), equalTo("description")),
                () -> assertThat(personTo.getPhotoUrl(), equalTo(null)),
                () -> assertThat(personTo.getRating(), equalTo(new RatingEmbeddable(572, 6.74)))
        );
    }

    @Test
    void map2ToShouldThrowIncorrectEntityExceptionWhenEntityIsNull() {
        // given when then
        assertThrows(IncorrectEntityException.class,
                () -> PersonMapper.map2To(null)
        );
    }

    @Test
    void map2ToShouldThrowIncorrectEntityExceptionWhenTryingMapEntityWithNullParameter_firstName_ToTransferObject() {
        // given
        var personEntity = new PersonEntity(null, "Deep", LocalDate.of(1965, 7, 21));

        // when then
        var exception = assertThrows(IncorrectEntityException.class,
                () -> PersonMapper.map2To(personEntity)
        );
        assertThat(exception.getMessage(), stringContainsInOrder("entity", "contains", "null", "required", "fields"));
    }

    @Test
    void map2ToShouldThrowIncorrectEntityExceptionWhenTryingMapEntityWithNullParameter_lastName_ToTransferObject() {
        // given
        var personEntity = new PersonEntity("Johnny", null, LocalDate.of(1965, 7, 21));

        // when then
        var exception = assertThrows(IncorrectEntityException.class,
                () -> PersonMapper.map2To(personEntity)
        );
        assertThat(exception.getMessage(), containsString("lastName: null"));
    }

    @Test
    void map2ToShouldThrowIncorrectEntityExceptionWhenTryingMapEntityWithNullParameter_birthDate_ToTransferObject() {
        // given
        var personEntity = new PersonEntity("Johnny", "Deep", null);

        // when then
        assertThrows(IncorrectEntityException.class,
                () -> PersonMapper.map2To(personEntity)
        );
    }

    @Test
    void map2EntityShouldSuccessfullyMapTransferObjectToEntityWithParameters_firstName_lastName_birthDate() {
        // given
        var personTo = new PersonTo("Johnny", "Deep", LocalDate.of(1965, 7, 21));

        // when
        var personEntity = PersonMapper.map2Entity(personTo);

        // then
        assertAll(
                () -> assertThat(personEntity.getFirstName(), equalTo(personTo.getFirstName())),
                () -> assertThat(personEntity.getLastName(), equalTo(personTo.getLastName())),
                () -> assertThat(personEntity.getBirthDate(), equalTo(personTo.getBirthDate())),
                () -> assertThat(personEntity.getId(), equalTo(null)),
                () -> assertThat(personEntity.getDescription(), equalTo(null)),
                () -> assertThat(personEntity.getPhotoUrl(), equalTo(null)),
                () -> assertThat(personEntity.getRating(), equalTo(null))
        );
    }

    @Test
    void map2EntityShouldSuccessfullyMapTransferObjectToEntityWithParameters_firstName_lastName_birthDate_AndAdditionalParameters() {
        // given
        var personTo = new PersonTo("Johnny", "Deep", LocalDate.of(1965, 7, 21));
        personTo.setDescription("description");
        personTo.setRating(new RatingEmbeddable(572, 6.74));

        // when
        var personEntity = PersonMapper.map2Entity(personTo);

        // then
        assertAll(
                () -> assertThat(personEntity.getFirstName(), equalTo(personTo.getFirstName())),
                () -> assertThat(personEntity.getLastName(), equalTo(personTo.getLastName())),
                () -> assertThat(personEntity.getBirthDate(), equalTo(personTo.getBirthDate())),
                () -> assertThat(personEntity.getId(), equalTo(null)),
                () -> assertThat(personEntity.getDescription(), equalTo("description")),
                () -> assertThat(personEntity.getPhotoUrl(), equalTo(null)),
                () -> assertThat(personEntity.getRating(), equalTo(new RatingEmbeddable(572, 6.74)))
        );
    }

    @Test
    void map2EntityShouldThrowIncorrectTransferObjectExceptionWhenTransferObjectIsNull() {
        // given when then
        assertThrows(IncorrectTransferObjectException.class,
                () -> PersonMapper.map2Entity(null)
        );
    }

    @Test
    void map2TosShouldConvertCorrectEntitiesToCorrectTransferObjects() {
        // given
        var p0 = new PersonEntity("Johnny", "Deep", LocalDate.of(1965, 7, 21));
        var p1 = new PersonEntity("Brad", "Pitt", LocalDate.of(1963, 8, 12));
        var p2 = new PersonEntity("Robert", "Wieckiewicz", LocalDate.of(1964, 9, 29));
        var persons = List.of(p0, p1, p2);

        //when
        var tos = PersonMapper.map2Tos(persons);

        // then
        assertAll(
                () -> assertThat(tos, hasSize(persons.size())),
                () -> assertEquals(p0.getFirstName(), tos.get(0).getFirstName()),
                () -> assertEquals(p0.getLastName(), tos.get(0).getLastName()),
                () -> assertEquals(p0.getBirthDate(), tos.get(0).getBirthDate()),
                () -> assertEquals(p1.getFirstName(), tos.get(1).getFirstName()),
                () -> assertEquals(p1.getLastName(), tos.get(1).getLastName()),
                () -> assertEquals(p1.getBirthDate(), tos.get(1).getBirthDate()),
                () -> assertEquals(p2.getFirstName(), tos.get(2).getFirstName()),
                () -> assertEquals(p2.getLastName(), tos.get(2).getLastName()),
                () -> assertEquals(p2.getBirthDate(), tos.get(2).getBirthDate())
        );
    }
    
    @Test
    void map2TosShouldThrowIncorrectEntityExceptionIfAnyEntityIsNull(){
        // given
        var p0 = new PersonEntity("Johnny", "Deep", LocalDate.of(1965, 7, 21));
        var p1 = new PersonEntity("Brad", "Pitt", LocalDate.of(1963, 8, 12));
        var persons = new ArrayList<PersonEntity>();
        persons.add(p0);
        persons.add(null);
        persons.add(p1);

        // when then
        assertThrows(IncorrectEntityException.class,
                () -> PersonMapper.map2Tos(persons)
        );
    }

    @Test
    void map2EntitiesShouldConvertCorrectTransferObjectsToCorrectEntities() {
        // given
        var p0 = new PersonTo("Johnny", "Deep", LocalDate.of(1965, 7, 21));
        var p1 = new PersonTo("Brad", "Pitt", LocalDate.of(1963, 8, 12));
        var p2 = new PersonTo("Robert", "Wieckiewicz", LocalDate.of(1964, 9, 29));
        var persons = List.of(p0, p1, p2);

        //when
        var entities = PersonMapper.map2Entities(persons);

        // then
        assertAll(
                () -> assertThat(entities, hasSize(persons.size())),
                () -> assertEquals(p0.getFirstName(), entities.get(0).getFirstName()),
                () -> assertEquals(p0.getLastName(), entities.get(0).getLastName()),
                () -> assertEquals(p0.getBirthDate(), entities.get(0).getBirthDate()),
                () -> assertEquals(p1.getFirstName(), entities.get(1).getFirstName()),
                () -> assertEquals(p1.getLastName(), entities.get(1).getLastName()),
                () -> assertEquals(p1.getBirthDate(), entities.get(1).getBirthDate()),
                () -> assertEquals(p2.getFirstName(), entities.get(2).getFirstName()),
                () -> assertEquals(p2.getLastName(), entities.get(2).getLastName()),
                () -> assertEquals(p2.getBirthDate(), entities.get(2).getBirthDate())
        );
    }

    @Test
    void map2EntitiesShouldThrowIncorrectTransferObjectExceptionIfAnyTransferObjectIsNull() {
        // given
        var p0 = new PersonTo("Johnny", "Deep", LocalDate.of(1965, 7, 21));
        var p1 = new PersonTo("Brad", "Pitt", LocalDate.of(1963, 8, 12));
        var persons = new ArrayList<PersonTo>();
        persons.add(p0);
        persons.add(null);
        persons.add(p1);

        // when then
        assertThrows(IncorrectTransferObjectException.class,
                () -> PersonMapper.map2Entities(persons)
        );
    }
}