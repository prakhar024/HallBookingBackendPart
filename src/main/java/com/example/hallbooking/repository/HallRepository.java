package com.example.hallbooking.repository;

import com.example.hallbooking.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {
}
