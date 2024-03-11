package org.example.q2validation.Controller;
import jakarta.validation.Valid;
import org.example.q2validation.Api.ApiResponse;
import org.example.q2validation.Model.ProjectTracker;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/project")
public class ProjectTrackerController {
    ArrayList<ProjectTracker> projects = new ArrayList<>();

    @GetMapping("/display")
    public ResponseEntity displayProject(){
        return ResponseEntity.status(200).body(projects);
    }
    @PostMapping("/create")
    public ResponseEntity createProject(@RequestBody @Valid ProjectTracker project, Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project Added"));
    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable  int index , @RequestBody @Valid ProjectTracker project, Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.set(index,project);
        return ResponseEntity.status(200).body(new ApiResponse("Project Updated"));
    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index ){
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Project Deleted"));
    }
    @PutMapping("/status/{index}")
    public ResponseEntity changeStatus(@PathVariable int index){
        if(projects.get(index).getStatus().equals("Not Started")){
            projects.get(index).setStatus("in Progress");
            return ResponseEntity.status(200).body(new ApiResponse("Status Changed"));
        }

        else if (projects.get(index).getStatus().equals("in Progress")){
            projects.get(index).setStatus("Completed");
            return ResponseEntity.status(200).body(new ApiResponse("Status Changed"));
        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Status is Completed already"));
        }
    }
    @GetMapping("/search/title/{title}")
    public ResponseEntity searchProjectTitle(@PathVariable String title){
        ArrayList<ProjectTracker>search= new ArrayList<>();
        for(ProjectTracker project: projects){
            if(project.getTitle().contains(title)){
                search.add(project);
            }
        }
        return ResponseEntity.status(200).body(search);

    }
    @GetMapping("/search/company/{companyName}")
    public ResponseEntity searchProjectCompany(@PathVariable String companyName){
        ArrayList<ProjectTracker>search= new ArrayList<>();
        for(ProjectTracker project: projects){
            if(project.getCompanyName().contains(companyName)){
                search.add(project);
            }
        }
        return ResponseEntity.status(200).body(search);
    }
}
