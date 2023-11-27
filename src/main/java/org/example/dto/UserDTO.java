package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.RoleName;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String password;
    private List<RoleName> listRole;
}