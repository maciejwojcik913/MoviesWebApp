package io.github.maciejwojcik913.MoviesWebApp.Services;

import io.github.maciejwojcik913.MoviesWebApp.DTO.CountryTo;
import io.github.maciejwojcik913.MoviesWebApp.Domain.CountryEntity;
import io.github.maciejwojcik913.MoviesWebApp.Exceptions.IncorrectParameterException;
import io.github.maciejwojcik913.MoviesWebApp.Mapper.CountryMapper;
import io.github.maciejwojcik913.MoviesWebApp.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(final CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /**
     * @throws IncorrectParameterException if name is null or empty.
     */
    public CountryTo save(String name) {
        validateParam(name, "Name");
        var toSave = new CountryEntity(name);
        CountryEntity saved = countryRepository.save(toSave);
        return CountryMapper.map2To(saved);
    }

    public List<CountryTo> findAll() {
        return CountryMapper.map2Tos(countryRepository.findAll());
    }

    /**
     * @throws IncorrectParameterException if name is null or empty.
     * @throws EntityNotFoundException if entity with given name was not found.
     */
    public CountryTo findByName(String name) {
        validateParam(name, "Name");
        Optional<CountryEntity> country = countryRepository.findById(name);
        return CountryMapper.map2To(country.orElseThrow(
                () -> new EntityNotFoundException("Could not find country with name: " + name)
        ));
    }

    /**
     * @throws IncorrectParameterException if name is null or empty.
     * @throws EntityNotFoundException if entity with given name was not found.
     */
    public void delete(String name) {
        validateParam(name, "Name");
        findByName(name);
        countryRepository.deleteById(name);
    }

    /**
     * @throws IncorrectParameterException if old-name or new-name is null or empty.
     * @throws EntityNotFoundException if entity with given old-name was not found.
     */
    public CountryTo update(String oldName, String newName) {
        validateParam(newName, "New name");
        validateParam(oldName, "Old name");
        delete(oldName);
        return save(newName);
    }

    /**
     * Method returns nothing if parameter is not null and not empty, unless throws exception.
     * @param param parameter to be validated
     * @param paramName name of parameter that is included in exception message.
     */
    private void validateParam(final String param, final String paramName) {
        var emptyMessage = "";
        var nullMessage = "";

        if (paramName == null) {
            emptyMessage = "Parameter is empty.";
            nullMessage = "Parameter is null.";
        } else {
            emptyMessage = paramName + " parameter is empty.";
            nullMessage = paramName + " parameter is null.";
        }

        if (param == null) {
            throw new IncorrectParameterException(nullMessage);
        }

        if (param.isEmpty()) {
            throw new IncorrectParameterException(emptyMessage);
        }
    }
}
