package com.trans.todos.api.v1.repositories;

import com.trans.todos.api.v1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
