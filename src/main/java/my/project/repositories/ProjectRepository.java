package my.project.repositories;

import my.project.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * |** @author 'Gihan Rathnayaka'**|
 */

@Repository
public interface ProjectRepository extends CrudRepository<Project ,Long> {

    Project  findByProjectId(String projectId);

    @Override
    Iterable<Project> findAll();
}
