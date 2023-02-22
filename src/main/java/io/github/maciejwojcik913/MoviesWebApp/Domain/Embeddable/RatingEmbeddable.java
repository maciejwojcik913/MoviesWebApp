package io.github.maciejwojcik913.MoviesWebApp.Domain.Embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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

}
