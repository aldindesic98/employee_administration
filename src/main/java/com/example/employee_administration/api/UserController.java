package com.example.employee_administration.api;

import com.example.employee_administration.dto.UserDTO;
import com.example.employee_administration.exception.ServiceException;
import com.example.employee_administration.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers(){
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDTO));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(userDTO));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

}
