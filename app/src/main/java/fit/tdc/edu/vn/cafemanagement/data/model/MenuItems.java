package fit.tdc.edu.vn.cafemanagement.data.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItems {
    private String menuID;
    private List<Material> materials;
    private long amount;
}
