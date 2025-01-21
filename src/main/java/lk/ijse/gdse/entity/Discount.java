package lk.ijse.gdse.entity;

import lombok.*;

import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
    private String discId;
    private double amount;
    private Date date;
    private String paymentId;
}
