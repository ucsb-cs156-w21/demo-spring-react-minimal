
package edu.ucsb.changeme.repositories;

import edu.ucsb.changeme.entities.JobRecord;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRecordRepository extends CrudRepository<JobRecord, Long> {
  public List<JobRecord> findByKey(String key);
}
