package lk.ijse.gdse.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String usId;
    private String role;
    private String password;
    private String username;
}
