package my.project.repositories;

import my.project.domain.BackLog;
import org.springframework.data.repository.CrudRepository;

/**
 * |** @author 'Gihan Rathnayaka'**|
 */
public interface BackLogRepository extends CrudRepository<BackLog,Long> {

    BackLog findByProjectIdentifier(String id);
}
