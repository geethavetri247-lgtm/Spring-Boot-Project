package com.example.demo.Controller;

import com.example.demo.DTO.CompanyResponseDTO;
import com.example.demo.Service.CompanyService;
import com.example.demo.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //create
    @PostMapping
    public ResponseEntity<CompanyResponseDTO> createCompany(@RequestBody Company company) {
        CompanyResponseDTO savedCompany = companyService.create(company);
        return ResponseEntity.ok(savedCompany);
    }

    //read
    @GetMapping
    public ResponseEntity<List<CompanyResponseDTO>> getAllCompanies() {
        List<CompanyResponseDTO> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> updateCompany(@PathVariable Long id,
                                                            @RequestBody Company company) {
        CompanyResponseDTO updatedCompany = companyService.updateCompany(id, company);

        if (updatedCompany == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCompany);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {

        if (id != null) {
            companyService.deleteCompany(id);
            return ResponseEntity.ok("Company deleted successfully");
        }
        return  ResponseEntity.notFound().build();
    }
}
