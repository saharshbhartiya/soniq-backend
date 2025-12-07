package com.saharsh.soniq.controller;

import com.saharsh.soniq.dto.CreateProjectRequest;
import com.saharsh.soniq.dto.ProjectResponse;
import com.saharsh.soniq.entity.Project;
import com.saharsh.soniq.entity.User;
import com.saharsh.soniq.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired private ProjectService projectService;

    @PostMapping
    public ProjectResponse createProject(@RequestBody CreateProjectRequest request, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        return projectService.createProjectAndConvertToDto(request, currentUser.getId());
    }

    @GetMapping("/user/{userId}")
    public List<ProjectResponse> getProjectsForUser(@PathVariable Long userId) {
        return projectService.findProjectsForUserAndConvertToDto(userId);
    }

    @PostMapping("/{projectId}/collaborators")
    public Project addCollaborator(@PathVariable Long projectId, @RequestBody com.saharsh.soniq.dto.RegisterRequest request) {
        return projectService.addCollaborator(projectId, request.getEmail());
    }
}