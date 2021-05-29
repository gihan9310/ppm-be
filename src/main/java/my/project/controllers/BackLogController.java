package my.project.controllers;

import my.project.domain.Project;
import my.project.domain.ProjectTask;
import my.project.services.ProjectTaskService;
import my.project.utils.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * |** @author 'Gihan Rathnayaka'**|
 */

@RestController
@RequestMapping("/back-log-task")
@CrossOrigin
public class BackLogController {


    @Autowired
    private ProjectTaskService projectTaskService;

    @PostMapping(value = "/add/{backLogId}")
    public ResponseEntity<?>addProjectTaskToBackLog(@Valid @RequestBody ProjectTask task , BindingResult result , @PathVariable("backLogId")String backLogId){

        if(result.hasErrors()){
            return  ErrorUtils.errorFlields(result.getFieldErrors());
        }

        ProjectTask projectTask = projectTaskService.addProjectTask(backLogId, task);

        return  new ResponseEntity(projectTask, HttpStatus.OK);

    }


    @GetMapping(value = "/{backLogId}")
    public ResponseEntity<?> findProjectBackLog(@PathVariable("backLogId") String backLogId){

        List<ProjectTask> byProjectId = projectTaskService.findByProjectId(backLogId);
        return new ResponseEntity(byProjectId, HttpStatus.OK);
    }

    @GetMapping(value = "/{backLogId}/{pt_id}")
    public ResponseEntity<?> findProjectTask(@PathVariable("backLogId") String backLogId,@PathVariable("pt_id") String pt_id){

        ProjectTask task = projectTaskService.findByProjectTaskSeq(backLogId, pt_id);
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @PatchMapping(value = "/{backLogId}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask , BindingResult result ,@PathVariable("backLogId") String backLogId,@PathVariable("pt_id") String pt_id){

        if(result.hasErrors()){
            return  ErrorUtils.errorFlields(result.getFieldErrors());
        }

        ProjectTask task = projectTaskService.updateProjectTask(projectTask, backLogId, pt_id);
        return new ResponseEntity(task, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{backLogId}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable("backLogId") String backLogId,@PathVariable("pt_id") String pt_id){

        String taskId = projectTaskService.deleteTask(backLogId, pt_id);
        return new ResponseEntity(taskId, HttpStatus.OK);
    }
}
