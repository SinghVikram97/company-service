package com.vikram.companyservice.controller;

import com.vikram.companyservice.dto.CompanyDTO;
import com.vikram.companyservice.model.Job;
import com.vikram.companyservice.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public List<CompanyDTO> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyDTO getCompany(@PathVariable Long id){
        return companyService.getCompany(id);
    }

    @PostMapping
    public CompanyDTO createCompany(@RequestBody @Valid CompanyDTO companyDto) {
        return companyService.createCompany(companyDto);
    }

    @PutMapping("/{id}")
    public CompanyDTO updateCompany(@PathVariable Long id, @RequestBody @Valid CompanyDTO companyDto) {
        return companyService.updateCompany(id, companyDto);
    }

    @DeleteMapping("/{id}")
    public CompanyDTO deleteCompany(@PathVariable Long id){
        return companyService.deleteCompany(id);
    }

    @GetMapping("/{id}/jobs")
    public List<Job> getJobsByCompany(@PathVariable Long id) {
        return companyService.getAllJobsByCompany(id);
    }
}

