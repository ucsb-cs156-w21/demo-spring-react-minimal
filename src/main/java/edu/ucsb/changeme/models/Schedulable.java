package edu.ucsb.changeme.models;

import edu.ucsb.changeme.entities.JobRecord;

@FunctionalInterface
public interface Schedulable {
    public JobRecord perform(JobRecord jr); 
}
