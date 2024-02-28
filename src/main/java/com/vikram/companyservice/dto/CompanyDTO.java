package com.vikram.companyservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyDTO {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
}

