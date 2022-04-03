package com.Sofka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sofka.domain.Session;

public interface SessionRepository extends  JpaRepository<Session, Integer>{

}
