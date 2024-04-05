package net.javaguides.departmentservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "DepartmentService - Department Controller",
        description = "Department Controller exposes REST APIs for Department Service"
)

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @Operation(
            summary = "Save Department REST API",
            description = "Save Department REST API is used to save department object in a databases"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(this.departmentService.saveDepartment(departmentDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All Department REST API",
            description = "Get All Department REST API is used to get all department object in a databases"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        return new ResponseEntity<>(this.departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @Operation(
            summary = "Get Department REST API",
            description = "Get Department REST API is used to get department object in a databases"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable(name = "department-code") String code){
        return new ResponseEntity<>(this.departmentService.getDepartmentByCode(code), HttpStatus.OK);
    }
}
