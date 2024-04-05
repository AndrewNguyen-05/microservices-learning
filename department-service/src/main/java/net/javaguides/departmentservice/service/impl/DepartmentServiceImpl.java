package net.javaguides.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.enitity.Department;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import net.javaguides.departmentservice.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper mapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper mapper){
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department newDepartment = mapToEntity(departmentDto);
        Department responseDepartment = this.departmentRepository.save(newDepartment);
        return mapToDto(responseDepartment);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = this.departmentRepository.findAll();
        return departments.stream().map(department -> mapToDto(department)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = this.departmentRepository.findByDepartmentCode(code);
        return mapToDto(department);
    }

    private Department mapToEntity(DepartmentDto departmentDto){
        Department department = mapper.map(departmentDto, Department.class);
        return department;
    }

    private DepartmentDto mapToDto(Department department){
        DepartmentDto departmentDto = mapper.map(department, DepartmentDto.class);
        return departmentDto;
    }
}
