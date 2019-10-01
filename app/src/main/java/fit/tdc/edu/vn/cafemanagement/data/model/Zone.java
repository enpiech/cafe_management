package fit.tdc.edu.vn.cafemanagement.data.model;

import lombok.Data;

@Data
class Zone {
    private String id;
    private ZoneType type;
    private Store store;
}
