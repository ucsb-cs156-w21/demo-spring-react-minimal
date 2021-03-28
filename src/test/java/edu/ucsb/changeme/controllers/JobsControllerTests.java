package edu.ucsb.changeme.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucsb.changeme.advice.AuthControllerAdvice;
import edu.ucsb.changeme.entities.Admin;
import edu.ucsb.changeme.entities.AppUser;
import edu.ucsb.changeme.jobs.TestJob;
import edu.ucsb.changeme.models.Job;
import edu.ucsb.changeme.repositories.AdminRepository;
import edu.ucsb.changeme.repositories.AppUserRepository;
import edu.ucsb.changeme.repositories.JobRecordRepository;
import edu.ucsb.changeme.services.JobsService;
import edu.ucsb.changeme.services.NowService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


@WebMvcTest(value = JobsController.class)
@WithMockUser
public class JobsControllerTests {

  private String exampleAuthToken = "Bearer blah";

  private ObjectMapper mapper = new ObjectMapper();
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  AuthControllerAdvice mockAuthControllerAdvice;

  @MockBean
  private JobsService mockJobsService;

  @Test
  public void test_get_jobs_unauthorizedIfNotAdmin() throws Exception {
    mockMvc.perform(get("/api/admin/jobs").contentType("application/json")
        .header(HttpHeaders.AUTHORIZATION, exampleAuthToken)).andExpect(status().is(401));
  }

  @Test
  public void test_get_jobs_returnsListOfJobs() throws Exception {
    List<Job> expectedJobs = new ArrayList<Job>();
    expectedJobs.add(new TestJob());
    
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.ENGLISH);
    java.util.Date now = sdf.parse("06/24/2017 12:34");

    when(mockJobsService.getJobs()).thenReturn(expectedJobs);

    when(mockAuthControllerAdvice.getIsAdmin(anyString())).thenReturn(true);
    MvcResult response =
        mockMvc
            .perform(get("/api/admin/jobs").contentType("application/json")
                .header(HttpHeaders.AUTHORIZATION, exampleAuthToken))
            .andExpect(status().isOk()).andReturn();
    String responseString = response.getResponse().getContentAsString();
    List<Job> jobs = mapper.readValue(responseString, new TypeReference<List<Job>>() {
    });
    assertEquals(expectedJobs, jobs);
  }

}
