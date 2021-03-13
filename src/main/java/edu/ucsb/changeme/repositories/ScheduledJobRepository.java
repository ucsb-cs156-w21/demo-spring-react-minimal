
package edu.ucsb.changeme.repositories;

import edu.ucsb.changeme.entities.ScheduledJob;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledJobRepository extends CrudRepository<ScheduledJob, Long> {
  public List<ScheduledJob> findByKey(String key);
}
