package tech.dimitar.app.ws.dto;

import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserDTO {
    @Null(message = "User Id must be null")
    private String userId;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    @Email
    private String email;


    @Size(min = 8, max = 16)
    private String password;
}
