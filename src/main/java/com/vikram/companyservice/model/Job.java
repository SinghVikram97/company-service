package com.vikram.companyservice.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Job {
    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotEmpty
    private String minSalary;
    @NotEmpty
    private String maxSalary;
    @NotEmpty
    private String location;
    @NotNull
    private Long companyId;
}