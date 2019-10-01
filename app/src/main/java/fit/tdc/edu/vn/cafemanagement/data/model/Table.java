package fit.tdc.edu.vn.cafemanagement.data.model;

public class Table {
    private String id;
    private String zoneID;
    private String stateID;
    private String name;

    Table(String id, String zoneID, String stateID, String name) {
        this.id = id;
        this.zoneID = zoneID;
        this.stateID = stateID;
        this.name = name;
    }

    public static TableBuilder builder() {
        return new TableBuilder();
    }

    public String getId() {
        return this.id;
    }

    public String getZoneID() {
        return this.zoneID;
    }

    public String getStateID() {
        return this.stateID;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setZoneID(String zoneID) {
        this.zoneID = zoneID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Table)) return false;
        final Table other = (Table) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$zoneID = this.getZoneID();
        final Object other$zoneID = other.getZoneID();
        if (this$zoneID == null ? other$zoneID != null : !this$zoneID.equals(other$zoneID))
            return false;
        final Object this$stateID = this.getStateID();
        final Object other$stateID = other.getStateID();
        if (this$stateID == null ? other$stateID != null : !this$stateID.equals(other$stateID))
            return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Table;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $zoneID = this.getZoneID();
        result = result * PRIME + ($zoneID == null ? 43 : $zoneID.hashCode());
        final Object $stateID = this.getStateID();
        result = result * PRIME + ($stateID == null ? 43 : $stateID.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "Table(id=" + this.getId() + ", zoneID=" + this.getZoneID() + ", stateID=" + this.getStateID() + ", name=" + this.getName() + ")";
    }

    public static class TableBuilder {
        private String id;
        private String zoneID;
        private String stateID;
        private String name;

        TableBuilder() {
        }

        public TableBuilder id(String id) {
            this.id = id;
            return this;
        }

        public TableBuilder zoneID(String zoneID) {
            this.zoneID = zoneID;
            return this;
        }

        public TableBuilder stateID(String stateID) {
            this.stateID = stateID;
            return this;
        }

        public TableBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Table build() {
            return new Table(id, zoneID, stateID, name);
        }

        public String toString() {
            return "Table.TableBuilder(id=" + this.id + ", zoneID=" + this.zoneID + ", stateID=" + this.stateID + ", name=" + this.name + ")";
        }
    }
}
