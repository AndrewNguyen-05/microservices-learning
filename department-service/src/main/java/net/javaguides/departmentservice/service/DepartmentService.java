package net.javaguides.departmentservice.service;

import net.javaguides.departmentservice.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> getAllDepartments();
    DepartmentDto getDepartmentByCode(String code);
}
