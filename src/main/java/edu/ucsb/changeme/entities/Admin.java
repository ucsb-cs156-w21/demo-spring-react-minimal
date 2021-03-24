package edu.ucsb.changeme.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private boolean permanentAdmin = false;

  public Admin(String email) {
    this.email = email;
  }

  public Admin(String email, boolean permanentAdmin) {
    this.email = email;
    this.permanentAdmin = permanentAdmin;
  }
}
