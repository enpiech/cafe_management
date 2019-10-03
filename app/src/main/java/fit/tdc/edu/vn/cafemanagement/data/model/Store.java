package fit.tdc.edu.vn.cafemanagement.data.model;

public class Store {
    private String id;
    private String name;
    private String address;
    private String managerID;

    Store(String id, String name, String address, String managerID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.managerID = managerID;
    }

    public static StoreBuilder builder() {
        return new StoreBuilder();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getManagerID() {
        return this.managerID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Store)) return false;
        final Store other = (Store) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address))
            return false;
        final Object this$managerID = this.getManagerID();
        final Object other$managerID = other.getManagerID();
        if (this$managerID == null ? other$managerID != null : !this$managerID.equals(other$managerID))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Store;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final Object $managerID = this.getManagerID();
        result = result * PRIME + ($managerID == null ? 43 : $managerID.hashCode());
        return result;
    }

    public String toString() {
        return "Store(id=" + this.getId() + ", name=" + this.getName() + ", address=" + this.getAddress() + ", managerID=" + this.getManagerID() + ")";
    }

    public static class StoreBuilder {
        private String id;
        private String name;
        private String address;
        private String managerID;

        StoreBuilder() {
        }

        public StoreBuilder id(String id) {
            this.id = id;
            return this;
        }

        public StoreBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StoreBuilder address(String address) {
            this.address = address;
            return this;
        }

        public StoreBuilder managerID(String managerID) {
            this.managerID = managerID;
            return this;
        }

        public Store build() {
            return new Store(id, name, address, managerID);
        }

        public String toString() {
            return "Store.StoreBuilder(id=" + this.id + ", name=" + this.name + ", address=" + this.address + ", managerID=" + this.managerID + ")";
        }
    }
}
