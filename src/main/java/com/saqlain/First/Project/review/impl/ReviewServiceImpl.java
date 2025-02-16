package com.saqlain.First.Project.review.impl;

import com.saqlain.First.Project.company.Company;
import com.saqlain.First.Project.company.CompanyService;
import com.saqlain.First.Project.review.Review;
import com.saqlain.First.Project.review.ReviewRepository;
import com.saqlain.First.Project.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    @Override
    public boolean addReview(Long companyId, Review review) {
        //step1 get company object
        //Inorder to get the company we need the company service....
        Company company = companyService.getCompanyById(companyId); // this is the company object..
        //Now if company exist or not check...
        if(company != null)
        {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        else return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        // so we will get all the reviews from getAllReviews method then we will filter it

        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        // converting list into stream then filtering
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);

    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        // check whether the company exist or not
        if(companyService.getCompanyById(companyId) != null)
        {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId))
        {
            Review review = reviewRepository.findById(reviewId).orElse(null);
          //  assert review != null;
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(company,companyId);
            reviewRepository.deleteById(reviewId);


            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }
}
