package net.javaguides.employeeservice.service;

import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

    // Build get department rest API
    @GetMapping("/api/departments/{department-code}")
    DepartmentDto getDepartmentByCode(@PathVariable("department-code") String departmentCode);

}
