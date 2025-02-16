package com.saqlain.First.Project.job;

import com.saqlain.First.Project.company.Company;
import jakarta.persistence.*;

@Entity
//@Table(name = "job_table")

public class Job {
    // now we need to define what information we need in this class
    // we need a primary key to differentiate between other entities..
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // unique to every job
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary; // ctrl + D to duplicate on windows..
    private String location;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // defining relationship with company...
    @ManyToOne // because many jobs are linked to one company...
    private Company company;

    // generating constructors and getter setters


    public Job() {
    }

    public Job(Long id, String title, String description, String minSalary, String maxSalary, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
