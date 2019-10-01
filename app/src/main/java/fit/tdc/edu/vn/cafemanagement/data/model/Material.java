package fit.tdc.edu.vn.cafemanagement.data.model;

import lombok.Data;

@Data
class Material {
    private String id;
    private String name;
    private long price;
    private Unit unit;
    private boolean isProduct;
}
