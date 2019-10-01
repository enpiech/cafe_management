package fit.tdc.edu.vn.cafemanagement.data.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Payment {
    private String id;
    private Store store;
    private Table table;
    private Employee confirmEmployee;
    private LocalDateTime paidTime;
    private String price;
    private PaymentState state;
    private String formatID;
}
