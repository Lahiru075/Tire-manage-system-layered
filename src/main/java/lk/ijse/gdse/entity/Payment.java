package lk.ijse.gdse.entity;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payment {
    private String pId;
    private double amount;
    private Date date;
    private String status;
    private String paymentMethod;

}
