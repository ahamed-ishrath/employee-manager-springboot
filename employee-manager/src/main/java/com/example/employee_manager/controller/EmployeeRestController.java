package com.example.employee_manager.controller;

import com.example.employee_manager.model.Employee;
import com.example.employee_manager.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeService service;

    public EmployeeRestController(EmployeeService service) {
        this.service = service;
    }

    // GET all
    @GetMapping
    public List<Employee> getAll() {
        return service.findAll();
    }

    // GET one
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getOne(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Employee employee) {
        if (service.emailExists(employee.getEmail(), null)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("This email is already registered");
        }
        service.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    // PUT update
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody Employee employee) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (service.emailExists(employee.getEmail(), id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("This email is already registered");
        }
        employee.setId(id);
        service.save(employee);
        return ResponseEntity.ok(employee);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
