package net.javaguides.organizationservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.organizationservice.dto.OrganizationDto;
import net.javaguides.organizationservice.entity.Organization;
import net.javaguides.organizationservice.repository.OrganizationRepository;
import net.javaguides.organizationservice.service.OrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;
    private ModelMapper mapper;

    @Override
    public List<OrganizationDto> getAllOrganizations() {
        List<Organization> organizations = this.organizationRepository.findAll();
        return organizations.stream().map(organization -> mapToDto(organization)).collect(Collectors.toList());
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = this.organizationRepository.findByOrganizationCode(organizationCode);
        return mapToDto(organization);
    }

    @Override
    public OrganizationDto createOrganization(OrganizationDto organizationDto) {
        Organization newOrganization = mapToEntity(organizationDto);

        Organization responseOrganization = this.organizationRepository.save(newOrganization);

        return mapToDto(responseOrganization);
    }

    private OrganizationDto mapToDto(Organization organization){
        OrganizationDto organizationDto = mapper.map(organization, OrganizationDto.class);
        return organizationDto;
    }

    private Organization mapToEntity(OrganizationDto organizationDto){
        Organization organization = mapper.map(organizationDto, Organization.class);
        return organization;
    }
}
