package io.github.jetmedeiros.gmailserver.repository;

import io.github.jetmedeiros.gmailserver.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {
}
