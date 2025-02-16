package com.saqlain.First.Project.company.impl;

import com.saqlain.First.Project.company.Company;
import com.saqlain.First.Project.company.CompanyRepository;
import com.saqlain.First.Project.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    // defining a repository of company to return all companies.
    private CompanyRepository companyRepository; // now create its constructor after this


    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;

    }

    @Override
    public Company getCompanyById(Long id) {
        // first check whether the company exist or not
       return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            companyToUpdate.setJobs(company.getJobs());
            companyRepository.save(companyToUpdate);
            return true; // company updated successfully
        }
        else
            return false;
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
