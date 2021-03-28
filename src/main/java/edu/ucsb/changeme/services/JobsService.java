package edu.ucsb.changeme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.ucsb.changeme.entities.JobRecord;
import edu.ucsb.changeme.jobs.TestJob;
import edu.ucsb.changeme.models.Job;
import edu.ucsb.changeme.repositories.JobRecordRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JobsService {

    @Autowired
    private  NowService nowService;

    @Autowired
    private  JobRecordRepository jobRecordRepository;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    private Map<String, Job> jobs = new HashMap<String, Job>();

    public JobsService() {
        addJob(new TestJob());
    }

    public void addJob(Job j) {
        jobs.put(j.getKey(), j);
    }

    public List<Job> getJobs() {
        ArrayList<Job> jobList = jobs.values().stream().collect(Collectors.toCollection(ArrayList::new));
        return jobList;
    }

    public Job getJob(String key) {
        return jobs.get(key);
    }

    public JobRecord submit(Job j, String paramsJson) {
        JobRecord jr = new JobRecord();
        jr.setKey(j.getKey());
        jr.setStarted(nowService.now());
        jr.setMessage("In progress");
        jr.setParamsJson(paramsJson);
        final JobRecord jrWithId = jobRecordRepository.save(jr);

        ListenableFuture<JobRecord> lfjs = taskScheduler.submitListenable(() -> j.perform(jrWithId));
        SuccessCallback<JobRecord> successCallback = (jobRecord) -> reportSuccess(jobRecord); 
        FailureCallback failureCallback = (throwable) -> reportFailure(throwable, jrWithId);
        lfjs.addCallback(successCallback, failureCallback);
        return jrWithId;
    }

    public JobRecord reportSuccess(JobRecord jr) {
        jr.setCompleted(nowService.now());
        jr.setSuccess(true);
        jobRecordRepository.save(jr);
        log.info("Job completed successfully: {}", jr);
        return jr;
    }

    public JobRecord reportFailure(Throwable throwable, JobRecord jr) {
        jr.setMessage("Failed with Exception: " + throwable.toString());
        jr.setCompleted(nowService.now());
        jr.setSuccess(false);
        jobRecordRepository.save(jr);
        log.info("Job failed : {}", jr);
        return jr;
    }

}