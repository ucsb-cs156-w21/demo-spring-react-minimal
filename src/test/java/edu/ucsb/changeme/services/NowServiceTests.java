package edu.ucsb.changeme.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NowServiceTests
{
  @Autowired
  private NowService nowService;

  @Test
  public void makeSureTheyDoNotCrash() {
      // not much else we can do
      nowService.now();
      nowService.currentTime();
  }
}
