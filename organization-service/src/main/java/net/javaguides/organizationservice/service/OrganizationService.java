package net.javaguides.organizationservice.service;

import net.javaguides.organizationservice.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {
    List<OrganizationDto> getAllOrganizations();

    OrganizationDto getOrganizationByCode(String organizationCode);
    OrganizationDto createOrganization(OrganizationDto organizationDto);
}