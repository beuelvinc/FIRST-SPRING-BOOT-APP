package com.java_learn.FirstSpring.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable("companyId") Long companyId){

        return  new ResponseEntity<List<Review>>(reviewService.getAllReviews(companyId), HttpStatus.OK);

    }
    @PostMapping("/reviews")
    public ResponseEntity<String> addReview( @PathVariable("companyId") Long companyId,
                                             @RequestBody Review review){
        boolean created  = reviewService.addReview(companyId,review);
        if (created){
            return  new ResponseEntity<>("review created", HttpStatus.OK);
        }
        return  new ResponseEntity<>("review could not be created", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById( @PathVariable("companyId") Long companyId,
                                             @PathVariable("reviewId") Long reviewId){
        Review review =  reviewService.getReviewByID(companyId,reviewId);
        if (review != null){
            return  new ResponseEntity<>(review, HttpStatus.OK);
        }
        return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }


    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview( @PathVariable("companyId") Long companyId,
                                                 @PathVariable("reviewId") Long reviewId,@RequestBody Review review){
        Review updatedReview =  reviewService.updateReview(companyId,reviewId,review);
        if (updatedReview != null){
            return  new ResponseEntity<>(updatedReview, HttpStatus.OK);
        }
        return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview( @PathVariable("companyId") Long companyId,
                                                @PathVariable("reviewId") Long reviewId){
        boolean deleted =  reviewService.deleteReview(companyId,reviewId);
        if (deleted){
            return  new ResponseEntity<>("deleted", HttpStatus.OK);
        }
        return  new ResponseEntity<>("could not be deleted", HttpStatus.NOT_FOUND);

    }


}
