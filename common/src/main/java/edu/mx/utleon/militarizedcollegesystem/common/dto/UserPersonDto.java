package edu.mx.utleon.militarizedcollegesystem.common.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserPersonDto {

    private Long userId;
    private String username;
    private String password;
    private String email;
    private boolean active;
    private String role;
    private Long personId;
    private String fullName;
    private Long phone;
    private String curp;

}
