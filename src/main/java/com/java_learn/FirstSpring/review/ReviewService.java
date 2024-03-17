package com.java_learn.FirstSpring.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
    boolean  addReview(Long companyId,Review review);
    Review getReviewByID(Long companyId,Long reviewId);

    Review updateReview(Long companyId,Long reviewId,Review review);

    boolean  deleteReview(Long companyId,Long reviewId);

}
