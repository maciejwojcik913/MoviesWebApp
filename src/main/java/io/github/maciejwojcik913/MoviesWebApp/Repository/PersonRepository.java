package io.github.maciejwojcik913.MoviesWebApp.Repository;

import io.github.maciejwojcik913.MoviesWebApp.Domain.PersonEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseDao<PersonEntity, Long> {
}
