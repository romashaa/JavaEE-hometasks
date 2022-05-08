package com.example.romanenko_dz3_javaee.repository;


import com.example.romanenko_dz3_javaee.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query("SELECT user FROM UserEntity user "
            + "LEFT JOIN FETCH user.permissions "
            + "WHERE user.login = :login")
    Optional<UserEntity> findByLogin(@Param("login") String login);

    @Query("SELECT user FROM UserEntity user "
            + "LEFT JOIN FETCH user.favouriteBooks "
            + "WHERE user.login = :login")
    Optional<UserEntity> findFavouritesByLogin(@Param("login") String login);

    boolean existsByLogin(String login);
}
