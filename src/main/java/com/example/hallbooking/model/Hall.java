package com.example.hallbooking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "hall",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "location"}) // prevent duplicates
        }
)
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String location;
    private Integer capacity;
    private Double pricePerDay;
}
