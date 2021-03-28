

package edu.ucsb.changeme.services;

import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.List;

import edu.ucsb.changeme.jobs.TestJob;
import edu.ucsb.changeme.models.Job;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class JobsServiceTests {


  @InjectMocks
  private JobsService jobsService = new JobsService();



  @Test
  public void test_getJobs() throws Exception {

   List<Job> jobs = jobsService.getJobs();
   assertTrue(jobs.contains(new TestJob()));
  }

}
