package fit.tdc.edu.vn.cafemanagement.data.model;

import java.util.Date;

import lombok.Data;

@Data
public class Employee {
    private String id;
    private String name;
    private Date birthDay;
    private Gender gender;
    private String identityID;
    private String phoneNumber;
    private String address;
    private Role role;
    private Store store;
    private String userName;
    private String password;
}
