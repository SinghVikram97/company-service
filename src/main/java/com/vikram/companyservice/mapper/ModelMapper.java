package com.vikram.companyservice.mapper;

import com.vikram.companyservice.dto.CompanyDTO;
import com.vikram.companyservice.entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public CompanyDTO mapCompanyToCompanyDto(CompanyEntity company){
        return CompanyDTO.builder()
                .name(company.getName())
                .description(company.getDescription())
                .id(company.getId())
                .build();
    }
    public CompanyEntity mapCompanyDtoToCompany(CompanyDTO companyDto){
        return CompanyEntity.builder()
                .name(companyDto.getName())
                .description(companyDto.getDescription())
                .build();
    }
}
