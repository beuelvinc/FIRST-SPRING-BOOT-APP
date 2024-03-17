package com.java_learn.FirstSpring.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByCompanyId(Long companyId);
    Review findByCompanyIdAndId(Long companyId, Long reviewId);
}
