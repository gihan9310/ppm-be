package my.project.repositories;

import my.project.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * |** @author 'Gihan Rathnayaka'**|
 */
public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {

    List<ProjectTask>findByProjectIdOrderByPriority(String projectId);

    ProjectTask findByProjectSeq(String projectSeq);
}
