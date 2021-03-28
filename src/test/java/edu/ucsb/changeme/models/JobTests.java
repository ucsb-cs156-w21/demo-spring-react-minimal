package edu.ucsb.changeme.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.ucsb.changeme.entities.JobRecord;

public class JobTests {
    
    @Test
    public void test_job_perform_returns_null() {
        Job j = new Job();
        JobRecord jr = new JobRecord();
        jr.setKey(j.getKey());
        assertEquals(jr,j.perform(jr));
    }
}
