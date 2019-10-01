package fit.tdc.edu.vn.cafemanagement.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OderItems {
    private String paymentID;
    private String materialID;
    private int amount;
}
