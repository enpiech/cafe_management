package fit.tdc.edu.vn.cafemanagement.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Material {
    private String id;
    private String name;
    private long price;
    private String unitID;
    private boolean isProduct;
}
