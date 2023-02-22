package io.github.maciejwojcik913.MoviesWebApp.Repository;

import io.github.maciejwojcik913.MoviesWebApp.Domain.CountryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends BaseDao<CountryEntity, String>{
}
