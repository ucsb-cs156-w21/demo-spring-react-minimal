package edu.ucsb.changeme.jobs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.ucsb.changeme.models.Job;
import edu.ucsb.changeme.entities.JobRecord;


public class TestJobTests {

  @Test
  public void test_perform() throws Exception {
    Job j = new TestJob();
    JobRecord jr = new JobRecord();
    jr.setKey(j.getKey());
    jr.setId(123L);
    JobRecord jrAfter = j.perform(jr);
    assertEquals(j.getKey(), jrAfter.getKey());
    assertTrue(jrAfter.getSuccess());
    // assertEquals("Test job completed successfully",jr.getMessage());
  }
 
}
