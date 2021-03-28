package edu.ucsb.changeme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class JobRecord {
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
}