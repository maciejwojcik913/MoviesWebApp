package io.github.maciejwojcik913.MoviesWebApp.Domain;

import io.github.maciejwojcik913.MoviesWebApp.Domain.Abstract.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class CountryEntity extends AbstractEntity {

    @Id @Column(nullable = false, length = 30)
    private String name;

    public CountryEntity() {
    }

    public CountryEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
