package lk.ijse.gdse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Employee {
    private String empId;
    private String name;
    private String role;
    private String email;
    private String address;
    private String contact;
    private double salary;
}
