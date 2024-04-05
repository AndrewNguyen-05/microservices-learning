package net.javaguides.organizationservice.controller;

import lombok.AllArgsConstructor;
import net.javaguides.organizationservice.dto.OrganizationDto;
import net.javaguides.organizationservice.service.OrganizationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@AllArgsConstructor
public class OrganizationController {
    private OrganizationService organizationService;

    @GetMapping
    private ResponseEntity<List<OrganizationDto>> getAllOrganizations(){
        return new ResponseEntity<>(this.organizationService.getAllOrganizations(), HttpStatus.OK);
    }

    @GetMapping("/{organizationCode}")
    private ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable(name = "organizationCode") String code){
        return new ResponseEntity<>(this.organizationService.getOrganizationByCode(code), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<OrganizationDto> createOrganization(@RequestBody OrganizationDto organizationDto){
        return new ResponseEntity<>(this.organizationService.createOrganization(organizationDto), HttpStatus.CREATED);
    }
}
