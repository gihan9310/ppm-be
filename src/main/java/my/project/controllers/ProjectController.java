package my.project.controllers;


import my.project.domain.Project;
import my.project.services.ProjectService;
import my.project.utils.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return  new ResponseEntity (ErrorUtils.errorFlields(bindingResult.getFieldErrors()),HttpStatus.BAD_REQUEST);
        }

        Project pro = projectService.saveOrupdateProject(project);
        return  new ResponseEntity<>(pro, HttpStatus.CREATED);
    }

    @GetMapping(value = "find-by/{projectId}")
    public ResponseEntity<?> getByProjectId(@PathVariable("projectId") String projectId){

        Project pro = projectService.findByProjectId(projectId);
        return  new ResponseEntity<>(pro, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll(){
        Iterable<Project> all = projectService.getAll();
        return  new ResponseEntity<>(all, HttpStatus.CREATED);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable("projectId")String projectId){
        projectService.deleteProjectbyId(projectId);
        return  new ResponseEntity<>("Project successfully deleted", HttpStatus.CREATED);
    }
}
