package glab.restaurante.rest;

import glab.restaurante.modelos.Reserva;
import glab.restaurante.modelos.Role;
import glab.restaurante.modelos.User;
import glab.restaurante.service.RoleService;
import glab.restaurante.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.POST)
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

    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody Map<String, String> loginData) {
        try {
            String email = loginData.get("email");
            String password = loginData.get("password");
            User exist = userService.findByEmail(email);
            if (exist == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuario no existe");
            } else {
                if (exist.getPassword().equals(password) && exist.getRole().getType().equals("administrador")){
                    return ResponseEntity.ok("access");
                }
                else{
                    return ResponseEntity.ok("denied");
                }
            }
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

    @PutMapping("/{id}")
    private ResponseEntity<User> updateuser(@RequestBody User user, @PathVariable("id") Long userId) {
        try {
            User exist = userService.getById(userId);
            if (exist == null) {
                throw new RuntimeException("El usuario no existe");
            }
            exist.setUserId(user.getUserId());
            exist.setPassword(user.getPassword());
            exist.setName(user.getName());
            exist.setLastName(user.getLastName());
            exist.setIdType(user.getIdType());
            exist.setEmail(user.getEmail());
            userService.save(exist);
            return ResponseEntity.ok(exist);
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
