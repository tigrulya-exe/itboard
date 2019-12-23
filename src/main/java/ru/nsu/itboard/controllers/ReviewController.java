package ru.nsu.itboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.nsu.itboard.models.Review;
import ru.nsu.itboard.services.ReviewService;

import java.util.List;

@RestController
@ComponentScan(value = "ru.nsu.itboard.exceptions")

public class ReviewController {
    private static final String EVENT_REVIEWS_PATH = "/event/{eventId}/r";

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(EVENT_REVIEWS_PATH)
    public List<Review> getReviews(@PathVariable String eventId) {
        return reviewService.getReviews(eventId);
    }

    @GetMapping(EVENT_REVIEWS_PATH + "/{reviewId}")
    public Review getReview(@PathVariable String reviewId) {
        return reviewService.getReview(reviewId);
    }

    @PostMapping(EVENT_REVIEWS_PATH)
    public Review addReview(@RequestBody Review reviewWithoutId) {
        return reviewService.addReview(reviewWithoutId);
    }

    @DeleteMapping(EVENT_REVIEWS_PATH + "/{reviewId}")
    public void removeReview(@PathVariable String reviewId) {
        reviewService.removeReview(reviewId);
    }
}
