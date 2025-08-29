package com.dev.superior.dslist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.dev.superior.dslist.users.User;

public interface UserRepository extends JpaRepository<User, String>{
    UserDetails findByLogin(String login);
}
