package tech.dimitar.app.ws.services;

import tech.dimitar.app.ws.dto.UserDTO;

public interface UserService {

    Iterable<UserDTO> getUsers();
    UserDTO getUser(final String userId);
    void addUser(final UserDTO userDTO);
    void deleteUser(final String userId);

}
