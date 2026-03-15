package com.example.demo.Service;

import com.example.demo.DTO.CompanyResponseDTO;
import com.example.demo.Repository.CompanyRepository;
import com.example.demo.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    private CompanyResponseDTO convertToDTO(Company company){
        CompanyResponseDTO dto = new CompanyResponseDTO();
        dto.setName(company.getName());
        dto.setDescription(company.getDescription());

        return dto;
    }

    //create
    public CompanyResponseDTO create(Company company) {
        Company savedCompany = companyRepository.save(company);
        return convertToDTO(savedCompany);
    }

    //read
    public List<CompanyResponseDTO> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //update
    public CompanyResponseDTO updateCompany(Long id, Company company) {
        Company c = companyRepository.findById(id).orElse(null);

        if(c == null){
            System.out.println("Company not found");
            return null;
    }
        c.setName(company.getName());
        c.setDescription(company.getDescription());
        Company updatedCompany = companyRepository.save(c);

        return convertToDTO(updatedCompany);
    }

   //delete
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
