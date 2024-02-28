package com.vikram.companyservice.service;

import com.vikram.companyservice.dto.CompanyDTO;
import com.vikram.companyservice.entity.CompanyEntity;
import com.vikram.companyservice.exception.ResourceNotFoundException;
import com.vikram.companyservice.mapper.ModelMapper;
import com.vikram.companyservice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{
    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;


    @Override
    public CompanyDTO createCompany(CompanyDTO companyDto) {
        CompanyEntity company = modelMapper.mapCompanyDtoToCompany(companyDto);
        CompanyEntity savedCompany = companyRepository.save(company);
        return modelMapper.mapCompanyToCompanyDto(savedCompany);
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<CompanyEntity> allCompanies = companyRepository.findAll();
        return allCompanies.stream().map(modelMapper::mapCompanyToCompanyDto).collect(Collectors.toList());
    }

    @Override
    public CompanyDTO getCompany(Long id) {
        CompanyEntity company = getCompanyOrThrowException(id);
        return modelMapper.mapCompanyToCompanyDto(company);
    }

    @Override
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDto) {
        CompanyEntity company = getCompanyOrThrowException(id);
        company.setDescription(companyDto.getDescription());
        company.setName(companyDto.getName());
        CompanyEntity savedCompany = companyRepository.save(company);
        return modelMapper.mapCompanyToCompanyDto(savedCompany);
    }

    @Override
    public CompanyDTO deleteCompany(Long id) {
        CompanyEntity company = getCompanyOrThrowException(id);
        companyRepository.deleteById(company.getId());
        return modelMapper.mapCompanyToCompanyDto(company);
    }

    private CompanyEntity getCompanyOrThrowException(Long id){
        return companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
    }
}
