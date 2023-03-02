package com.yavuzahmet.crudappunittest.repository;

import com.yavuzahmet.crudappunittest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}