package my.project.repositories;

import my.project.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;

/**
 * |** @author 'Gihan Rathnayaka'**|
 */
public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {
}
