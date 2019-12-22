package ru.nsu.itboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.itboard.models.Review;
import ru.nsu.itboard.repositories.ReviewRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviews(String eventId) {
        Collection<Review> reviews = reviewRepository.getReviews();
        return reviews
                .stream()
                .filter(r -> r.getId().equals(eventId))
                .collect(Collectors.toList());
    }

    public Review getReview(String reviewId) {
        return reviewRepository.getReview(reviewId);
    }

    public Review addReview(Review reviewWithoutId) {
        reviewWithoutId.generateId();
        reviewRepository.addReview(reviewWithoutId);
        return reviewWithoutId;
    }

    public void removeReview(String reviewId) {
        reviewRepository.removeReview(reviewId);
    }
}
