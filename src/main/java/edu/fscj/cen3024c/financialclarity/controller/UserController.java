package edu.fscj.cen3024c.financialclarity.controller;

import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.dto.UserDTO;
import edu.fscj.cen3024c.financialclarity.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.TimeInstrument;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getAge(), user.getTotalIncome(), user.getTotalExpenses() );
    }

    @CrossOrigin(origins = {"http://example.com", "http://localhost"})

    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.findAll();
        logger.error("Found all users");
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        UserDTO userDTO = convertToDTO(user);
        logger.error("Found user {}", username);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        Profiler profiler = new Profiler("createUser");
        profiler.start("Create User");

        UserDTO savedUser = userService.save(userDTO);
        logger.info("A new user has been added: {}", savedUser.getUsername());

        TimeInstrument ti = profiler.stop();
        ti.print();

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("{username}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
        User user = userService.findByUsername(username);
        if  (user != null) {
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setAge(userDTO.getAge());
            user.setTotalIncome(userDTO.getTotalIncome());
            user.setTotalExpenses(userDTO.getTotalExpense());
            User updatedUser = userService.save(user);
            logger.info("A user has been updated: {}", username);
            return new ResponseEntity<>(convertToDTO(updatedUser), HttpStatus.OK);
        } else {
            logger.error("Update user failed, not found: {}", username);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{username}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable String username) {
        userService.deleteByUsername(username);
        logger.error("Deleted user {}", username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
