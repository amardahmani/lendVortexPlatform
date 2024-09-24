package com.loanvortex.userservice.repository;

import com.loanvortex.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
