package edu.ucsb.changeme.models;

import java.util.Objects;


public class JobStatus {
    private boolean success;
    private String message;

    public JobStatus(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return message;
    }


    public JobStatus() {
    }

    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof JobStatus)) {
            return false;
        }
        JobStatus jobStatus = (JobStatus) o;
        return success == jobStatus.success && Objects.equals(message, jobStatus.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message);
    }


}