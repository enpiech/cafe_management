package fit.tdc.edu.vn.cafemanagement.data.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payment {
    private String id;
    private String storeID;
    private String tableID;
    private String confirmedEmployeeID;
    private LocalDateTime paidTime;
    private String price;
    private String stateID;
    private String formatID;
}
