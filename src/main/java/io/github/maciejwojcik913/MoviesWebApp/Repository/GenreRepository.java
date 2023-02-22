package io.github.maciejwojcik913.MoviesWebApp.Repository;

import io.github.maciejwojcik913.MoviesWebApp.Domain.GenreEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends BaseDao<GenreEntity, String> {
}
