package com.saqlain.First.Project.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId, Review review); // what if company don't exist...
    Review getReview(Long companyId, Long reviewId);
    boolean updateReview(Long companyId,Long reviewId, Review review);
}
