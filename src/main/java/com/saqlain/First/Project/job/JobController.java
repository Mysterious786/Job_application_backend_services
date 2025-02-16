package com.saqlain.First.Project.job;
// importing list
import com.saqlain.First.Project.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/jobs") // base url path for all the methods..
public class JobController {
    // defining the return
    // all end point related to job will be here.
    //creating an end point to return the list of jobs
    // I am going to call job service so I am creating an object for that
//Basically we have refractor over code by building services that will help to add and find jobs and list jobs
    private JobService jobService;// Initialization is still left

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    // we can use RequestMapping also instead get put and all
    //Now lets map this
    @GetMapping
    //@RequestMapping(value = "/jobs",method = RequestMethod.GET) // using it at method level above is impl of class level
    public ResponseEntity<List<Job>> findAll()
    {

        return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> createJobs(@RequestBody Job job)
    {
        Company company = job.getCompany();
        if(company == null)
        {
         return new ResponseEntity<String>("Error!, Company does not exist",HttpStatus.NOT_FOUND);
        }
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully!",HttpStatus.CREATED);
    }

    @GetMapping("/{id}") // tell springboot that its dynamic {}...
    public ResponseEntity<Job> getJobById(@PathVariable long id) // define path variable for dynamic
    {
        Job job = jobService.getJobById(id);
        if(job!= null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        //we need to show 404 if no job available so for that we need to use Response Entity -- Helps to customize the http response...
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    //Now I want to delete a job...
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable long id)
    {
        // I need to find this job...
        // Let's create a service for this
        boolean isJob = jobService.deleteJobById(id);
        // check whether the job exits or not
        if (!isJob) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>("Job got deleted successfully",HttpStatus.OK);


    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable long id, @RequestBody Job updatedJob)
    {
        boolean updated = jobService.updateJob(id,updatedJob);
        if(updated)
        {
            return new ResponseEntity<>("Job updated Successfully!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Job Not Found!",HttpStatus.NOT_FOUND);
    }


}
