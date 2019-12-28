package ru.nsu.itboard.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.nsu.itboard.models.Review;
import ru.nsu.itboard.services.ReviewService;

import java.util.List;

@RestController
@ComponentScan(value = "ru.nsu.itboard.exceptions")

public class ReviewController {
    private static final String EVENT_REVIEWS_PATH = "/events/{eventId}/reviews";

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(EVENT_REVIEWS_PATH)
    @ApiOperation(value = "Получение всех отзывов о мероприятии")
    public List<Review> getReviews(@PathVariable String eventId) {
        return reviewService.getReviews(eventId);
    }

    @GetMapping(EVENT_REVIEWS_PATH + "/{reviewId}")
    @ApiOperation(value = "Получение конкретного отзыва о мероприятии")
    public Review getReview(@PathVariable String reviewId) {
        return reviewService.getReview(reviewId);
    }

    @PostMapping(EVENT_REVIEWS_PATH)
    @ApiOperation(value = "Добавление отзыва о мероприятии")
    public Review addReview(@RequestBody Review reviewWithoutId) {
        return reviewService.addReview(reviewWithoutId);
    }

    @DeleteMapping(EVENT_REVIEWS_PATH + "/{reviewId}")
    @ApiOperation(value = "Удаление отзыва о мероприятии")
    public void removeReview(@PathVariable String reviewId) {
        reviewService.removeReview(reviewId);
    }
}
