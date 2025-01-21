package lk.ijse.gdse.entity;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TireOrder {
    private String orderId;
    private String tireId;
    private String description;
    private String payment_method;
    private int qty;
    private double total_amount;
}
