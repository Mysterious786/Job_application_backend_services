package com.saqlain.First.Project.company;

import java.util.List;
import java.util.Optional;


public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    // If I am deleting a company that means I have to delete all the jobs relevant to this company....
    Company getCompanyById(Long id);
}
