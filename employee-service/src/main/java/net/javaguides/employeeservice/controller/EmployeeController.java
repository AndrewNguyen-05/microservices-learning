package net.javaguides.employeeservice.controller;

import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(this.employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return new ResponseEntity<>(this.employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(this.employeeService.getEmployeeById(id), HttpStatus.OK);
    }
}
