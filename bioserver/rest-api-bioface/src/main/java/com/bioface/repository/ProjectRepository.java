package com.bioface.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bioface.model.EnumProjectStatus;
import com.bioface.model.Project;

public interface ProjectRepository extends MongoRepository<Project, String>, CustomProjectRepository {

	List<Project> findByUserIdAndStatus(String userId, EnumProjectStatus status);

}
