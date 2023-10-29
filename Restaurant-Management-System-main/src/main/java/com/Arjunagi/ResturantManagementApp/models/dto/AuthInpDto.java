package com.Arjunagi.ResturantManagementApp.models.dto;

import com.Arjunagi.ResturantManagementApp.models.user.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthInpDto {
    @NotBlank
    private String TokenValue;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;
    private Role role;

}
