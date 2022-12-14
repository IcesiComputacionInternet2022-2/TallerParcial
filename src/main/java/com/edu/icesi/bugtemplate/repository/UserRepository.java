package com.edu.icesi.bugtemplate.repository;

import com.edu.icesi.bugtemplate.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface UserRepository extends CrudRepository<User, UUID> {
}
