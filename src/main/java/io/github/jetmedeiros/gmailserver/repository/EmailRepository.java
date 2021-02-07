package io.github.jetmedeiros.gmailserver.repository;

import io.github.jetmedeiros.gmailserver.model.Email;
import io.github.jetmedeiros.gmailserver.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {

//
//    @Transactional(readOnly=true)
//    @Query("SELECT u FROM Email u WHERE u.idUser = :idUser")
//    Page<Email> findByIdUser(@Param("idUser") Integer idUser, Pageable pageRequest);

}
