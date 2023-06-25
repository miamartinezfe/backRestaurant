package glab.restaurante.rest;

import glab.restaurante.modelos.Role;
import glab.restaurante.modelos.User;
import glab.restaurante.service.RoleService;
import glab.restaurante.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserRest {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    private ResponseEntity<List<User>> getUsers() {
        try {
            return ResponseEntity.ok(userService.findAll());
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping
    private ResponseEntity<User> postUser(@RequestBody User user) {
        try {
            Role role = roleService.getById(user.getRole().getType());
            if (role == null) {
                role = new Role(user.getRole().getType());
                roleService.save(role);
            }
            User exist = userService.getById(user.getUserId());
            if (exist == null) {
                User newUser = new User(user.getUserId(), role, user.getPassword(), user.getName(), user.getLastName(),
                        user.getIdType(), user.getEmail());
                userService.save(newUser);
                return ResponseEntity.ok(newUser);
            } else {
                throw new RuntimeException("El usuario ya existe");
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
