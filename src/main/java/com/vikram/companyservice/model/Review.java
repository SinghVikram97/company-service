package com.vikram.companyservice.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review {
    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @DecimalMin("1.0")
    @DecimalMax("10.0")
    private Double rating;
    @NotNull
    private Long companyId;
}
