package edu.ucsb.changeme.models;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Job {
    private Logger logger = LoggerFactory.getLogger(Job.class);

    private String key;
    private String description;

    public JobStatus perform(Object params) {
        this.key = "Job (base class)";
        this.description = "Generic Job (base class)";
        logger.info("params: {}", params.toString());
        return new JobStatus(true,"Job completed successfully (base class job)");
    } 

    public Job() {
    }

    public Job(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Job)) {
            return false;
        }
        Job job = (Job) o;
        return Objects.equals(key, job.key) && Objects.equals(description, job.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, description);
    }

    @Override
    public String toString() {
        return "{" +
            " key='" + getKey() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

    

}