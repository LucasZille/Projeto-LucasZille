package org.example.dto;

import lombok.Data;
import org.example.enums.RoleName;

import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String password;
    private List<RoleName> listRole;
}