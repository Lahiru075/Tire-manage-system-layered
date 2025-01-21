package lk.ijse.gdse.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlaceOrder {
    private String tireId;
    private String tireBrand;
    private String tireModel;
    private String tireSize;
    private int year;
    private double tirePrice;
}
