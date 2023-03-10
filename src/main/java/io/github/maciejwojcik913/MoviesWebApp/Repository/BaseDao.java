package io.github.maciejwojcik913.MoviesWebApp.Repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    @Override
    List<T> findAll();

    Optional<T> findById(ID id);
}
