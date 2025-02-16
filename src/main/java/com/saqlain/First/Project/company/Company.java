package com.saqlain.First.Project.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saqlain.First.Project.job.Job;
import com.saqlain.First.Project.review.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // now we need to map every company to its relevant job
    @JsonIgnore // to prevent infinite recursion while mapping company to jobs
    @OneToMany(mappedBy = "company") // means that one company have many jobs, map the field company which exists in job
    private List<Job> jobs;

    //@JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Review> reviews;
    public Company() { // defining a default constructor for the JPA
    }
// reviews part to do.... to link like same as that of jobs...


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
