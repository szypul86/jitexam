package com.jitexam.jitexam.controller;

import com.jitexam.jitexam.entity.Project;
import com.jitexam.jitexam.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    /*public ResponseEntity<Page<Project>> getAllTasks(@PageableDefault(size = 100) Pageable pageable,
}*/

    private final ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.findAll();
    }

}
