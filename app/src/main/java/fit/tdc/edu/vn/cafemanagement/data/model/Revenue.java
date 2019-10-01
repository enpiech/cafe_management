package fit.tdc.edu.vn.cafemanagement.data.model;

import java.util.Date;

import lombok.Data;

@Data
public class Revenue {
    private String id;
    private Store store;
    private long income;
    private long outcome;
    private Date startDate;
    private Date endDate;
}
