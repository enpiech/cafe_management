package fit.tdc.edu.vn.cafemanagement.data.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private String id;
    private String name;
    private Date birthDay;
    private Gender gender;
    private String identityID;
    private String phoneNumber;
    private String address;
    private String roleID;
    private String storeID;
    private String userName;
    private String password;
}
