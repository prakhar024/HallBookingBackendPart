package com.example.hallbooking.service;

import com.example.hallbooking.model.Hall;
import com.example.hallbooking.repository.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {
    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public Hall saveHall(Hall hall) {
        return hallRepository.save(hall);
    }
}
