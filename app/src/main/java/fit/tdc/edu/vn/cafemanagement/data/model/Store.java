package fit.tdc.edu.vn.cafemanagement.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Store {
    private String id;
    private String name;
    private String address;
    private String managerID;
}
