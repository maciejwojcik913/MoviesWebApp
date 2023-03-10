package io.github.maciejwojcik913.MoviesWebApp.Services;

import io.github.maciejwojcik913.MoviesWebApp.DTO.CountryTo;
import io.github.maciejwojcik913.MoviesWebApp.Domain.CountryEntity;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectParameterException;
import io.github.maciejwojcik913.MoviesWebApp.Mapper.CountryMapper;
import io.github.maciejwojcik913.MoviesWebApp.Repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    private CountryRepository countryRepository;
    private CountryService SuT;

    @BeforeEach
    void cleanUp() {
        countryRepository = Mockito.mock(CountryRepository.class);
        SuT = new CountryService(countryRepository);
    }

    @Test
    void saveShouldThrowIncorrectParameterExceptionWhenNameIsNull() {
        // given when then
        assertThrows(IncorrectParameterException.class,
                () -> SuT.save(null)
        );
    }

    @Test
    void saveShouldThrowIncorrectParameterExceptionWhenNameIsEmpty() {
        // given when then
        assertThrows(IncorrectParameterException.class,
                () -> SuT.save("")
        );
    }

    @Test
    void saveShouldReturnCountryTransferObjectWithGivenName() {
        // given
        var france = "France";
        var countryToSave = new CountryEntity(france);
        given(countryRepository.save(any(CountryEntity.class))).willReturn(countryToSave);

        // when
        CountryTo country = SuT.save(france);

        // then
        assertThat(country.getName(), equalTo(countryToSave.getName()));
    }

    @Test
    void findAllShouldReturnEmptyListIfRepositoryDoesNotHaveResultsToReturn() {
        // given
        given(countryRepository.findAll()).willReturn(new ArrayList<>());

        // when
        var results = SuT.findAll();

        // then
        assertThat(results, is(empty()));
    }

    @Test
    void findAllShouldReturnTransferObjectListMatchingToEntitiesFromRepository() {
        // given
        var repoEntities = List.of(new CountryEntity("USA"), new CountryEntity("Germany"), new CountryEntity("Poland"));
        var expectedTos = CountryMapper.map2Tos(repoEntities);
        given(countryRepository.findAll()).willReturn(repoEntities);

        // when
        var foundCountries = SuT.findAll();

        // then
        assertAll(
                () -> assertThat(foundCountries.size(), equalTo(repoEntities.size())),
                () -> assertThat(foundCountries, hasItems(expectedTos.get(0), expectedTos.get(1), expectedTos.get(2)))
        );
    }

    @Test
    void findByNameShouldThrowIncorrectParameterExceptionWhenNameIsNull() {
        // given when then
        assertThrows(IncorrectParameterException.class,
                () -> SuT.findByName(null)
        );
    }

    @Test
    void findByNameShouldThrowIncorrectParameterExceptionWhenNameIsEmpty() {
        // given when then
        assertThrows(IncorrectParameterException.class,
                () -> SuT.findByName("")
        );
    }

    @Test
    void findByNameShouldReturnMatchingCountry() {
        // given
        var countryName = "Spain";
        given(countryRepository.findById(countryName)).willReturn(Optional.of(new CountryEntity(countryName)));

        // when
        CountryTo result = SuT.findByName(countryName);

        // then
        assertThat(result.getName(), is(countryName));
    }

    @Test
    void findByNameShouldThrowEntityNotFoundExceptionIfNoneMatch() {
        // given
        var searchingName = "Sweden";

        // when then
        var exception = assertThrows(
                EntityNotFoundException.class,
                () -> SuT.findByName(searchingName)
        );
        assertThat(exception.getMessage(), containsString("Could not find country with name: " + searchingName));
    }

    @Test
    void deleteShouldThrowIncorrectParameterExceptionIfNameIsNull() {
        // given when then
        assertThrows(IncorrectParameterException.class,
                () -> SuT.delete(null)
        );
    }

    @Test
    void deleteShouldThrowIncorrectParameterExceptionIfNameIsEmpty() {
        // given when then
        assertThrows(IncorrectParameterException.class,
                () -> SuT.delete("")
        );
    }

    @Test
    void deleteShouldThrowEntityNotFoundExceptionIfEntityToBeDeletedDoesNotExist() {
        // given
        given(countryRepository.findById(any())).willReturn(Optional.empty());

        // when then
        assertThrows(EntityNotFoundException.class,
                () -> SuT.delete("Name")
        );
    }

    @Test
    void deleteShouldNotThrowExceptionWhenGivenNameIsCorrectAndExists() {
        // given
        var name = "USA";
        given(countryRepository.findById(name)).willReturn(Optional.of(new CountryEntity(name)));

        // when then
        assertDoesNotThrow(
                () -> SuT.delete(name)
        );
    }

    @Test
    void updateShouldThrowIncorrectParameterExceptionIfOldNameIsNull() {
        // given when then
        assertThrows(IncorrectParameterException.class,
                () -> SuT.update(null, "newName")
        );
    }

    @Test
    void updateShouldThrowIncorrectParameterExceptionIfOldNameIsEmpty() {
        // given when then
        assertThrows(IncorrectParameterException.class,
                () -> SuT.update("", "newName")
        );
    }

    @Test
    void updateShouldThrowIncorrectParameterExceptionIfNewNameIsNull() {
        // given when then
        assertThrows(IncorrectParameterException.class,
                () -> SuT.update("oldName", null)
        );
    }

    @Test
    void updateShouldThrowIncorrectParameterExceptionIfNewNameIsEmpty() {
        // given when then
        assertThrows(IncorrectParameterException.class,
                () -> SuT.update("oldName", "")
        );
    }

    @Test
    void updateShouldThrowEntityNotFoundExceptionIfEntityToBeUpdatedDoesNotExist() {
        // given
        given(countryRepository.findById(any())).willReturn(Optional.empty());

        // when then
        assertThrows(EntityNotFoundException.class,
                () -> SuT.update("oldName", "newName")
        );
    }

    @Test
    void updateShouldReturnUpdatedCountry() {
        // given
        var oldName = "Ger";
        var newName = "Germany";
        given(countryRepository.findById(oldName)).willReturn(Optional.of(new CountryEntity(oldName)));

        // when
        when(countryRepository.save(any(CountryEntity.class))).thenReturn(new CountryEntity(newName));
        var updated = SuT.update(oldName, newName);

        // then
        assertThat(updated.getName(), equalTo(newName));
    }
}