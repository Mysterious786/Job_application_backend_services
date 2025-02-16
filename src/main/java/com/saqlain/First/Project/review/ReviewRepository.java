package com.saqlain.First.Project.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long companyId); // automatically break down using the name...
    // tell jpa we want all the entity of the review and hence give us all the records by company id, as we have already map this to company in company.java

}
