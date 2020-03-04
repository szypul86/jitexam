package com.jitexam.jitexam.repository;

import com.jitexam.jitexam.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
