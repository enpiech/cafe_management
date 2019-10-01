package fit.tdc.edu.vn.cafemanagement.data.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Revenue {
    private String id;
    private String storeID;
    private long income;
    private long outcome;
    private Date startDate;
    private Date endDate;
}
