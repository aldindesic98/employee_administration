package com.example.employee_administration.api;

import com.example.employee_administration.dto.AuthorityDTO;
import com.example.employee_administration.exception.ServiceException;
import com.example.employee_administration.service.AuthorityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "*")
public class AuthorityController {
    private final AuthorityService roleService;


    public AuthorityController(AuthorityService authorityService) {
        this.roleService = authorityService;
    }

    @GetMapping
    public ResponseEntity<?> getAuthorities(){
        try {
            return ResponseEntity.ok(roleService.getAuthorities());
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorityById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(roleService.getAuthorityById(id));
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> saveAuthority(@RequestBody AuthorityDTO authorityDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(roleService.saveAuthority(authorityDTO));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateAuthority(@RequestBody AuthorityDTO authorityDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(roleService.updateAuthority(authorityDTO));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try {
            roleService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

}
