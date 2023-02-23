package io.github.maciejwojcik913.MoviesWebApp.Mapper;

import io.github.maciejwojcik913.MoviesWebApp.DTO.CountryTo;
import io.github.maciejwojcik913.MoviesWebApp.Domain.CountryEntity;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectEntityException;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectTransferObjectException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class CountryMapperTest {

    @Test
    void map2ToShouldConvertCorrectEntityToCorrectTransferObject() {
        //given
        var entity = new CountryEntity("test name");

        //when
        var to = CountryMapper.map2To(entity);

        //then
        assertEquals(entity.getName(), to.getName());
    }

    @Test
    void map2ToShouldThrowIncorrectEntityExceptionWhenEntityIsNull() {
        //when then
        assertThrows(IncorrectEntityException.class,
                () -> CountryMapper.map2To(null)
        );
    }

    @Test
    void map2EntityShouldConvertCorrectTransferObjectToCorrectEntity() {
        //given
        var to = new CountryTo("test name");

        //when
        var entity = CountryMapper.map2Entity(to);

        //then
        assertEquals(entity.getName(), to.getName());
    }

    @Test
    void map2EntityShouldThrowIncorrectTransferObjectExceptionWhenTransferObjectIsNull() {
        //when then
        assertThrows(IncorrectTransferObjectException.class,
                () -> CountryMapper.map2Entity(null)
        );
    }

    @Test
    void map2TosShouldConvertCorrectEntitiesToCorrectTransferObjects() {
        //given
        var entity0 = new CountryEntity("USA");
        var entity1 = new CountryEntity("Sweden");
        var entity2 = new CountryEntity("France");
        var entities = List.of(entity0, entity1, entity2);

        //when
        var tos = CountryMapper.map2Tos(entities);

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
        var entity0 = new CountryEntity("USA");
        var entity2 = new CountryEntity("France");
        var entities = new ArrayList<CountryEntity>();
        entities.add(entity0);
        entities.add(null);
        entities.add(entity2);

        //when then
        assertThrows(IncorrectEntityException.class,
                () -> CountryMapper.map2Tos(entities)
        );
    }

    @Test
    void map2EntitiesShouldConvertCorrectTransferObjectsToCorrectEntities() {
        //given
        var to0 = new CountryTo("USA");
        var to1 = new CountryTo("Sweden");
        var to2 = new CountryTo("France");
        var tos = List.of(to0, to1, to2);

        //when
        var entities = CountryMapper.map2Entities(tos);

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
        var to0 = new CountryTo("USA");
        var to1 = new CountryTo("France");
        var tos = new ArrayList<CountryTo>();
        tos.add(to0);
        tos.add(null);
        tos.add(to1);

        //when then
        assertThrows(IncorrectTransferObjectException.class,
                () -> CountryMapper.map2Entities(tos)
        );
    }
}