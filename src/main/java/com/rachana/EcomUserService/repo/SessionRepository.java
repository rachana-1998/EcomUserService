package com.rachana.EcomUserService.repo;

import com.rachana.EcomUserService.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

   Optional<Session>  findByTokenAndUser_Id(String token, Long userId);
}
