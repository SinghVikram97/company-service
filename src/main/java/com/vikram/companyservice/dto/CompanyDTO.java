package com.vikram.companyservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyDTO {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
}

