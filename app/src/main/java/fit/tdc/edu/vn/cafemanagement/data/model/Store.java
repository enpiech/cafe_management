package fit.tdc.edu.vn.cafemanagement.data.model;

import lombok.Data;

@Data
public class Store {
    private String id;
    private String name;
    private String address;
    private Employee manager;
}
