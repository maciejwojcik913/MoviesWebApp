package io.github.maciejwojcik913.MoviesWebApp.Domain.Embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class RatingEmbeddable {

    private Integer votes;
    @Column(precision = 4, scale = 2)
    private Double rating;

    public RatingEmbeddable() {
    }

    public RatingEmbeddable(Integer votes, Double rating) {
        this.votes = votes;
        this.rating = rating;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingEmbeddable that = (RatingEmbeddable) o;
        return Objects.equals(votes, that.votes) && Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(votes, rating);
    }
}
