package lk.ijse.gdse.entity;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Report {
    private String paymentId;
    private double paymentAmount;
    private String date;
    private String paymentStatus;
    private String discountId;
    private double discountAmount;
    private String paymentMethod;
}
