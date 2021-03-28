package edu.ucsb.changeme.jobs;

import edu.ucsb.changeme.models.Job;
import edu.ucsb.changeme.entities.JobRecord;


public class TestJob extends Job {
    public TestJob() {
        setKey("test-job");
        setDescription("Test Job");
    }

    public JobRecord perform(JobRecord jr) {

        // Do whatever you need to do

        jr.setMessage("Test Job Completed Successfully");
        jr.setSuccess(true);
        return jr;
    }

}
