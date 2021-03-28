package edu.ucsb.changeme.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucsb.changeme.advice.AuthControllerAdvice;
import edu.ucsb.changeme.entities.JobRecord;
import edu.ucsb.changeme.models.Job;
import edu.ucsb.changeme.services.JobsService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin/jobs")
public class JobsController {

  @Autowired
  private AuthControllerAdvice authControllerAdvice;

  @Autowired
  private JobsService jobsService;

  private ObjectMapper mapper = new ObjectMapper();

  private ResponseEntity<String> getUnauthorizedResponse(String roleRequired) throws JsonProcessingException {
    Map<String, String> response = new HashMap<String, String>();
    response.put("error", String.format("Unauthorized; only %s may access this resource.", roleRequired));
    String body = mapper.writeValueAsString(response);
    return new ResponseEntity<String>(body, HttpStatus.UNAUTHORIZED);
  }

  @GetMapping("")
  public ResponseEntity<String> jobs(@RequestHeader("Authorization") String authorization)
      throws JsonProcessingException {
    if (!authControllerAdvice.getIsAdmin(authorization))
      return getUnauthorizedResponse("admin");
    List<Job> jobs = jobsService.getJobs();
    String body = mapper.writeValueAsString(jobs);
    return ResponseEntity.ok().body(body);
  }

  @GetMapping("/status")
  public ResponseEntity<String> jobrecords(@RequestHeader("Authorization") String authorization)
      throws JsonProcessingException {
    if (!authControllerAdvice.getIsAdmin(authorization))
      return getUnauthorizedResponse("admin");

    Page<JobRecord> jobrecords = jobsService.getJobRecords();

    String body = mapper.writeValueAsString(jobrecords);
    return ResponseEntity.ok().body(body);
  }

  @GetMapping("/status/paged")
  public ResponseEntity<String> jobrecords(@RequestHeader("Authorization") String authorization,
      @RequestParam("page") int page, @RequestParam("sizePerPage") int sizePerPage) throws JsonProcessingException {

    log.info("/status/paged page={} sizePerPage={}", page, sizePerPage);
    if (!authControllerAdvice.getIsAdmin(authorization))
      return getUnauthorizedResponse("admin");

    Page<JobRecord> jobrecords = jobsService.getJobRecords(page, sizePerPage);

    String body = mapper.writeValueAsString(jobrecords);
    log.info("body={}", body);
    
    return ResponseEntity.ok().body(body);
  }

  @PostMapping("/run/{key}")
  public ResponseEntity<String> runJob(@RequestHeader("Authorization") String authorization,
      @PathVariable("key") String key, @RequestBody String paramsJson) throws JsonProcessingException {
    if (!authControllerAdvice.getIsAdmin(authorization))
      return getUnauthorizedResponse("admin");

    Map<String, String> resultMap = new HashMap<String, String>();
    resultMap.put("key", key);

    Job j = jobsService.getJob(key);
    if (j == null) {
      resultMap.put("status", "unknown job");
    } else {
      JobRecord jr = jobsService.submit(j, paramsJson);
      resultMap.put("status", "started");
      resultMap.put("id", Long.toString(jr.getId()));
    }

    String body = mapper.writeValueAsString(resultMap);
    return ResponseEntity.ok().body(body);
  }

}
