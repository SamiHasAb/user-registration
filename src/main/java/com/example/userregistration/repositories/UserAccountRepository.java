package com.example.userregistration.repositories;

import com.example.userregistration.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;



@Repository
@Transactional(readOnly = true)
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {

    Optional<UserAccount> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE UserAccount a SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

}
