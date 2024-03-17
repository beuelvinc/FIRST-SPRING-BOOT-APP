package com.java_learn.FirstSpring.company.impl;

import com.java_learn.FirstSpring.company.Company;
import com.java_learn.FirstSpring.company.CompanyRepository;
import com.java_learn.FirstSpring.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company updatedCompany, Long id) {
        Optional<Company> companyOption = companyRepository.findById(id);

        if (companyOption.isPresent()){
            Company company = companyOption.get();
            company.setDescription(updatedCompany.getDescription());
            company.setJobs(updatedCompany.getJobs());
            company.setName(updatedCompany.getName());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);

    }

    @Override
    public boolean deleteCompany(Long id) {

        try{
            companyRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
