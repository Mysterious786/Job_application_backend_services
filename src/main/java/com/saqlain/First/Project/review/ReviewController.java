package com.saqlain.First.Project.review;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId)
    {
        return new ResponseEntity<List<Review>>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")// inorder to add review we need the id as we define company in review class..
    public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody Review review)
    {
        boolean isAddedSuccessfully = reviewService.addReview(companyId,review);
        if(isAddedSuccessfully)
            return new ResponseEntity<String>("Review added successfully!",HttpStatus.CREATED);
        else
            return new ResponseEntity<String>("Error! Company dont exist!",HttpStatus.NOT_FOUND);

    }
    // building get review based on review id...

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId)
    {
        return new ResponseEntity<Review>(reviewService.getReview(companyId,reviewId), HttpStatus.OK) ;
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review)
    {
    //review id tells us which review is to be updated
        // review object tells us this is the new updated review
        // companyId helps us to fetch company to which we need to update the review...
        boolean isUpdatedReview = reviewService.updateReview(companyId,reviewId,review);

        if(isUpdatedReview)
            return new ResponseEntity<String>("Review updated successfully!",HttpStatus.OK);
        else
            return new ResponseEntity<String>("Error! Not able to update",HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId)
    {
        boolean isDeleted = reviewService.deleteReview(companyId,reviewId);
        if(isDeleted)
            return new ResponseEntity<String>("Review deleted successfully!",HttpStatus.OK);
        else
            return new ResponseEntity<String>("Review does not get deleted!",HttpStatus.NOT_FOUND);
    }
}
