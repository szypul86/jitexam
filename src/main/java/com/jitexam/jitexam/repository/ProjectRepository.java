package com.jitexam.jitexam.repository;

import com.jitexam.jitexam.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
