package com.saharsh.soniq.services;

import com.saharsh.soniq.dto.CreateProjectRequest;
import com.saharsh.soniq.dto.OwnerResponse;
import com.saharsh.soniq.dto.ProjectResponse;
import com.saharsh.soniq.entity.Project;
import com.saharsh.soniq.entity.User;
import com.saharsh.soniq.repository.ProjectRepository;
import com.saharsh.soniq.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    public ProjectResponse createProjectAndConvertToDto(CreateProjectRequest request, Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found: " + ownerId));
        Project newProject = new Project();
        newProject.setTitle(request.getTitle());
        newProject.setBpm(request.getBpm());
        newProject.setOwner(owner);
        return convertToDto(projectRepository.save(newProject));
    }

    public List<ProjectResponse> findProjectsForUserAndConvertToDto(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        List<Project> projects = projectRepository.findProjectsByUserId(userId);
        return projects.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Project addCollaborator(Long projectId, String collaboratorEmail) {
        User collaborator = userRepository.findByEmail(collaboratorEmail)
                .orElseThrow(() -> new RuntimeException("User not found: " + collaboratorEmail));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found: " + projectId));

        if (project.getOwner().getId().equals(collaborator.getId())) {
            throw new IllegalStateException("Owner cannot be a collaborator.");
        }
        Set<User> collaborators = project.getCollaborators();
        if (!collaborators.add(collaborator)) {
            throw new IllegalStateException("User is already a collaborator.");
        }
        return projectRepository.save(project);
    }

    private ProjectResponse convertToDto(Project project) {
        OwnerResponse ownerResponse = new OwnerResponse();
        ownerResponse.setId(project.getOwner().getId());
        ownerResponse.setName(project.getOwner().getName());
        
        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setTitle(project.getTitle());
        response.setBpm(project.getBpm());
        response.setOwner(ownerResponse);
        return response;
    }
}