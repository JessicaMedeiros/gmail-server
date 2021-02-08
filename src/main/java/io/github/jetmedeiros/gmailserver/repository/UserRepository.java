package io.github.jetmedeiros.gmailserver.repository;

import io.github.jetmedeiros.gmailserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional(readOnly=true)
    Optional<User> findByUsername(String username);
}
