package com.loanstorm.userservice.repository;

import com.loanstorm.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
