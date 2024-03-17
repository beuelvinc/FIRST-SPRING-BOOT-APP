package com.java_learn.FirstSpring.review.Impl;

import com.java_learn.FirstSpring.company.Company;
import com.java_learn.FirstSpring.company.CompanyService;
import com.java_learn.FirstSpring.review.Review;
import com.java_learn.FirstSpring.review.ReviewRepository;
import com.java_learn.FirstSpring.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private final CompanyService companyService;
    public ReviewServiceImpl(ReviewRepository reviewRepository,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews  = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company!= null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewByID(Long companyId, Long reviewId) {

        //OPTION 1
        return reviewRepository.findByCompanyIdAndId(companyId,reviewId);
        //OPTION 2
//        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
//        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public Review updateReview(Long companyId, Long reviewId, Review review) {

        Review review1 = reviewRepository.findByCompanyIdAndId(companyId,reviewId);

        if (review1 != null){
            review1.setRating(review.getRating());
            review1.setTitle(review.getTitle());
            review1.setDescription(review.getDescription());
            reviewRepository.save(review1);
            return  review1;
        }
        return   null;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        try {
            if (companyService.getCompanyById(companyId)!= null && reviewRepository.existsById(reviewId))
            {
                Review review = reviewRepository.findById(reviewId).orElse(null);
                Company company = review.getCompany();
                if (company!= null){
                    company.getReviews().remove(review);
                    companyService.updateCompany(company,companyId);
                    reviewRepository.deleteById(reviewId);
                    return  true;
                }
                else{
                    return false;
                }

            }
            return false;
        }
        catch (Exception e){
            return  false;
        }
    }
}
