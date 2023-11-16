package com.azubu.schoolmanagementapplication.user;

import com.azubu.schoolmanagementapplication.response.HttpResponse;
import com.azubu.schoolmanagementapplication.roles.UserRoles;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public String welcome() {
        return "HOME PAGE | Try URLs such as: -/users -/users/{roleName}s -/roles";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
//
//    @GetMapping("/users/{id}")
//    public User getUserById(@PathVariable("id") int id) {
//        return userService.getUserById(id);
//    }
//
//    @GetMapping("/users/{roleName}s")
//    public List<User> getUsersByRoles(@PathVariable("roleName") String roleName) {
//        return userService.getUsersByRole(roleName);
//    }
//
//    @GetMapping("/roles")
//    public List<UserRoles> getAllUserRoles() {
//        return userService.getAllUserRoles();
//    }

//    @PostMapping("/register")
//    public User registerNewUser(@RequestBody User user) {
//        return userService.registerNewUser(user);
//    }

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> registerNewUser(@RequestBody User user) {
        userService.registerNewUser(user);
        return ResponseEntity.created(getUri()).body(
               new HttpResponse(
                       LocalDateTime.now().toString(),
                       HttpStatus.CREATED.value(),
                       HttpStatus.CREATED,
                       "User created"
                       )
                );
    }

    private URI getUri() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/get/<userId>").toUriString());
    }

//LocalDateTime.now().toString(),
//                HttpStatus.CREATED.value(),
//    HttpStatus.CREATED,
//            "Created User"
}
