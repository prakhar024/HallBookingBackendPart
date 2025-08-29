package com.example.hallbooking.controller;

import com.example.hallbooking.model.Hall;
import com.example.hallbooking.repository.HallRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/halls")
@CrossOrigin(origins = "http://localhost:4200")
public class HallController {

    private final HallRepository hallRepo;

    public HallController(HallRepository hallRepo) {
        this.hallRepo = hallRepo;
    }

    @GetMapping
    public List<Hall> getAllHalls() {
        return hallRepo.findAll();
    }

    @PostMapping
    public Hall createHall(@RequestBody Hall hall) {
        return hallRepo.save(hall);
    }
}

