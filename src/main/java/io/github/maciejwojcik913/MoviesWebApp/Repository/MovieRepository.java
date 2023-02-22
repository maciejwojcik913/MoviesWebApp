package io.github.maciejwojcik913.MoviesWebApp.Repository;

import io.github.maciejwojcik913.MoviesWebApp.Domain.MovieEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends BaseDao<MovieEntity, Long> {
}
