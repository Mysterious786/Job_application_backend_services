package com.saqlain.First.Project.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    // creating an object of company service
    private CompanyService companyService; // Inorder to call all functions



    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies()
    {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable Long id)
    {
        boolean isUpdated = companyService.updateCompany(company, id);
        if(isUpdated)
            return new ResponseEntity<String>("Company updated successfully!", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Error! 404 Not Found",HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company)
    {
        companyService.createCompany(company);
        return new ResponseEntity<String>("Company added successfully!", HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id)
    {
        boolean isDeleted = companyService.deleteCompanyById(id);
        if(isDeleted)
        {
            return new ResponseEntity<String>("Company Deleted successfully!",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Error 404 Found, company does not exist!", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if(company == null)
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Company>(company, HttpStatus.OK);
    }



}
