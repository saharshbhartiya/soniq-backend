package com.saharsh.soniq.repository;

import com.saharsh.soniq.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN p.collaborators c WHERE p.owner.id = :userId OR c.id = :userId")
    List<Project> findProjectsByUserId(@Param("userId") Long userId);
}