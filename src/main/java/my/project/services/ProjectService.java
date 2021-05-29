package my.project.services;

import my.project.domain.BackLog;
import my.project.domain.Project;
import my.project.exceptions.ProjectIdException;
import my.project.repositories.BackLogRepository;
import my.project.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * |** @author 'Gihan Rathnayaka'**|
 */

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BackLogRepository backLogRepository;

    public Project saveOrupdateProject(Project project){

        try {
            project.setProjectId(project.getProjectId().toUpperCase());
            if(project.getId()==null){
                BackLog backLog = new BackLog();
                backLog.setProjectIdentifier(project.getProjectId());
                backLog.setProject(project);
                project.setBackLog(backLog);
            }else {
                project.setBackLog(backLogRepository.findByProjectIdentifier(project.getProjectId()));
            }

            return projectRepository.save(project);
        }catch (Exception e){
            e.printStackTrace();
            throw  new ProjectIdException("Project Id "+project.getProjectId()+" already exist");
        }
    }


    public Project findByProjectId(String projectId){
        Project project = projectRepository.findByProjectId(projectId.toUpperCase());

        if(project== null){
            throw  new ProjectIdException("Project Not Exist");
        }

        return project;
    }

    public Iterable<Project> getAll(){

        return projectRepository.findAll();
    }

    public  void deleteProjectbyId(String projectId){
        Project project = projectRepository.findByProjectId(projectId.toUpperCase());

        if(project== null){
            throw  new ProjectIdException("Cannot delete Project with Id : "+projectId);
        }

        projectRepository.delete(project);
    }
}
