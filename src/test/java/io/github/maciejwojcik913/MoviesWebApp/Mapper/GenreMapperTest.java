package io.github.maciejwojcik913.MoviesWebApp.Mapper;

import io.github.maciejwojcik913.MoviesWebApp.DTO.GenreTo;
import io.github.maciejwojcik913.MoviesWebApp.Domain.GenreEntity;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectEntityException;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectTransferObjectException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class GenreMapperTest {

    @Test
    void map2ToShouldConvertCorrectEntityToCorrectTransferObject() {
        //given
        var entity = new GenreEntity("test name");

        //when
        var to = GenreMapper.map2To(entity);

        //then
        assertEquals(entity.getName(), to.getName());
    }

    @Test
    void map2ToShouldThrowIncorrectEntityExceptionWhenEntityIsNull() {
        //when then
        assertThrows(IncorrectEntityException.class,
                () -> GenreMapper.map2To(null)
        );
    }

    @Test
    void map2EntityShouldConvertCorrectTransferObjectToCorrectEntity() {
        //given
        var to = new GenreTo("test name");

        //when
        var entity = GenreMapper.map2Entity(to);

        //then
        assertEquals(entity.getName(), to.getName());
    }

    @Test
    void map2EntityShouldThrowIncorrectTransferObjectExceptionWhenTransferObjectIsNull() {
        //when then
        assertThrows(IncorrectTransferObjectException.class,
                () -> GenreMapper.map2Entity(null)
        );
    }

    @Test
    void map2TosShouldConvertCorrectEntitiesToCorrectTransferObjects() {
        //given
        var entity0 = new GenreEntity("Sc-Fi");
        var entity1 = new GenreEntity("Drama");
        var entity2 = new GenreEntity("Horror");
        var entities = List.of(entity0, entity1, entity2);

        //when
        var tos = GenreMapper.map2Tos(entities);

        //then
        assertAll(
                () -> assertThat(tos.size(), equalTo(entities.size())),
                () -> assertThat(tos.get(0).getName(), equalTo(entity0.getName())),
                () -> assertThat(tos.get(1).getName(), equalTo(entity1.getName())),
                () -> assertThat(tos.get(2).getName(), equalTo(entity2.getName()))
        );
    }

    @Test
    void map2TosShouldThrowIncorrectEntityExceptionIfAnyEntityIsNull() {
        //given
        var entity0 = new GenreEntity("USA");
        var entity2 = new GenreEntity("France");
        var entities = new ArrayList<GenreEntity>();
        entities.add(entity0);
        entities.add(null);
        entities.add(entity2);

        //when then
        assertThrows(IncorrectEntityException.class,
                () -> GenreMapper.map2Tos(entities)
        );
    }

    @Test
    void map2EntitiesShouldConvertCorrectTransferObjectsToCorrectEntities() {
        //given
        var to0 = new GenreTo("USA");
        var to1 = new GenreTo("Sweden");
        var to2 = new GenreTo("France");
        var tos = List.of(to0, to1, to2);

        //when
        var entities = GenreMapper.map2Entities(tos);

        //then
        assertAll(
                () -> assertThat(tos.size(), equalTo(entities.size())),
                () -> assertThat(tos.get(0).getName(), equalTo(to0.getName())),
                () -> assertThat(tos.get(1).getName(), equalTo(to1.getName())),
                () -> assertThat(tos.get(2).getName(), equalTo(to2.getName()))
        );
    }

    @Test
    void map2EntitiesShouldThrowIncorrectTransferObjectExceptionIfAnyTransferObjectIsNull() {
        //given
        var to0 = new GenreTo("Comedy");
        var to1 = new GenreTo("Adventure");
        var tos = new ArrayList<GenreTo>();
        tos.add(to0);
        tos.add(null);
        tos.add(to1);

        //when then
        assertThrows(IncorrectTransferObjectException.class,
                () -> GenreMapper.map2Entities(tos)
        );
    }
}