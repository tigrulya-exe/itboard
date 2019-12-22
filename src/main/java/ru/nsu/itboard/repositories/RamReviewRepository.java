package ru.nsu.itboard.repositories;

import org.springframework.stereotype.Repository;
import ru.nsu.itboard.exceptions.NotFoundException;
import ru.nsu.itboard.exceptions.WrongArgumentException;
import ru.nsu.itboard.models.Review;

import java.util.*;

@Repository
public class RamReviewRepository implements ReviewRepository{
    private Map<String, Review> reviews = new HashMap<>();

    private Review checkReview(String reviewId){
        Review review = reviews.get(reviewId);
        if(review == null)
            throw new NotFoundException("Wrong reviewId");
        return review;
    }

    @Override
    public Review getReview(String reviewId) {
        return checkReview(reviewId);
    }

    @Override
    public Collection<Review> getReviews() {
        return reviews.values();
    }

    @Override
    public void addReview(Review review) {
        if (review == null)
            throw new WrongArgumentException("Wrong review");
        reviews.put(review.getId(), review);
    }

    @Override
    public void removeReview(String reviewId) {
        checkReview(reviewId);
        reviews.remove(reviewId);
    }

    @Override
    public void updateReview(String reviewId, Review review) {
        checkReview(reviewId);
        reviews.put(reviewId, review);
    }
}
