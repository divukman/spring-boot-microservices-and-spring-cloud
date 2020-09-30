package tech.dimitar.app.ws.services;

import org.springframework.stereotype.Service;
import tech.dimitar.app.ws.dto.UserDTO;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Dummy service implementation.
 */
@Service
public class UserServiceImpl implements UserService {
    private final Set<UserDTO> userSet = new LinkedHashSet<>();


    @Override
    public Iterable<UserDTO> getUsers() {
        return userSet;
    }

    @Override
    public UserDTO getUser(String userId) {
        return userSet.stream().filter(userDTO -> userDTO.getUserId().equalsIgnoreCase(userId)).findFirst().orElse(null);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        userSet.add(userDTO);
    }

    @Override
    public void deleteUser(String userId) {
        final UserDTO user = getUser(userId);
        if (user != null) {
            userSet.remove(user);
        }
    }
}
