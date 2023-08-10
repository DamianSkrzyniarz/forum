package pl.dskrzyniarz.forum.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String email; //TODO: email validation
    @NotEmpty
    private String password;
}
