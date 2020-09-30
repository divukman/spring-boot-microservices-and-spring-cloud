package tech.dimitar.app.ws.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.dimitar.app.ws.dto.UserDTO;
import tech.dimitar.app.ws.exception.ExampleException;
import tech.dimitar.app.ws.services.UserService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping()
    public ResponseEntity<?> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "10") int limit) {
       return new ResponseEntity(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getUser(@PathVariable final String userId) {
        UserDTO user =  userService.getUser(userId);

        if (user != null) {
            return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        userDTO.setUserId(UUID.randomUUID().toString());
        userService.addUser(userDTO);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO) {
        final UserDTO user = userService.getUser(userId);
        if (user != null) {
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<?>  deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/throw")
    public ResponseEntity<?> throwException() throws ExampleException {
        throw new ExampleException("EXAMPLE");
    }
}
