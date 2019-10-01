package fit.tdc.edu.vn.cafemanagement.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Table {
    private String id;
    private String zoneID;
    private String stateID;
    private String name;
}
