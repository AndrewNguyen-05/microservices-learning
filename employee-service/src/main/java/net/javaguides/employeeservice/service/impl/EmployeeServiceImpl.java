package net.javaguides.employeeservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.dto.OrganizationDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.APIClient;
import net.javaguides.employeeservice.service.EmployeeService;
import net.javaguides.employeeservice.service.OrganizationAPIClient;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;
    private ModelMapper mapper;

    //private RestTemplate restTemplate;
    //private WebClient webClient;
    private APIClient apiClient;
    private OrganizationAPIClient organizationAPIClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee newEmployee = mapToEntity(employeeDto);
        Employee responseEmployee = this.employeeRepository.save(newEmployee);
        return mapToDto(responseEmployee);
    }

//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(long id){

        LOGGER.info("inside getEmployeeById() method");
        Employee employee = this.employeeRepository.findById(id).get();

        // REST TEMPLATE (DEPRECATED TO SUPPORT WEB CLIENT)
//        ResponseEntity<DepartmentDto> response = this.restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
//
//        DepartmentDto departmentDto = response.getBody();


        // WEB CLIENT
//        DepartmentDto departmentDto =  this.webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        // SPRING CLOUD OPENFEIGN
        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
        OrganizationDto organizationDto = organizationAPIClient.getOrganizationByCode(employee.getOrganizationCode());

        EmployeeDto employeeDto = mapToDto(employee);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = this.employeeRepository.findAll();
        return employees.stream().map(employee -> mapToDto(employee)).collect(Collectors.toList());
    }

    private EmployeeDto mapToDto(Employee employee){
        EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    private Employee mapToEntity(EmployeeDto employeeDto){
        Employee employee = mapper.map(employeeDto, Employee.class);
        return employee;
    }

    public APIResponseDto getDefaultDepartment(long id, Exception exception){

        LOGGER.info("inside getDefaultDepartment() method");

        Employee employee = this.employeeRepository.findById(id).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        EmployeeDto employeeDto = mapToDto(employee);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
