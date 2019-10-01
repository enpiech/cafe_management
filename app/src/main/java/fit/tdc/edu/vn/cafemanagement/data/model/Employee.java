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
    private String roleID;
    private String storeID;
    private String userName;
    private String password;

    Employee(String id, String name, Date birthDay, Gender gender, String identityID, String phoneNumber, String address, String roleID, String storeID, String userName, String password) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.identityID = identityID;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roleID = roleID;
        this.storeID = storeID;
        this.userName = userName;
        this.password = password;
    }

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public static class EmployeeBuilder {
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

        EmployeeBuilder() {
        }

        public EmployeeBuilder id(String id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public EmployeeBuilder birthDay(Date birthDay) {
            this.birthDay = birthDay;
            return this;
        }

        public EmployeeBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public EmployeeBuilder identityID(String identityID) {
            this.identityID = identityID;
            return this;
        }

        public EmployeeBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public EmployeeBuilder address(String address) {
            this.address = address;
            return this;
        }

        public EmployeeBuilder roleID(String roleID) {
            this.roleID = roleID;
            return this;
        }

        public EmployeeBuilder storeID(String storeID) {
            this.storeID = storeID;
            return this;
        }

        public EmployeeBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public EmployeeBuilder password(String password) {
            this.password = password;
            return this;
        }

        public Employee build() {
            return new Employee(id, name, birthDay, gender, identityID, phoneNumber, address, roleID, storeID, userName, password);
        }

        public String toString() {
            return "Employee.EmployeeBuilder(id=" + this.id + ", name=" + this.name + ", birthDay=" + this.birthDay + ", gender=" + this.gender + ", identityID=" + this.identityID + ", phoneNumber=" + this.phoneNumber + ", address=" + this.address + ", roleID=" + this.roleID + ", storeID=" + this.storeID + ", userName=" + this.userName + ", password=" + this.password + ")";
        }
    }
}
