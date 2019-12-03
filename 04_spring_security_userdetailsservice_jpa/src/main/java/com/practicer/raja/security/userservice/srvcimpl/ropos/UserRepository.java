package com.practicer.raja.security.userservice.srvcimpl.ropos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicer.raja.security.userservice.srvcimpl.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
}
