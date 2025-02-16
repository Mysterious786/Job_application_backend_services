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
}
