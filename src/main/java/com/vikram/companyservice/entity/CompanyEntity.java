package com.vikram.companyservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}

