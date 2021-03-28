package edu.ucsb.changeme.models;
import edu.ucsb.changeme.entities.JobRecord;
import lombok.Data;

@Data
public class Job implements Schedulable {

    private String key;
    private String description;    
    public JobRecord perform(JobRecord jr) { return jr; }; 

    
}