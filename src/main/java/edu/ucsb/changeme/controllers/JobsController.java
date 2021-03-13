package edu.ucsb.changeme.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucsb.changeme.advice.AuthControllerAdvice;
import edu.ucsb.changeme.entities.Admin;
import edu.ucsb.changeme.entities.AppUser;
import edu.ucsb.changeme.models.Job;
import edu.ucsb.changeme.repositories.AdminRepository;
import edu.ucsb.changeme.repositories.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobsController {
  private final Logger logger = LoggerFactory.getLogger(JobsController.class);

  @Autowired
  AppUserRepository appUserRepository;

  @Autowired
  AdminRepository adminRepository;

  @Autowired
  private AuthControllerAdvice authControllerAdvice;

  private ObjectMapper mapper = new ObjectMapper();

  private ArrayList<Job> jobs = new ArrayList<Job>();

  public JobsController() {
    jobs.add(new Job("test-job", "Test Job"));
  }

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
    String body = mapper.writeValueAsString(jobs);
    return ResponseEntity.ok().body(body);
  }
}
