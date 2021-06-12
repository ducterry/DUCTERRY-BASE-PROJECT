package com.ducterry.base.ducterrybase.dto.auth.req;


import com.ducterry.base.ducterrybase.entity.login.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignUpForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Set<Role> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
