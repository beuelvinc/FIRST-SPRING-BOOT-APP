package com.java_learn.FirstSpring.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

    private JobService  jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/jobs")
    public  ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Added successfully",HttpStatus.OK );
    }

    @GetMapping("/job/{id}")
    public  ResponseEntity<Job> getJobById(@PathVariable Long id){

        Job job  =  jobService.getJobById(id);
        if (job !=null)
            return new ResponseEntity<>(job,HttpStatus.OK);
        return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}
