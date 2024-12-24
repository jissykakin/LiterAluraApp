package com.challenger.literAlura.repositories;

import com.challenger.literAlura.models.Book;
import com.challenger.literAlura.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String email);

}
