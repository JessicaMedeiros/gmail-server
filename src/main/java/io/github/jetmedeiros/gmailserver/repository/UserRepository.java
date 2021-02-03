package io.github.jetmedeiros.gmailserver.repository;

import io.github.jetmedeiros.gmailserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
