package fit.tdc.edu.vn.cafemanagement.data.model;

import java.util.Date;

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

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Date getBirthDay() {
        return this.birthDay;
    }

    public Gender getGender() {
        return this.gender;
    }

    public String getIdentityID() {
        return this.identityID;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public String getRoleID() {
        return this.roleID;
    }

    public String getStoreID() {
        return this.storeID;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setIdentityID(String identityID) {
        this.identityID = identityID;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Employee)) return false;
        final Employee other = (Employee) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$birthDay = this.getBirthDay();
        final Object other$birthDay = other.getBirthDay();
        if (this$birthDay == null ? other$birthDay != null : !this$birthDay.equals(other$birthDay))
            return false;
        final Object this$gender = this.getGender();
        final Object other$gender = other.getGender();
        if (this$gender == null ? other$gender != null : !this$gender.equals(other$gender))
            return false;
        final Object this$identityID = this.getIdentityID();
        final Object other$identityID = other.getIdentityID();
        if (this$identityID == null ? other$identityID != null : !this$identityID.equals(other$identityID))
            return false;
        final Object this$phoneNumber = this.getPhoneNumber();
        final Object other$phoneNumber = other.getPhoneNumber();
        if (this$phoneNumber == null ? other$phoneNumber != null : !this$phoneNumber.equals(other$phoneNumber))
            return false;
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address))
            return false;
        final Object this$roleID = this.getRoleID();
        final Object other$roleID = other.getRoleID();
        if (this$roleID == null ? other$roleID != null : !this$roleID.equals(other$roleID))
            return false;
        final Object this$storeID = this.getStoreID();
        final Object other$storeID = other.getStoreID();
        if (this$storeID == null ? other$storeID != null : !this$storeID.equals(other$storeID))
            return false;
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName))
            return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Employee;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $birthDay = this.getBirthDay();
        result = result * PRIME + ($birthDay == null ? 43 : $birthDay.hashCode());
        final Object $gender = this.getGender();
        result = result * PRIME + ($gender == null ? 43 : $gender.hashCode());
        final Object $identityID = this.getIdentityID();
        result = result * PRIME + ($identityID == null ? 43 : $identityID.hashCode());
        final Object $phoneNumber = this.getPhoneNumber();
        result = result * PRIME + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
        final Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final Object $roleID = this.getRoleID();
        result = result * PRIME + ($roleID == null ? 43 : $roleID.hashCode());
        final Object $storeID = this.getStoreID();
        result = result * PRIME + ($storeID == null ? 43 : $storeID.hashCode());
        final Object $userName = this.getUserName();
        result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        return "Employee(id=" + this.getId() + ", name=" + this.getName() + ", birthDay=" + this.getBirthDay() + ", gender=" + this.getGender() + ", identityID=" + this.getIdentityID() + ", phoneNumber=" + this.getPhoneNumber() + ", address=" + this.getAddress() + ", roleID=" + this.getRoleID() + ", storeID=" + this.getStoreID() + ", userName=" + this.getUserName() + ", password=" + this.getPassword() + ")";
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
