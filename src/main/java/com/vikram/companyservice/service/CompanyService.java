package com.vikram.companyservice.service;

import com.vikram.companyservice.dto.CompanyDTO;
import com.vikram.companyservice.model.Job;

import java.util.List;

public interface CompanyService {
    CompanyDTO createCompany(CompanyDTO companyDto);

    List<CompanyDTO> getAllCompanies();
    CompanyDTO getCompany(Long id);

    CompanyDTO updateCompany(Long id, CompanyDTO companyDto);

    CompanyDTO deleteCompany(Long id);

    List<Job> getAllJobsByCompany(Long id);
}
