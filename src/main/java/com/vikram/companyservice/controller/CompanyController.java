package com.vikram.companyservice.controller;

import com.vikram.companyservice.dto.CompanyDTO;
import com.vikram.companyservice.model.Job;
import com.vikram.companyservice.model.Review;
import com.vikram.companyservice.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @GetMapping
    public List<CompanyDTO> getAllCompanies(){
        logger.info("Get all companies request");
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyDTO getCompany(@PathVariable Long id){
        logger.info("Get company request with id: {}",id);
        return companyService.getCompany(id);
    }

    @PostMapping
    public CompanyDTO createCompany(@RequestBody @Valid CompanyDTO companyDto) {
        logger.info("Create company request: {}",companyDto);
        return companyService.createCompany(companyDto);
    }

    @PutMapping("/{id}")
    public CompanyDTO updateCompany(@PathVariable Long id, @RequestBody @Valid CompanyDTO companyDto) {
        logger.info("Update company request with id: {} and request body: {}",id, companyDto);
        return companyService.updateCompany(id, companyDto);
    }

    @DeleteMapping("/{id}")
    public CompanyDTO deleteCompany(@PathVariable Long id){
        logger.info("Delete company request with id: {}",id);
        return companyService.deleteCompany(id);
    }

    @GetMapping("/{id}/jobs")
    public List<Job> getJobsByCompany(@PathVariable Long id) {
        logger.info("Get all jobs by company request with id: {}",id);
        return companyService.getAllJobsByCompany(id);
    }

    @GetMapping("/{id}/reviews")
    public List<Review> getAllReviewsForCompany(@PathVariable Long id) {
        logger.info("Get all reviews by company request with id: {}",id);
        return companyService.getAllReviewsForCompany(id);
    }
}

