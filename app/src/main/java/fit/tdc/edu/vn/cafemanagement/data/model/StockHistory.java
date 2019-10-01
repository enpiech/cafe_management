package fit.tdc.edu.vn.cafemanagement.data.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockHistory {
    private String materialID;
    private long amount;
    private LocalDateTime stockTakeTime;
}
