package my.project.services;

import my.project.domain.BackLog;
import my.project.domain.ProjectTask;
import my.project.exceptions.ProjectNotFoundException;
import my.project.repositories.BackLogRepository;
import my.project.repositories.ProjectRepository;
import my.project.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * |** @author 'Gihan Rathnayaka'**|
 */

@Service
@Transactional
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private BackLogRepository  backLogRepository;

    @Autowired
    private ProjectRepository projectRepository ;


    public ProjectTask  addProjectTask(String projectId,ProjectTask task){

        try {

            BackLog backLog = backLogRepository.findByProjectIdentifier(projectId);

            task.setBackLog(backLog);
            Integer sequence = backLog.getPTSequence();
            sequence++;
            task.setProjectSeq(backLog.getProjectIdentifier()+"-"+sequence);
            task.setProjectId(backLog.getProjectIdentifier());
            backLog.setPTSequence(sequence);

            if( task.getPriority()==null){
                task.setPriority(3);
            }
            if( task.getPriority()==0){
                task.setPriority(3);
            }
            if(task.getStatus()==null|| task.getStatus()==null){
                task.setStatus("TO_DO");
            }

            return  projectTaskRepository.save(task);

        }catch (Exception e){
//            e.printStackTrace();
            throw  new ProjectNotFoundException("Back log not found by identifier.");
        }

    }

    public List<ProjectTask> findByProjectId(String id) {

        if(projectRepository.findByProjectId(id)==null){
            throw  new ProjectNotFoundException("Project with id : '"+id+"' not exist");
        }

       return projectTaskRepository.findByProjectIdOrderByPriority(id);
    }


    public ProjectTask findByProjectTaskSeq(String sequence ,String ptId){

        BackLog backLog = backLogRepository.findByProjectIdentifier(sequence);

        if(backLog==null){
            throw  new ProjectNotFoundException("Project with id '"+sequence+"' does not exist.");
        }
        ProjectTask task = projectTaskRepository.findByProjectSeq(ptId);

        if(task==null){
            throw  new ProjectNotFoundException("Project task '"+ptId+"' not found.");
        }
        if(!task.getProjectId().equals(backLog.getProjectIdentifier())){
            throw new ProjectNotFoundException("Project task '"+ptId+"' does not exist in ptoject : '"+backLog.getProjectIdentifier()+"' ");
        }
        return task;
    }

    public  ProjectTask updateProjectTask(ProjectTask projectTask,String backLogId,String ptId){

        BackLog backLog = backLogRepository.findByProjectIdentifier(backLogId);

        if(backLog==null){
            throw  new ProjectNotFoundException("Project with id '"+backLogId+"' does not exist.");
        }
        ProjectTask task = projectTaskRepository.findByProjectSeq(ptId);
        if(task==null){
            throw  new ProjectNotFoundException("Project task '"+ptId+"' not found.");
        }
        if(!task.getProjectId().equals(backLog.getProjectIdentifier())){
            throw new ProjectNotFoundException("Project task '"+ptId+"' does not exist in ptoject : '"+backLog.getProjectIdentifier()+"' ");
        }
        projectTask =task;
        return projectTaskRepository.save(projectTask);
    }

    public String deleteTask(String backLogId, String pt_id) {


        BackLog backLog = backLogRepository.findByProjectIdentifier(backLogId);

        if(backLog==null){
            throw  new ProjectNotFoundException("Project with id '"+backLogId+"' does not exist.");
        }

        ProjectTask task = projectTaskRepository.findByProjectSeq(pt_id);
        if(task==null){
            throw  new ProjectNotFoundException("Project task '"+pt_id+"' not found.");
        }
        if(!task.getProjectId().equals(backLog.getProjectIdentifier())){
            throw new ProjectNotFoundException("Project task '"+pt_id+"' does not exist in ptoject : '"+backLog.getProjectIdentifier()+"' ");
        }

        projectTaskRepository.delete(task);
        return pt_id;
    }
}
