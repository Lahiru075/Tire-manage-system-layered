package lk.ijse.gdse.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    private String cusId;
    private String cusName;
    private String email;
    private String contact;
    private String address;
}
