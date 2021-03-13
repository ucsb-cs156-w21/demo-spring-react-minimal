package edu.ucsb.changeme.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
public class ScheduledJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String key;
    private String paramsJson;
    private Date scheduled;
    private Date started;
    private Date completed;
    private Boolean success;
    private String message;


    public ScheduledJob() {
    }

    public ScheduledJob(long id, String key, String paramsJson, Date scheduled, Date started, Date completed, Boolean success, String message) {
        this.id = id;
        this.key = key;
        this.paramsJson = paramsJson;
        this.scheduled = scheduled;
        this.started = started;
        this.completed = completed;
        this.success = success;
        this.message = message;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getParamsJson() {
        return this.paramsJson;
    }

    public void setParamsJson(String paramsJson) {
        this.paramsJson = paramsJson;
    }

    public Date getScheduled() {
        return this.scheduled;
    }

    public void setScheduled(Date scheduled) {
        this.scheduled = scheduled;
    }

    public Date getStarted() {
        return this.started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getCompleted() {
        return this.completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    public Boolean isSuccess() {
        return this.success;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ScheduledJob id(long id) {
        setId(id);
        return this;
    }

    public ScheduledJob key(String key) {
        setKey(key);
        return this;
    }

    public ScheduledJob paramsJson(String paramsJson) {
        setParamsJson(paramsJson);
        return this;
    }

    public ScheduledJob scheduled(Date scheduled) {
        setScheduled(scheduled);
        return this;
    }

    public ScheduledJob started(Date started) {
        setStarted(started);
        return this;
    }

    public ScheduledJob completed(Date completed) {
        setCompleted(completed);
        return this;
    }

    public ScheduledJob success(Boolean success) {
        setSuccess(success);
        return this;
    }

    public ScheduledJob message(String message) {
        setMessage(message);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ScheduledJob)) {
            return false;
        }
        ScheduledJob scheduledJob = (ScheduledJob) o;
        return id == scheduledJob.id && Objects.equals(key, scheduledJob.key) && Objects.equals(paramsJson, scheduledJob.paramsJson) && Objects.equals(scheduled, scheduledJob.scheduled) && Objects.equals(started, scheduledJob.started) && Objects.equals(completed, scheduledJob.completed) && Objects.equals(success, scheduledJob.success) && Objects.equals(message, scheduledJob.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key, paramsJson, scheduled, started, completed, success, message);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", key='" + getKey() + "'" +
            ", paramsJson='" + getParamsJson() + "'" +
            ", scheduled='" + getScheduled() + "'" +
            ", started='" + getStarted() + "'" +
            ", completed='" + getCompleted() + "'" +
            ", success='" + isSuccess() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
    
}