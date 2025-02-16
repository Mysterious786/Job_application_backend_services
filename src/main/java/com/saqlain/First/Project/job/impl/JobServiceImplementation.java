package com.saqlain.First.Project.job.impl;

import com.saqlain.First.Project.job.Job;
import com.saqlain.First.Project.job.JobRepository;
import com.saqlain.First.Project.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImplementation implements JobService {
    //private List<Job> jobs = new ArrayList<>();
    //creating an object of JobRepository...
    JobRepository jobRepository;

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    //Now lets move to create a constructor...
    private long nextId = 1L;

    @Override
    public List<Job> findAll() {
       // return jobs;
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
       // job.setId(nextId++);

        //jobs.add(job);
        jobRepository.save(job);

    }

    @Override
    public Job getJobById(Long id) {
//        // using for loop to find ..
//        for (Job job : jobs) {
//            if (job.getId().equals(id)) return job;
//        }
//        return null;
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
//        Iterator<Job> it = jobs.iterator();
//        while(it.hasNext())
//        {
//            Job job = it.next();
//            if(job.getId().equals(id))
//            {
//                it.remove();
//                return true;
//            }
//        }
//        return false;
        try {
            jobRepository.deleteById(id);
            return true;
        } catch(Exception e)
        {
            return false;
        }


    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
       // for (Job job : jobs) {
           // if (job.getId().equals(id)) { // Use '=='
        if(jobOptional.isPresent()){
            Job job = jobOptional.get(); // getting the job..

                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                jobRepository.save(job);
                return true; // Job updated successfully
            }
      //  }
        return false; // Job not found
    }

}

