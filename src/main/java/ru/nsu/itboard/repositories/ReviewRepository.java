package ru.nsu.itboard.repositories;

import ru.nsu.itboard.models.Review;

import java.util.Collection;
import java.util.List;

public interface ReviewRepository {
    Review getReview(String reviewId);

    Collection<Review> getReviews();

    void addReview(Review review);

    void removeReview(String reviewId);

    void updateReview(String reviewId, Review review);
}