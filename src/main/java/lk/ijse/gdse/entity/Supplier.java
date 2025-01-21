package lk.ijse.gdse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Supplier {
    private String supId;
    private String name;
    private String email;
    private String contact;
    private String address;
}
